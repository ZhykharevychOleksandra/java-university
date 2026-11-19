package lab14.prutcredit.common;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class EventLog {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalTime now = LocalTime.of(9, 0, 0);

    private EventLog() {}

    public static void reset() { now = LocalTime.of(9, 0, 0); }

    public static void log(String source, String event) {
        System.out.printf("[%s] [%s] %s%n", now.format(FMT), source, event);
        now = now.plusSeconds(1);
    }
}
