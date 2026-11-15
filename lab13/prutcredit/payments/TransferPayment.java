package lab13.prutcredit.payments;
import java.time.LocalDate;

public class TransferPayment extends AbstractPayment {
    private final String purpose;

    public TransferPayment(BankAccount source, BankAccount recipient, long amount,
                           LocalDate date, Repeat repeat, String purpose) {
        super(source, recipient, amount, date, repeat);
        this.purpose = purpose;
    }

    @Override
    protected PaymentCommand copyFor(LocalDate date) {
        return new TransferPayment(getSource(), getTarget(), getAmount(), date, getRepeat(), purpose);
    }

    @Override
    protected String kind() {
        return "Переказ";
    }

    @Override
    protected String details() {
        return "→ " + getTarget().getName() + " («" + purpose + "»)";
    }
}