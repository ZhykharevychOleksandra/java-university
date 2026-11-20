package lab15.bank.closing;
import lab15.bank.common.Client;
import lab15.bank.common.Log15;

public class DepositClosure extends ProductClosure {
    private static final double TAX_RATE = 0.23; 
    private final double amount, annualRate;
    private final int months;
    private double interest;

    public DepositClosure(double amount, double annualRate, int months) {
        super("Депозит");
        this.amount = amount; 
        this.annualRate = annualRate; 
        this.months = months;
    }

    @Override 
    protected String productName() { 
        return "Депозит"; 
    }

    @Override 
    protected void finalSettlement(Client c) {
        interest = amount * annualRate * months / 12;
        Log15.log("Депозит", "Етап 3а. Нарахування відсотків: " + Log15.money(interest)
                + "; повернення тіла " + Log15.money(amount));
    }

    @Override 
    protected void withholdTax(Client c) {
        double tax = interest * TAX_RATE;
        Log15.log("Депозит", "Етап 3б. Утримання податку " + (int) (TAX_RATE * 100) + "%: "
                + Log15.money(tax) + "; клієнту до виплати " + Log15.money(amount + interest - tax));
    }
}