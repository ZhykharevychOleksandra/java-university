package lab13.prutcredit.payments;
import java.time.LocalDate;

public interface PaymentCommand {
    void execute();

    void reverse();

    void cancel();

    boolean isReversibleOn(LocalDate day);

    PaymentCommand nextOccurrence();

    int getId();

    LocalDate getScheduledDate();

    PaymentState getState();

    String label();

    String describe();
}