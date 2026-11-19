package lab14.prutcredit.rates;
import lab14.prutcredit.common.EventLog;
import java.math.BigDecimal;

public class BranchBoard implements RateObserver {
    @Override public String getName() { return "Табло у відділенні"; }
    @Override public void onRateChanged(String pair, BigDecimal oldRate, BigDecimal newRate) {
        EventLog.log(getName(), "оновлено, показує " + pair + " = " + newRate);
    }
}