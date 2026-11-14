package lab11.bank.common;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Log11 {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalTime clock = LocalTime.of(9, 0, 0);

    private Log11() {
    }

    public static void log(String source, String event) {
        System.out.printf("[%s] [%s] %s%n", clock.format(FMT), source, event);
        clock = clock.plusSeconds(1);
    }

    public static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}