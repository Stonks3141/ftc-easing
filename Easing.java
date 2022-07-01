import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Easing {
    public static void main(String[] args) {
        BlockingQueue<Double> q = new LinkedBlockingQueue<Double>();
        EasedMotor motor = new EasedMotor(q);
        new Thread(motor).start();

        System.out.println("Enter a number between 1 and 100.");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                double x = Double.parseDouble(scanner.nextLine());
                // safe, queue is unbounded
                q.offer(x / 100.0);  
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }
}
