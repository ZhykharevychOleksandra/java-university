package lab14.prutcredit.rates;
import lab14.prutcredit.common.EventLog;
import java.math.BigDecimal;

public class NotifiedClient implements RateObserver {
    private final String name;
    private final BigDecimal threshold;

    public NotifiedClient(String name, BigDecimal threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    @Override public String getName() { return "Клієнт " + name; }

    @Override public void onRateChanged(String pair, BigDecimal oldRate, BigDecimal newRate) {
        if (newRate.compareTo(threshold) <= 0) {
            EventLog.log(getName(), "СПОВІЩЕННЯ: курс " + newRate + " досяг порогу " + threshold);
        } else {
            EventLog.log(getName(), "курс " + newRate + " вище порогу " + threshold + " → ігнорує");
        }
    }
}