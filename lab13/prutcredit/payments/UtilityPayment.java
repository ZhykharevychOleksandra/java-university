package lab13.prutcredit.payments;
import java.time.LocalDate;

public class UtilityPayment extends AbstractPayment {
    private final String service;
    private final String personalAccount;

    public UtilityPayment(BankAccount source, BankAccount provider, String service,
                          String personalAccount, long amount, LocalDate date, Repeat repeat) {
        super(source, provider, amount, date, repeat);
        this.service = service;
        this.personalAccount = personalAccount;
    }

    @Override
    protected PaymentCommand copyFor(LocalDate date) {
        return new UtilityPayment(getSource(), getTarget(), service, personalAccount,
                getAmount(), date, getRepeat());
    }

    @Override
    protected String kind() {
        return "Комунальний платіж";
    }

    @Override
    protected String details() {
        return service + " (о/р " + personalAccount + ") → " + getTarget().getName();
    }
}