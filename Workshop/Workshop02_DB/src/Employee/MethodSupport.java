/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.sql.*;
import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author duclt
 */
public class MethodSupport {

    static void rdbGrouping(JRadioButton[] myGroup) {
        ButtonGroup bg = new ButtonGroup();
        for (JRadioButton x : myGroup) {
            bg.add(x);
        }
        myGroup[0].setSelected(true);
    }

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test";
    String username = "root";
    String pass = "";

    static void loadData(EmployeeModel<Employee> model, HashSet<String> codes) {
        try {
            //Connection c = openConnection();

            File f = new File("Employee.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                str = str.trim();
                if (str.length() > 0) {
                    StringTokenizer stk = new StringTokenizer(str, ";");
                    String code = stk.nextToken().trim().toUpperCase();
                    String name = stk.nextToken().trim();
                    String address = stk.nextToken().trim();
                    String sexStr = stk.nextToken().trim();
                    boolean sex = (sexStr.equalsIgnoreCase("MALE") == true);
                    int salary = Integer.parseInt(stk.nextToken());
                    model.getData().add(new Employee(code, name, address, sex, salary));
                    codes.add(code);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    static void clearTxtbox(JTextField[] myText) {
        for (JTextField x : myText) {
            x.setText("");
        }
        myText[0].setEditable(true);
        myText[0].setBackground(Color.WHITE);
    }

    static boolean validateData(JTextField[] myText, HashSet<String> codes, boolean addNew) {        
        String myCode = "";
        for (JTextField x : myText) {
            String type = x.getName().trim().toUpperCase();
            String val = x.getText().trim().toUpperCase();

            if (type.compareTo("CODE") == 0 && addNew) {
                myCode = val;
            }

            if (!Validator.checkTxtbox(val, type)) {
                if (type.compareTo("CODE") != 0 && !myCode.isEmpty()) {
                    codes.remove(myCode);
                }
                switch (type) {
                    case "SALARY":
                        JOptionPane.showMessageDialog(null,
                                "- Salary must be Integer \n"
                                + "- First char must be in [1-9] \n"
                                + "- Length must be in [2-9] \n");
                        break;
                    case "NAME":
                        JOptionPane.showMessageDialog(null,
                                "- Name must be alphabetic, can be Unicode, contain space\n"
                                + "- Length must be in [3-50] \n");
                        break;
                    case "CODE":
                        JOptionPane.showMessageDialog(null,
                                "- Code must follow format: Exxx with xxx is numberic\n");
                        break;
                    case "ADDRESS":
                        JOptionPane.showMessageDialog(null,
                                "- Address must be follow this format\n"
                                + "[Numbers], [Street], [Town], [District], [City], [Country]");
                }
                x.requestFocus();
                return false;
            }
            if (type.compareTo("CODE") == 0 && !Validator.checkDuplicate(val, codes)
                    && addNew) {
                JOptionPane.showMessageDialog(null, "Duplicate code");
                x.requestFocus();
                return false;
            }
        }
        return true;
    }

    static boolean saveData(EmployeeModel<Employee> model) {
        try {
            File f = new File("Employee.txt");
            PrintWriter pw = new PrintWriter(f);
            for (Employee x : model.getData()) {
                pw.println(x);
            }
            pw.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

}
