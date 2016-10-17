
import java.io.*;
import java.util.Scanner;
import javazoom.jl.player.Player;

public class Main {

    static Player player = null;

    public static void main(String args[]) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter music file path: ");
            String filename = sc.nextLine().trim();

            FileInputStream fis = null;
            BufferedInputStream bis = null;
            File f = new File(filename);
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);

            Thread playingThread = new Thread() {
                public void run() {
                    try {
                        player.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            playingThread.start();

            System.out.println("Music is playing...");
            System.out.println("Press 'S' to stop!");
            String command = sc.nextLine();
            if (command.equalsIgnoreCase("S")) {
                if (player != null) {
                    player.close();
                    System.out.println("Player stoped.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
