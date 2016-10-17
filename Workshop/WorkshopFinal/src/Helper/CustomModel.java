/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author duclt
 */
public class CustomModel<E> extends AbstractTableModel {

    public String[] header;
    public int[] indexes;
    public Vector<E> data;

    public CustomModel(String[] header, int[] indexes) {
        this.header = new String[header.length];
        this.indexes = new int[indexes.length];

        System.arraycopy(header, 0, this.header, 0, this.header.length);
        System.arraycopy(indexes, 0, this.indexes, 0, this.indexes.length);

        this.data = new Vector<>();
    }

    public Vector<E> getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 && column < header.length) {
            return header[column];
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

}
