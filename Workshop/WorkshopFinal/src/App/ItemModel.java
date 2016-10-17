/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Helper.CustomModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author duclt
 *
 */
public class ItemModel extends CustomModel<Item> {

    public ItemModel(String[] header, int[] indexes) {
        super(header, indexes);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= data.size()
                || columnIndex < 0 || columnIndex >= header.length) {
            return null;
        }

        Item item = data.get(rowIndex);
        switch (indexes[columnIndex]) {
            case 0:
                return item.getCode();
            case 1:
                return item.getName();
            case 2:
                return item.getSupCode();
            case 3:
                return item.getPrice();
            case 4:
                return item.getSupCode();
            case 5:
                return item.isStatus();
        }

        return null;

    }

}
