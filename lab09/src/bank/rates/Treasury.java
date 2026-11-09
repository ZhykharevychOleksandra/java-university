package bank.rates;
import bank.common.Log;
import java.math.BigDecimal;

public final class Treasury {
    private static final String SOURCE = "Казначейство";

    public void setRate(String currency, BigDecimal buy, BigDecimal sell) {
        ExchangeRateService service = ExchangeRateService.getInstance();
        Log.log(SOURCE, "отримано службу курсів " + service.getId() + ", встановлюю " + currency
                + " " + Log.money(buy) + " / " + Log.money(sell));
        service.setRate(currency, buy, sell);
    }
}