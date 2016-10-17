/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author duclt
 */
public class Helper {

    static void clearTxtbox(JTextField[] myText) {
        for (JTextField x : myText) {
            x.setText("");
            x.setEditable(true);
            x.setBackground(Color.WHITE);
        }

    }

    static void disableTxtbox(JTextField[] myText) {
        for (JTextField x : myText) {
            x.setText("");
            x.setEditable(false);
            x.setBackground(Color.GRAY);
        }
    }

    static boolean isValid(JTextField[] myText) {
        if (!Validator.checkTxtbox(myText[0].getText(), "CODE")) {
            return false;
        }
        if (!Validator.checkTxtbox(myText[2].getText(), "PRICE")) {
            return false;
        }
        if (!Validator.checkTxtbox(myText[3].getText(), "YEAR")) {
            return false;
        }
        if (!Validator.checkTxtbox(myText[4].getText(), "COMPANY")) {
            return false;
        }
        return true;
    }
}
