/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author duclt
 */
public class Database {

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

    public void loadData(EmployeeModel<Employee> model, HashSet<String> codes) {
        try {
            Connection c = openConnection();
            Statement st = c.createStatement();
            String query = "SELECT * FROM Employee";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String code = rs.getString("Code").trim();
                String name = rs.getString("Name").trim();
                String address = rs.getString("Address").trim();
                String sexStr = rs.getString("Sex").trim();
                boolean sex = (sexStr.equalsIgnoreCase("MALE") == true);
                int salary = rs.getInt("Salary");

                model.getData().add(new Employee(code, name, address, sex, salary));
                codes.add(code);
            }
            rs.close();
            st.close();
            c.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
