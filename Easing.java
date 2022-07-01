import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Easing {
    public static void main(String[] args) {
        BlockingQueue<Double> q = new LinkedBlockingQueue<Double>();
        EasedMotor motor = new EasedMotor(q);
        new Thread(motor).start();

        Scanner scanner = new Scanner(System.in);
        
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
