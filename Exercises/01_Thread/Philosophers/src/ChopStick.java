public class ChopStick {
    boolean ready;

    public ChopStick() {
        ready = true;
    }
    
    public synchronized void getUp(){
        while (!ready){
            try {
                System.out.println("A philosopher is waiting for a chopstick.");
                wait();
                
            } catch (InterruptedException e) {
                System.out.println("An error occured!");
            }
        }
        ready = false;
    }
    
    public synchronized void getDown(){
        ready = true;
        notify();
    }
}
