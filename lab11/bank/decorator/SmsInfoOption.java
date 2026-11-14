package lab11.bank.decorator;
import java.math.BigDecimal;

public class SmsInfoOption extends CardOption {
    public SmsInfoOption(Card wrapped) {
        super(wrapped);
    }

    @Override
    protected String optionName() {
        return "SMS-інформування";
    }

    @Override
    protected BigDecimal optionCost() {
        return new BigDecimal("15.00");
    }
}