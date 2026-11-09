package bank.rates;
import bank.common.Log;

public final class OnlineBanking {
    public String showRate(String currency) {
        ExchangeRateService service = ExchangeRateService.getInstance();
        Log.log("Онлайн-банкінг", service.getRate(currency) + " (служба " + service.getId() + ")");
        return service.getId();
    }
}