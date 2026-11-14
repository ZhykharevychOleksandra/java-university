package lab11.bank.decorator;
import java.math.BigDecimal;

public class CashbackOption extends CardOption {
    public CashbackOption(Card wrapped) {
        super(wrapped);
    }

    @Override
    protected String optionName() {
        return "кешбек";
    }

    @Override
    protected BigDecimal optionCost() {
        return new BigDecimal("25.00");
    }
}