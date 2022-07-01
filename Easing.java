import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Easing {
    public static void main(String[] args) {
        BlockingQueue<Double> q = new LinkedBlockingQueue<Double>();
        EasedMotor motor = new EasedMotor(q);
        new Thread(motor).start();
        
        try {
            q.put(0.5);            
            Thread.sleep(150);
            q.put(1.0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
