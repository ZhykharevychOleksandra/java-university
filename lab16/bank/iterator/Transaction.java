package lab16.bank.iterator;
import lab16.bank.common.Log;
import java.time.LocalDate;

public record Transaction(String id, String accountNo, LocalDate date, long amountUah, String description) {
    @Override
    public String toString() {
        return id + " " + date + " " + accountNo + " " + Log.uah(amountUah) + " (" + description + ")";
    }
}