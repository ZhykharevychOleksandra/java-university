package lab13.prutcredit.payments;
import java.time.LocalDateTime;

public record JournalEntry(LocalDateTime time, int paymentId, String operation, String text) {
}
