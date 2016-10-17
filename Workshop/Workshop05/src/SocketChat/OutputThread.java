/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author ducltm
 */
public class OutputThread extends Thread {

    Socket socket;
    JTextArea txt;
    BufferedReader bf;
    String sender;
    String receiver;

    public OutputThread(Socket socket, JTextArea txt, String sender, String receiver) {
        super();
        this.socket = socket;
        this.txt = txt;
        this.sender = sender;
        this.receiver = receiver;
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Network Error!");
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (socket != null) {
                    String msg = "";
                    if ((msg = bf.readLine()) != null && msg.length() > 0) {
                        if (msg.charAt(0) == 'T') {
                            txt.append("\n" + receiver + ": " + msg.substring(1));
                        }
                        if (msg.charAt(0) == 'F') {
                            saveFile(msg.substring(1));
                        }
                    }
                }
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    final JFileChooser fc = new JFileChooser();

    public void saveFile(String msg) {
        int result = JOptionPane.showConfirmDialog(null, sender + " sent you a file. Receive it?",
                "Receive file from " + sender, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                int resultChooser = fc.showSaveDialog(null);
                if (resultChooser == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    PrintWriter pw = new PrintWriter(f);

                    StringTokenizer stk = new StringTokenizer(msg, "@#$%^");
                    while (stk.hasMoreTokens()) {
                        pw.println(stk.nextToken());
                    }
                    pw.close();
                    JOptionPane.showMessageDialog(null, "File saved");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Can't save file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
