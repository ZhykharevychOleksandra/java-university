package lab16.bank.common;
import java.util.Locale;

public final class Log {
    private static int seconds = 9 * 3600; 

    private Log() {}

    public static void log(String source, String event) {
        seconds += 7;
        System.out.printf("[%02d:%02d:%02d] [%s] %s%n",
                seconds / 3600, (seconds / 60) % 60, seconds % 60, source, event);
    }

    public static void header(String title) {
        System.out.println();
        System.out.println("--- " + title + " ---");
    }

    public static String uah(double amount) {
        return String.format(Locale.US, "%,.2f грн", amount).replace(',', ' ');
    }
}