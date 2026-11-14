package lab11.bank.decorator;
import java.math.BigDecimal;

public class LoungeAccessOption extends CardOption {
    public LoungeAccessOption(Card wrapped) {
        super(wrapped);
    }

    @Override
    protected String optionName() {
        return "доступ до бізнес-залів";
    }

    @Override
    protected BigDecimal optionCost() {
        return new BigDecimal("120.00");
    }
}