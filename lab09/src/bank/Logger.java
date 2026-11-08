package lab09.src.bank;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void log(String source, String message) {
        String time = LocalTime.now().format(FORMAT);

        System.out.println(
                "[" + time + "] [" + source + "] " + message
        );
    }
}