package lab14.prutcredit.rates;
import lab14.prutcredit.common.EventLog;
import java.math.BigDecimal;

public class ConverterModule implements RateObserver {
    @Override public String getName() { return "Модуль конвертації"; }
    @Override public void onRateChanged(String pair, BigDecimal oldRate, BigDecimal newRate) {
        EventLog.log(getName(), "перераховано коефіцієнт конвертації за курсом " + newRate);
    }
}