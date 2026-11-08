package lab10.src.bank.statements;
import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(LocalDate date, String description, BigDecimal amount) {
}
