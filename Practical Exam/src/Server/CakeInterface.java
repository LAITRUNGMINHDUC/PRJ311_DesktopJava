/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author duclt
 */
public interface CakeInterface extends Remote {

    Vector getData() throws RemoteException;

    boolean saveData(Vector data) throws RemoteException;

}
