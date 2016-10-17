/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.util.HashSet;

/**
 *
 * @author duclt
 */
public class Validator {

    static boolean checkTxtbox(String S, String type) {
        String pattern = "";
        switch (type) {
            case "SALARY":
                pattern = "[1-9]{1}[0-9]{1,8}";
                break;
            case "NAME":
                pattern = "[\\p{L}\\s]{2,30}";
                break;
            case "ADDRESS":
                pattern = "[0-9A-Z/]{1,5}([,\\s]+[\\p{L}0-9\\s]{2,50}){1,5}";
                //pattern = "";
                break;
            case "CODE":
                pattern = "E\\d{3}";
                break;
        }
        return S.matches(pattern);
    }

    static boolean checkDuplicate(String S, HashSet<String> codes) {
        return codes.add(S);
    }
}
