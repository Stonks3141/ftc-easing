public class Motor {
    Motor() {}
    /** @param x Should be in 0 - 1 range */
    public void setPower(double x) {
        // bar to visualize smoothing
        final String s = "################################################################";
        System.out.println(s.substring(0, (int)(x * s.length())));
    }
}
