/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeJTree;

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
            case "EMPCODE":
                pattern = "E\\d{3}";
                break;
            case "DEPCODE":
                pattern = "[A-Z0-9]{2,5}";
                break;
        }
        return S.matches(pattern);
    }
}
