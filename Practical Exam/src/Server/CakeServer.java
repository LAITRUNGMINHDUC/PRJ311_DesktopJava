/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author duclt
 */
public class CakeServer extends UnicastRemoteObject
        implements CakeInterface {

    String fileName;

    public CakeServer(String fileName) throws RemoteException {
        super();
        this.fileName = fileName;
    }

    @Override
    public Vector getData() throws RemoteException {
        Vector data = new Vector();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line;
            StringTokenizer stk;
            String code, name;
            int salary;

            while ((line = br.readLine()) != null) {
                stk = new StringTokenizer(line, ",");
                Vector v = new Vector();
                v.add(stk.nextToken()); // code
                v.add(stk.nextToken()); // date        
                v.add(stk.nextToken()); // price
                v.add(stk.nextToken()); // year
                v.add(stk.nextToken()); // company
                data.add(v);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
        }
        return data;
    }

    @Override
    public boolean saveData(Vector data) throws RemoteException {
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < data.size(); i++) {
                Vector v = (Vector) (data.get(i));
                String S = "";
                S += v.get(0) + "," + v.get(1) + "," + v.get(2) + "," + v.get(3) + "," + v.get(4);
                pw.println(S);
            }
            pw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
