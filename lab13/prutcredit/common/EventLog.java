package lab13.prutcredit.common;
import java.time.format.DateTimeFormatter;

public final class EventLog {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private EventLog() {
    }

    public static void log(String source, String event) {
        System.out.println("[" + SimClock.now().format(FORMAT) + "] [" + source + "] " + event);
        SimClock.tick();
    }
}