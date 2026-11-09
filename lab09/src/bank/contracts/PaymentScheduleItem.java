package bank.contracts;
import java.math.BigDecimal;

public final class PaymentScheduleItem {
    private final int month;
    private BigDecimal payoutPercent;

    public PaymentScheduleItem(int month, BigDecimal payoutPercent) {
        this.month = month;
        this.payoutPercent = payoutPercent;
    }

    /** Конструктор копіювання. */
    PaymentScheduleItem(PaymentScheduleItem source) {
        this(source.month, source.payoutPercent);
    }

    public int getMonth() {
        return month;
    }

    public BigDecimal getPayoutPercent() {
        return payoutPercent;
    }

    public void setPayoutPercent(BigDecimal payoutPercent) {
        this.payoutPercent = payoutPercent;
    }
}