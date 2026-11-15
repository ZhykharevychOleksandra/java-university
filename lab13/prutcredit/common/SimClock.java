package lab13.prutcredit.common;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class SimClock {
    private static LocalDateTime now = LocalDateTime.of(2026, 11, 16, 9, 0);

    private SimClock() {
    }

    public static LocalDateTime now() {
        return now;
    }

    public static LocalDate today() {
        return now.toLocalDate();
    }

    public static void reset(LocalDateTime time) {
        now = time;
    }

    public static void startOfDay(LocalDate day) {
        now = day.atTime(9, 0);
    }

    public static void tick() {
        now = now.plusMinutes(1);
    }
}
