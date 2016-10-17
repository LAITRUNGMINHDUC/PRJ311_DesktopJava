/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author duclt
 */
public interface Calculator extends Remote{

    public int add(int a, int b) throws RemoteException;

    public int sub(int a, int b) throws RemoteException;

    public int mul(int a, int b) throws RemoteException;

    public int div(int a, int b) throws RemoteException;
}
