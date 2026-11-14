package lab11.bank.decorator;
import java.math.BigDecimal;

public class TravelInsuranceOption extends CardOption {
    public TravelInsuranceOption(Card wrapped) {
        super(wrapped);
    }

    @Override
    protected String optionName() {
        return "страхування подорожей";
    }

    @Override
    protected BigDecimal optionCost() {
        return new BigDecimal("60.00");
    }
}