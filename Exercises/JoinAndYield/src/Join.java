/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author duclt
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RunnableImpl());
        Thread t2 = new Thread(new RunnableImpl());
        Thread t3 = new Thread(new RunnableImpl());
        
        t.start();
        t.join(); // the main thread waits till the "t" thread is finished
        t2.start();
        t3.start();
    }
}

class RunnableImpl implements Runnable {

    @Override
    public void run() {
        System.out.println("one");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("two");
    }

}
