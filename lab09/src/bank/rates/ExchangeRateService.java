package bank.rates;
import bank.common.Log;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ExchangeRateService {
    private static final String SOURCE = "Служба курсів";

    private static boolean created;

    private final String id;
    private final Map<String, ExchangeRate> rates = new ConcurrentHashMap<>();

    private ExchangeRateService() {
        synchronized (ExchangeRateService.class) {
            if (created) {
                throw new IllegalStateException("Службу курсів валют уже створено");
            }
            created = true;
        }
        this.id = "RS-" + UUID.randomUUID().toString().substring(0, 8);
        Log.log(SOURCE, "Службу курсів валют ініціалізовано");
    }

    private static final class Holder {
        static final ExchangeRateService INSTANCE = new ExchangeRateService();
    }

    public static ExchangeRateService getInstance() {
        return Holder.INSTANCE;
    }

    public String getId() {
        return id;
    }

    public ExchangeRate getRate(String currency) {
        ExchangeRate rate = rates.get(currency);
        if (rate == null) {
            throw new IllegalStateException("Курс " + currency + " ще не встановлено");
        }
        return rate;
    }

    void setRate(String currency, BigDecimal buy, BigDecimal sell) {
        ExchangeRate rate = new ExchangeRate(currency, buy, sell);
        rates.put(currency, rate);
        Log.log(SOURCE, "Курс оновлено: " + rate);
    }
}
