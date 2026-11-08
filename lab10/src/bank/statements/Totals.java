package lab10.src.bank.statements;
import java.math.BigDecimal;

// підсумки виписки за період
public record Totals(int count, BigDecimal income, BigDecimal expenses) {
    public BigDecimal net() {
        return income.add(expenses);
    }
}