package lab11.bank.decorator;
import java.math.BigDecimal;

public interface Card {
    String getDescription();

    BigDecimal getMonthlyCost();
}