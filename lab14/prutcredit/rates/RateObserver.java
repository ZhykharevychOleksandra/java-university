package lab14.prutcredit.rates;
import java.math.BigDecimal;

public interface RateObserver {
    String getName();
    void onRateChanged(String pair, BigDecimal oldRate, BigDecimal newRate);
}