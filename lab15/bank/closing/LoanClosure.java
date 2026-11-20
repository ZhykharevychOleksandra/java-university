package lab15.bank.closing;
import lab15.bank.common.Client;
import lab15.bank.common.Log15;

public class LoanClosure extends ProductClosure {
    private final double remaining;

    public LoanClosure(double remaining) {
        super("Кредит");
        this.remaining = remaining;
    }

    @Override 
    protected String productName() { 
        return "Кредит"; 
    }

    @Override 
    protected void finalSettlement(Client c) {
        Log15.log("Кредит", "Етап 3а. Дострокове погашення залишку тіла кредиту: " + Log15.money(remaining));
    }
}