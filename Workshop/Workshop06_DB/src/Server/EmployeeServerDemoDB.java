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
import java.sql.*;

/**
 *
 * @author duclt
 */
public class EmployeeServerDemoDB extends UnicastRemoteObject
        implements EmpManagementInterface {

    String fileName;

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test";
    String username = "root";
    String pass = "";

    private Connection openConnection() {
        try {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url, username, pass);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public EmployeeServerDemoDB() throws RemoteException {
        super();        
    }

    @Override
    public Vector getInitialData() throws RemoteException {
        Vector data = new Vector();
        try {
            Connection c = openConnection();
            Statement st = c.createStatement();
            String query = "SELECT * FROM Employee";
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("Code").trim());
                v.add(rs.getString("Name").trim());
                v.add(Integer.parseInt(rs.getString("Salary").trim()));
                data.add(v);
            }

            rs.close();
            st.close();
            c.close();
        } catch (Exception e) {
        }
        return data;
    }

    @Override
    public boolean saveList(Vector data) throws RemoteException {
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < data.size(); i++) {
                Vector v = (Vector) (data.get(i));
                String S = "";
                S += v.get(0) + "," + v.get(1) + "," + v.get(2);
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
