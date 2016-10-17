public class Philosopher extends Thread{
    ChopStick leftStick, rightStick;
    int position;

    public Philosopher(ChopStick lStick, ChopStick rStick, int pos) {
        leftStick = lStick;
        rightStick = rStick;
        position = pos;
    }
    
    public void eat(){
        leftStick.getUp();
        rightStick.getUp();
        System.out.println("The " + position + "(th) philosopher is eating");
    }
    
    public void think(){
        leftStick.getDown();
        rightStick.getDown();
        System.out.println("The " + position + "(th) philosopher is thinking");
    }
    
    @Override
    public void run(){
        while (true){
            eat();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("An error occured!");
            }
            think();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("An error occured!");
            }
        }
    }
}
