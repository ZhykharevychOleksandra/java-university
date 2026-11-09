package bank.rates;
import bank.common.Log;

public final class Branch {
    private final int number;

    public Branch(int number) {
        this.number = number;
    }

    public String showRate(String currency) {
        ExchangeRateService service = ExchangeRateService.getInstance();
        Log.log("Відділення №" + number, service.getRate(currency) + " (служба " + service.getId() + ")");
        return service.getId();
    }
}