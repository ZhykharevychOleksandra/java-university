package lab13.prutcredit.payments;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lab13.prutcredit.common.SimClock;

public class PaymentJournal {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final List<JournalEntry> entries = new ArrayList<>();

    public void record(int paymentId, String operation, String text) {
        entries.add(new JournalEntry(SimClock.now(), paymentId, operation, text));
    }

    public List<JournalEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public void print() {
        for (JournalEntry e : entries) {
            System.out.println("  " + e.time().format(FORMAT) + " | #" + e.paymentId() + " | "
                    + e.operation() + " | " + e.text());
        }
    }
}