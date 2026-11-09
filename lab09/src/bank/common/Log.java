package bank.common;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Log {
    private static final PrintStream OUT =
            new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8);
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private Log() {
    }

    public static synchronized void log(String source, String event) {
        OUT.println("[" + LocalTime.now().format(TIME) + "] [" + source + "] " + event);
    }

    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String uah(long amount) {
        return String.format(Locale.US, "%,d", amount).replace(',', ' ') + " грн";
    }

    public static String money(BigDecimal value) {
        return String.format(Locale.US, "%,.2f", value).replace(',', ' ').replace('.', ',');
    }
}
