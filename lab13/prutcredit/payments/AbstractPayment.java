package lab13.prutcredit.payments;
import java.time.LocalDate;
import lab13.prutcredit.common.EventLog;
import lab13.prutcredit.common.Money;
import lab13.prutcredit.common.SimClock;

public abstract class AbstractPayment implements PaymentCommand {
    private static int counter = 0;

    private final int id;
    private final BankAccount source;
    private final BankAccount target;
    private final long amount;
    private final LocalDate scheduledDate;
    private final Repeat repeat;
    private PaymentState state = PaymentState.SCHEDULED;
    private LocalDate executedOn;

    protected AbstractPayment(BankAccount source, BankAccount target, long amount,
                              LocalDate scheduledDate, Repeat repeat) {
        this.id = ++counter;
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.scheduledDate = scheduledDate;
        this.repeat = repeat;
    }

    @Override
    public void execute() {
        requireState(PaymentState.SCHEDULED, "виконати");
        EventLog.log(label(), "виконується: " + describe());
        try {
            source.withdraw(amount);
            target.deposit(amount);
        } catch (IllegalStateException e) {
            state = PaymentState.FAILED;
            throw e;
        }
        state = PaymentState.EXECUTED;
        executedOn = SimClock.today();
        EventLog.log(label(), "виконано");
    }

    @Override
    public void reverse() {
        requireState(PaymentState.EXECUTED, "сторнувати");
        EventLog.log(label(), "сторнується: " + describe());
        target.withdraw(amount);
        source.deposit(amount);
        state = PaymentState.REVERSED;
        EventLog.log(label(), "сторновано");
    }

    @Override
    public void cancel() {
        requireState(PaymentState.SCHEDULED, "скасувати");
        state = PaymentState.CANCELLED;
        EventLog.log(label(), "скасовано до виконання");
    }

    @Override
    public boolean isReversibleOn(LocalDate day) {
        return state == PaymentState.EXECUTED && day.equals(executedOn);
    }

    @Override
    public PaymentCommand nextOccurrence() {
        LocalDate next = repeat.next(scheduledDate);
        return next == null ? null : copyFor(next);
    }

    protected abstract PaymentCommand copyFor(LocalDate date);

    protected abstract String kind();

    protected abstract String details();

    @Override
    public String label() {
        return getClass().getSimpleName() + "#" + id;
    }

    @Override
    public String describe() {
        return kind() + " " + details() + ", " + Money.format(amount) + ", " + scheduledDate
                + " (" + repeat.getLabel() + ")";
    }

    private void requireState(PaymentState expected, String action) {
        if (state != expected) {
            throw new IllegalStateException("не можна " + action + " платіж у стані «" + state.getLabel() + "»");
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    @Override
    public PaymentState getState() {
        return state;
    }

    public LocalDate getExecutedOn() {
        return executedOn;
    }

    protected BankAccount getSource() {
        return source;
    }

    protected BankAccount getTarget() {
        return target;
    }

    protected long getAmount() {
        return amount;
    }

    protected Repeat getRepeat() {
        return repeat;
    }
}