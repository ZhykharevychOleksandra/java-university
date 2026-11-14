package lab11.bank.adapter;
import java.math.BigDecimal;

public record Payment(String sender, String receiver, BigDecimal amount, String currency) {
}