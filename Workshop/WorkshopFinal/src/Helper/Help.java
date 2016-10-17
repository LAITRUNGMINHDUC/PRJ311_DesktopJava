/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import App.Item;
import App.Supplier;
import com.sun.rowset.CachedRowSetImpl;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author duclt
 */
public class Help {

    public void btnNew(JTable table, JTextField[] txtField) {
        table.clearSelection();
        for (JTextField x : txtField) {
            x.setText("");
            x.setBackground(Color.WHITE);
        }
        txtField[0].requestFocus();
    }

    public void btnSave(JTable table, JTextField[] txtField) {

    }

    public void btnInsertUpdateSupplier(Supplier sup, String type) {
        String query = "";
        if (type.equals("INSERT")) {
            query = "INSERT INTO `suppliers` (`SupName`, `Address`, `Collaborating`, `SupCode`)" + "VALUES (?, ?, ?, ?)";
        } else if (type.equals("UPDATE")) {
            query = "UPDATE `suppliers` SET SupName=?,`Address`=?,`Collaborating`=? WHERE `SupCode` = ?";
        }

        try (Connection c = openConnection();) {
            PreparedStatement st = c.prepareStatement(query);
            st.setString(1, sup.getName());
            st.setString(2, sup.getAddress());
            st.setBoolean(3, sup.isStatus());
            st.setString(4, sup.getCode());
            st.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(String sql, int[] colnames,String[] colValues) throws SQLException {
        String data;
        PreparedStatement st = c.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            st.setString(colnames[i], colValues[i]);
        }
        
    }
    
    public void update(String sql, String condition) {
        
    }
    

    public void btnInsertUpdateItem(Item item, String type) {
        String query = "";
        if (type.equals("INSERT")) {
            query = "INSERT INTO `items` (`itemName`, `supCode`, `unit`, `price`, `supplying`, `itemCode`) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
        } else if (type.equals("UPDATE")) {
            query = "UPDATE `items` SET `itemName`=?, `supCode`=?,"
                    + "`unit`=?,`price`=?,`supplying`=? WHERE `itemCode` = ?";
        }

        try (Connection c = openConnection();) {

            PreparedStatement st = c.prepareStatement(query);
            st.setString(1, item.getName());
            st.setString(2, item.getSupCode());
            st.setString(3, item.getUnit());
            st.setInt(4, item.getPrice());
            st.setBoolean(5, item.isStatus());
            st.setString(6, item.getCode());
            st.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteItem(String code) {
        try (Connection c = openConnection();) {
            String query = "DELETE FROM items WHERE itemCode = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setString(1, code);
            st.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteSupplier(String code) {
        try {
            Connection c = openConnection();
            String query = "SELECT * FROM items WHERE supCode = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                query = "DELETE FROM suppliers WHERE supCode = ?";
                st = c.prepareStatement(query);
                st.setString(1, code);
                st.execute();
                JOptionPane.showMessageDialog(null, "Delete Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Have Items with that Supplier. Can't delete. Please delete items first.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public ResultSet loadData(String tbl) {
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        //Reference at: http://stackoverflow.com/questions/14853508/returning-a-resultset

        try (Connection c = openConnection(); Statement st = c.createStatement()) {
            String query = "SELECT * FROM " + tbl;
            rs = st.executeQuery(query);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
            rs.close();
            st.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crs;
    }

    public boolean checkDuplicateCode(String code, String codeCol, String tableName) {
        ResultSet rs = null;
        boolean flag = false;
        String query = "SELECT * FROM " + tableName + " WHERE " + codeCol + " = ?";
        try (Connection c = openConnection(); PreparedStatement st = c.prepareStatement(query);) {
            st.setString(1, code);
            rs = st.executeQuery();
            if (!rs.next()) {
                flag = true;
            } else {
                flag = false;
            }
            rs.close();
            st.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
