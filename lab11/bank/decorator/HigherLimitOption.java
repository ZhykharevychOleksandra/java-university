package lab11.bank.decorator;
import java.math.BigDecimal;

public class HigherLimitOption extends CardOption {
    public HigherLimitOption(Card wrapped) {
        super(wrapped);
    }

    @Override
    protected String optionName() {
        return "підвищений ліміт";
    }

    @Override
    protected BigDecimal optionCost() {
        return new BigDecimal("40.00");
    }
}