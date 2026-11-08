package lab10.src.bank.common;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Log {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalTime now = LocalTime.of(9, 0, 0);

    private Log() {
    }

    public static synchronized void log(String source, String event) {
        System.out.println("[" + now.format(FMT) + "] [" + source + "] " + event);
        now = now.plusSeconds(3);
    }

    // виводить створений об'єкт у читабельному вигляді (з відступом)
    public static void print(Object object) {
        for (String line : object.toString().split("\n")) {
            System.out.println("      | " + line);
        }
    }

    public static void header(String title) {
        System.out.println();
        System.out.println("=".repeat(78));
        System.out.println(title);
        System.out.println("=".repeat(78));
    }
}
