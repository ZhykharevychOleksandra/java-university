package lab12.prutcredit.common;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class EventLog {

    private static final PrintStream OUT = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final int STEP_SECONDS = 3;

    private static LocalTime clock = LocalTime.of(9, 0, 0);

    private EventLog() {
    }

    public static void log(String source, String event) {
        OUT.printf("[%s] [%s] %s%n", clock.format(FMT), source, event);
        clock = clock.plusSeconds(STEP_SECONDS);
    }

    public static void raw(String line) {
        OUT.println("           " + line);
    }

    public static void section(String title) {
        OUT.println();
        OUT.println("==================== " + title + " ====================");
    }

    public static void setTime(int hour, int minute) {
        clock = LocalTime.of(hour, minute, 0);
    }
}
