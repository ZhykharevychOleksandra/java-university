package lab14.prutcredit.rates;
import lab14.prutcredit.common.EventLog;
import java.math.BigDecimal;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExchangeRateService implements RateSubject {
    private static final String NAME = "Служба курсів";

    private final String pair;
    private BigDecimal rate;
    private final CopyOnWriteArrayList<RateObserver> observers = new CopyOnWriteArrayList<>(); // безпечна відписка під час розсилки

    public ExchangeRateService(String pair, BigDecimal initialRate) {
        this.pair = pair;
        this.rate = initialRate;
    }

    @Override public void subscribe(RateObserver o) {
        if (observers.addIfAbsent(o)) EventLog.log(NAME, "підписано: " + o.getName());
    }

    @Override public void unsubscribe(RateObserver o) {
        if (observers.remove(o)) EventLog.log(NAME, "відписано: " + o.getName());
    }

    public void setRate(BigDecimal newRate) {
        BigDecimal old = rate;
        rate = newRate;
        EventLog.log(NAME, "курс " + pair + " змінено: " + old + " → " + newRate
                + "; сповіщає підписників: " + observers.size());
        for (RateObserver o : observers) o.onRateChanged(pair, old, newRate);
    }

    public BigDecimal getRate() { return rate; }
}