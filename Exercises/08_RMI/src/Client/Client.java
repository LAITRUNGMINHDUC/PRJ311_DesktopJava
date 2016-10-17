/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Common.Calculator;
import java.rmi.Naming;

/**
 *
 * @author duclt
 */
public class Client {

    public static void main(String[] args) {
        try {
            Calculator c = (Calculator) Naming.lookup("rmi://localhost:1098/Calculator");
            System.out.println("Add: " + c.add(3, 9));
            System.out.println("Sub: " + c.sub(3, 9));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
