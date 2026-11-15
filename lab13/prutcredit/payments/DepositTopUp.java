package lab13.prutcredit.payments;
import java.time.LocalDate;

public class DepositTopUp extends AbstractPayment {

    public DepositTopUp(BankAccount source, BankAccount deposit, long amount,
                        LocalDate date, Repeat repeat) {
        super(source, deposit, amount, date, repeat);
    }

    @Override
    protected PaymentCommand copyFor(LocalDate date) {
        return new DepositTopUp(getSource(), getTarget(), getAmount(), date, getRepeat());
    }

    @Override
    protected String kind() {
        return "Поповнення депозиту";
    }

    @Override
    protected String details() {
        return "→ " + getTarget().getName();
    }
}