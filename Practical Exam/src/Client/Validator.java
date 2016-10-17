/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.HashSet;

/**
 *
 * @author duclt
 */
public class Validator {

    static boolean checkTxtbox(String S, String type) {
        String pattern = "";
        switch (type) {
            case "PRICE":
                pattern = "[1-9]{1}[0-9]{1,8}";
                break;
            case "COMPANY":
                pattern = "[\\p{L}\\s]{2,30}";
                break;
            case "YEAR":
                pattern = "\\d{4}";
                break;                
            case "DATE":
                pattern = "\\d{4}";
                break;
            case "CODE":
                pattern = "C\\d{3}";
                break;
        }
        return S.matches(pattern);
    }

    static boolean checkDuplicate(String S, HashSet<String> codes) {
        return codes.add(S);
    }
}
