package lab13.prutcredit.common;
import java.util.Locale;

public final class Money {
    private Money() {
    }

    public static String format(long amount) {
        return String.format(Locale.ROOT, "%,d", amount).replace(',', ' ') + " грн";
    }
}
