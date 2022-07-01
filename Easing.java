import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Easing {
    public static void main(String[] args) {
        BlockingQueue<Double> q = new LinkedBlockingQueue<Double>();
        EasedMotor motor = new EasedMotor(q);
        Thread m = new Thread(motor);
        m.start();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number between 1 and 100.");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            m.interrupt();
            scanner.close();
            System.exit(0);
        }));

        while(true) {
            try {
                double x = Double.parseDouble(scanner.nextLine());
                try {
                    q.put(x / 100.0);            
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }
}
