public class Motor {
    Motor() {}
    /** @param x Should be in 0 - 1 range */
    public void setPower(double x) {
        final int n = 64;
        final String s = "#".repeat(n);
        final String h = "-".repeat(n);
        System.out.printf(
            "[%s%s] %d%%\n",
            s.substring(0, (int)(x * s.length())),
            h.substring((int)(x * h.length()), h.length()),
            (int)(x * 100.0)
        );
    }
}
