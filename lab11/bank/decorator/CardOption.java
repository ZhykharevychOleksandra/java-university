package lab11.bank.decorator;
import bank.common.Log;
import java.math.BigDecimal;

public abstract class CardOption implements Card {
    protected final Card wrapped;

    protected CardOption(Card wrapped) {
        this.wrapped = wrapped;
    }

    protected abstract String optionName();

    protected abstract BigDecimal optionCost();

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + " + optionName();
    }

    @Override
    public BigDecimal getMonthlyCost() {
        BigDecimal inner = wrapped.getMonthlyCost();
        BigDecimal total = inner.add(optionCost());
        Log.log(getClass().getSimpleName(), "опція \"" + optionName() + "\": " + inner + " + " + optionCost()
                + " = " + total + " грн");
        return total;
    }
}