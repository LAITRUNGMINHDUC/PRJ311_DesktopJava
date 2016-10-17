/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author duclt
 */
public class Program {

    public static void main(String[] args) {
        String serviceName = "rmi://127.0.0.1:12345/Cake";
        String fileName = "Cake.txt";
        CakeServer srv = null;
        //EmployeeServer srv = null;

        try {
            srv = new CakeServer(fileName);            
            LocateRegistry.createRegistry(12345);
            Naming.rebind(serviceName, srv);
            System.out.println("Service " + serviceName + " is running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
