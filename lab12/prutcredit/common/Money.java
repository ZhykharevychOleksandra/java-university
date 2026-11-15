package lab12.prutcredit.common;
import java.util.Locale;

public final class Money {

    private Money() {
    }

    public static String uah(long amount) {
        return String.format(Locale.US, "%,d", amount).replace(',', ' ');
    }

    public static String kopecks(long kopecks) {
        return String.format(Locale.US, "%,d.%02d", kopecks / 100, kopecks % 100).replace(',', ' ');
    }
}