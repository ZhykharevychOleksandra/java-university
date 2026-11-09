package bank.rates;
import bank.common.Log;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class ExchangeRate {
    private final String currency;
    private final BigDecimal buy;
    private final BigDecimal sell;
    private final LocalDateTime setAt;

    ExchangeRate(String currency, BigDecimal buy, BigDecimal sell) {
        this.currency = currency;
        this.buy = buy;
        this.sell = sell;
        this.setAt = LocalDateTime.now();
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public LocalDateTime getSetAt() {
        return setAt;
    }

    @Override
    public String toString() {
        return currency + " " + Log.money(buy) + " / " + Log.money(sell);
    }
}
