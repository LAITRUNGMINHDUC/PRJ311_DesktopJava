
public class DinnerTable {

    static int n;
    static ChopStick[] sticks = new ChopStick[5];
    static Philosopher[] philosopers = new Philosopher[5];

    public static void main(String[] args) {
        n = 5;
        System.out.println("The program will stop after 20 seconds.");
        System.out.println("---------------------------------------");

        for (int i = 0; i < n; ++i) {
            sticks[i] = new ChopStick();
        }
        for (int i = 0; i < n; ++i) {
            philosopers[i]
                    = new Philosopher(sticks[i], sticks[(i + 1) % 5], i);
        }
        for (int i = 0; i < n; ++i) {
            philosopers[i].setDaemon(true);
            philosopers[i].start();
        }

        Thread autoStop = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(20000);
                    System.out.println("---------------------------------------");
                    System.out.println("Program Stop");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        autoStop.start();

    }
}
