package lab09.src.bank.singleton;

public class ExchangeRate {

    private final String currency;
    private final double buy;
    private final double sell;

    public ExchangeRate(String currency, double buy, double sell) {
        this.currency = currency;
        this.buy = buy;
        this.sell = sell;
    }

    @Override
    public String toString() {
        return currency +
                " | купівля: " + buy +
                " | продаж: " + sell;
    }
}