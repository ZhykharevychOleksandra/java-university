package lab15.bank.common;

public final class Log15 {
    private static int minutes = 0;
    private Log15() {}

    public static void log(String source, String event) {
        int m = 9 * 60 + minutes++;
        System.out.printf("[%02d:%02d] [%s] %s%n", m / 60 % 24, m % 60, source, event);
    }

    public static String money(double v) {
        return String.format(java.util.Locale.US, "%.2f грн", v);
    }
}