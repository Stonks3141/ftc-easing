public class Motor {
    Motor() {}
    /** @param x Should be in range [0, 1] */
    public void setPower(double x) {
        final int n = 72;
        final String s = "#".repeat(n);
        final String h = "-".repeat(n);
        
        System.out.printf(
            "[%s%s] %d%%\n",
            s.substring(0, (int)(x * n)),
            h.substring((int)(x * n), n),
            (int)(x * 100.0)
        );
    }
}
