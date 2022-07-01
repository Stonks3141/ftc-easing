import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class EasedMotor implements Runnable {
    private BlockingQueue<Double> queue;
    private final long TRANSITION_TIME = 2000; // ms
    private boolean isTransitioning = false;
    private double target = 0.0;
    private double prev = 0.0;
    private double current = 0.0;
    private Motor motor = new Motor();

    EasedMotor(BlockingQueue<Double> q) {
        this.queue = q;
    }

    public void run() {
        long start = 0;

        while(true) {
            try {
                Double x = this.queue.poll(100, TimeUnit.MILLISECONDS);
                if (x != null) {
                    // restart transition if already in progress
                    if (this.isTransitioning) {
                        this.prev = this.current;
                    }
                    System.out.printf("Beginning transition to %d%% power\n", (int)(x * 100.0));
                    this.target = x;
                    this.isTransitioning = true;
                    start = System.currentTimeMillis();
                }
            } catch (InterruptedException e) {}

            long time = System.currentTimeMillis() - start;

            if (time > this.TRANSITION_TIME || !this.isTransitioning) {
                if (this.isTransitioning) {
                    this.motor.setPower(this.target);
                    this.current = this.target;
                    this.prev = this.target;
                }
                this.isTransitioning = false;
                continue;
            }

            // map time into 0-1 range
            double t = (double)time / (double)this.TRANSITION_TIME;

            // renormalize easing function
            this.current = (this.target - this.prev) * ease(t) + this.prev;

            this.motor.setPower(this.current);
        }
    }

    /** @param x Should be in range 0 - 1 */
    private static double ease(double x) {
        return (-Math.cos(Math.PI * x) + 1.0) / 2.0;
    }

    /** Overrides easing */
    public void setPower(double x) {
        this.target = x;
        this.current = x;
        this.prev = x;
        this.isTransitioning = false;
        this.motor.setPower(x);
    }
}
