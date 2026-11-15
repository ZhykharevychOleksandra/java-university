package lab13.prutcredit.payments;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lab13.prutcredit.common.EventLog;
import lab13.prutcredit.common.SimClock;

public class PaymentScheduler {
    private static final String SOURCE = "Планувальник платежів";
    private final List<PaymentCommand> queue = new ArrayList<>();
    private final List<PaymentCommand> executed = new ArrayList<>();
    private final PaymentJournal journal = new PaymentJournal();

    public PaymentJournal getJournal() {
        return journal;
    }

    public void schedule(PaymentCommand payment) {
        if (!payment.getScheduledDate().isAfter(SimClock.today())) {
            throw new IllegalArgumentException("Платіж відкладений: дата має бути пізніше за "
                    + SimClock.today());
        }
        queue.add(payment);
        queue.sort(Comparator.comparing(PaymentCommand::getScheduledDate).thenComparingInt(PaymentCommand::getId));
        note(payment, "СТВОРЕНО", "поставлено в чергу: " + payment.describe());
    }

    public void cancel(int paymentId) {
        PaymentCommand payment = find(queue, paymentId);
        if (payment == null) {
            EventLog.log(SOURCE, "платежу #" + paymentId + " немає в черзі (виконаний платіж можна лише сторнувати)");
            journal.record(paymentId, "ВІДМОВА", "скасування неможливе: платежу немає в черзі");
            return;
        }
        EventLog.log(SOURCE, "отримав запит на скасування " + payment.label());
        payment.cancel();
        queue.remove(payment);
        journal.record(paymentId, "СКАСОВАНО", payment.describe());
    }

    public void reverse(int paymentId) {
        PaymentCommand payment = find(executed, paymentId);
        if (payment == null) {
            EventLog.log(SOURCE, "виконаного платежу #" + paymentId + " не знайдено");
            journal.record(paymentId, "ВІДМОВА", "сторно неможливе: платіж не виконаний");
            return;
        }
        EventLog.log(SOURCE, "отримав запит на сторно " + payment.label());
        if (!payment.isReversibleOn(SimClock.today())) {
            EventLog.log(SOURCE, "сторно " + payment.label()
                    + " відхилено: операційний день виконання вже закрито");
            journal.record(paymentId, "ВІДМОВА", "сторно можливе лише протягом операційного дня виконання");
            return;
        }
        try {
            payment.reverse();
            journal.record(paymentId, "СТОРНОВАНО", payment.describe());
        } catch (IllegalStateException e) {
            EventLog.log(SOURCE, "сторно " + payment.label() + " не вдалося: " + e.getMessage());
            journal.record(paymentId, "ПОМИЛКА", "сторно: " + e.getMessage());
        }
    }

    public void advanceTo(LocalDate target) {
        while (SimClock.today().isBefore(target)) {
            SimClock.startOfDay(SimClock.today().plusDays(1));
            EventLog.log(SOURCE, "настав операційний день " + SimClock.today());
            runDue();
        }
    }

    public void printQueue() {
        EventLog.log(SOURCE, "черга платежів (" + queue.size() + "):");
        for (PaymentCommand p : queue) {
            EventLog.log("Черга", p.label() + " | " + p.getScheduledDate() + " | " + p.describe());
        }
    }

    private void runDue() {
        List<PaymentCommand> due = queue.stream()
                .filter(p -> !p.getScheduledDate().isAfter(SimClock.today()))
                .toList();
        for (PaymentCommand payment : due) {
            queue.remove(payment);
            EventLog.log(SOURCE, "настав термін " + payment.label() + " → передає на виконання");
            try {
                payment.execute();
                executed.add(payment);
                journal.record(payment.getId(), "ВИКОНАНО", payment.describe());
                PaymentCommand next = payment.nextOccurrence();
                if (next != null) {
                    EventLog.log(SOURCE, "регулярний платіж: планується наступне виконання");
                    schedule(next);
                }
            } catch (IllegalStateException e) {
                EventLog.log(SOURCE, payment.label() + " не виконано: " + e.getMessage());
                journal.record(payment.getId(), "ПОМИЛКА", e.getMessage());
            }
        }
    }

    private void note(PaymentCommand payment, String operation, String text) {
        EventLog.log(SOURCE, "платіж #" + payment.getId() + ": " + text);
        journal.record(payment.getId(), operation, text);
    }

    private static PaymentCommand find(List<PaymentCommand> list, int id) {
        return list.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
