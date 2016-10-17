/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Common.Calculator;
import Common.CalculatorImpl;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author duclt
 */
public class Server {

    public static void main(String[] args) {
        try {
            // 1. Create RMI Registry            
            LocateRegistry.createRegistry(1098); // Port 1099 is default port for RMI
            // 2. Create reference
            Calculator c = new CalculatorImpl();
            // 3. Create service name
            String name = "rmi://localhost:1098/Calculator"; // Protocal://IP:Port/Resource
            // 4. Binding: name <-> reference (c)
            Naming.rebind(name, c);
            
            System.out.println("Server is running! Service " + name + " is ready");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
