package lab15.bank.closing;
import lab15.bank.common.Client;
import lab15.bank.common.Log15;

public class CardClosure extends ProductClosure {
    private final double balance;

    public CardClosure(double balance) {
        super("Картка");
        this.balance = balance;
    }

    @Override 
    protected String productName() { 
        return "Картка"; 
    }

    @Override 
    protected void finalSettlement(Client c) {
        Log15.log("Картка", "Етап 3а. Повернення залишку на рахунку клієнту: " + Log15.money(balance));
    }

    @Override 
    protected void closeProduct(Client c) {
        Log15.log("Картка", "Етап 4. Картку заблоковано та знищено в процесингу");
    }
}