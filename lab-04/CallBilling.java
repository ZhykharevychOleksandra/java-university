import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// завдання 4 
public final class CallBilling {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private CallBilling() { }

    public static void run(LocalDateTime start, LocalDateTime end, BigDecimal pricePerMinute) {
        if (!end.isAfter(start)) {
            System.out.println("Помилка: час закінчення має бути пізніше за час початку.");
            return;
        }

        Duration duration = Duration.between(start, end);
        long totalSeconds = duration.getSeconds();
        BigDecimal cost = pricePerMinute
                .multiply(BigDecimal.valueOf(totalSeconds))
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        System.out.println("Початок дзвінка:   " + start.format(FORMAT));
        System.out.println("Кінець дзвінка:    " + end.format(FORMAT));
        System.out.println("Тривалість:        " + duration + " (" + totalSeconds + " с)");
        System.out.println("Тариф:             " + pricePerMinute + " грн/хв");
        System.out.println("Знято з рахунку:   " + cost + " грн");
    }

    public static void runInteractive() {
        Scanner in = new Scanner(System.in);
        LocalDateTime start = readDate(in, "Введіть дату і час початку дзвінка (напр. 18.10.2026 09:15:30): ");
        LocalDateTime end = readDate(in, "Введіть дату і час закінчення дзвінка (напр. 18.10.2026 09:22:45): ");
        BigDecimal price = readPrice(in, "Введіть вартість однієї хвилини (грн): ");
        System.out.println();
        run(start, end, price);
    }

    private static LocalDateTime readDate(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDateTime.parse(in.nextLine().trim(), FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Некоректний формат, спробуйте ще раз.");
            }
        }
    }

    private static BigDecimal readPrice(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                BigDecimal value = new BigDecimal(in.nextLine().trim().replace(',', '.'));
                if (value.signum() >= 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) { }
            System.out.println("Некоректне число, спробуйте ще раз.");
        }
    }
}