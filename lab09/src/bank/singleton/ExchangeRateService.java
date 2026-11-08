package lab09.src.bank.singleton;
import lab09.src.bank.Logger;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRateService {

    private static class Holder {
        private static final ExchangeRateService INSTANCE =
                new ExchangeRateService();
    }

    private final Map<String, ExchangeRate> rates =
            new HashMap<>();

    private ExchangeRateService() {
        Logger.log("ExchangeRateService",
                "Службу курсів валют ініціалізовано");
    }

    public static ExchangeRateService getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setRate(
            String currency,
            double buy,
            double sell
    ) {
        rates.put(currency,
                new ExchangeRate(currency, buy, sell));

        Logger.log("Казначейство",
                "Встановлено курс: " + rates.get(currency));
    }

    public synchronized ExchangeRate getRate(String currency) {
        return rates.get(currency);
    }
}