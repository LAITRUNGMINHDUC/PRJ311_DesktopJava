/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManualTree;

import java.awt.Color;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author duclt
 */

/*
        Node[0] : Root
        Node[1] : curDepNode
        Node[2] : curEmpNode
 */
public class MethodSupport {

    static void loadData(DefaultMutableTreeNode Node[], HashSet<String> DepCodes, HashSet<String> EmpCodes) {
        try {
            File f = new File("Employee.txt");
            try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {

                String S;
                StringTokenizer stk;
                while ((S = br.readLine()) != null) {
                    S = S.trim();
                    boolean isDept = (S.charAt(S.length() - 1) == ':');
                    stk = new StringTokenizer(S, "-:,");
                    String code = stk.nextToken().trim();
                    String name = stk.nextToken().trim();

                    if (isDept) {
                        Node[1] = new DefaultMutableTreeNode(new Department(code, name));
                        Node[0].add(Node[1]);
                        DepCodes.add(code);
                    } else {
                        int salary = Integer.parseInt(stk.nextToken().trim());
                        Node[2] = new DefaultMutableTreeNode(new Employee(code, name, salary));
                        Node[1].add(Node[2]);
                        EmpCodes.add(code);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    static void clearTxtbox(JTextField[] myText) {
        for (JTextField x : myText) {
            x.setText("");
            x.setBackground(Color.WHITE);
            x.setEditable(true);
        }
    }

    static void disableTxtbox(JTextField[] myText) {
        for (JTextField x : myText) {
            x.setText("");
            x.setBackground(Color.GRAY);
            x.setEditable(false);
        }
    }

    static void saveFile(DefaultMutableTreeNode root) {
        File f = new File("Employee.txt");
        try (FileWriter fw = new FileWriter(f); PrintWriter pw = new PrintWriter(fw);) {
            saveFileRecursive(pw, root.children());
            pw.close();
            fw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        JOptionPane.showMessageDialog(null, "Saved file");
    }

    static void saveFileRecursive(PrintWriter pw, Enumeration Enum) {
        while (Enum.hasMoreElements()) {
            DefaultMutableTreeNode Node = (DefaultMutableTreeNode) Enum.nextElement();
            String S;
            NodeObject nodeObj = (NodeObject) Node.getUserObject();
            S = nodeObj.toStringFile();
            pw.println(S);
            saveFileRecursive(pw, Node.children());
        }
    }

}
