package lab11.bank.decorator;
import bank.common.Log;
import java.math.BigDecimal;

public class BasicCard implements Card {
    private static final BigDecimal BASE_COST = new BigDecimal("50.00");
    private final String holder;

    public BasicCard(String holder) {
        this.holder = holder;
    }

    @Override
    public String getDescription() {
        return "Базова картка (" + holder + ")";
    }

    @Override
    public BigDecimal getMonthlyCost() {
        Log.log("BasicCard", "базова вартість обслуговування " + BASE_COST + " грн");
        return BASE_COST;
    }
}