package lab14.prutcredit.rates;
import lab14.prutcredit.common.EventLog;
import java.math.BigDecimal;

public class OnlineBanking implements RateObserver {
    @Override public String getName() { return "Онлайн-банкінг"; }
    @Override public void onRateChanged(String pair, BigDecimal oldRate, BigDecimal newRate) {
        EventLog.log(getName(), "оновлено курс на сторінці: " + pair + " = " + newRate);
    }
}