package lab16.bank.visitor;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private final String clientName;
    private final List<BankProduct> products = new ArrayList<>();

    public Portfolio(String clientName) { 
        this.clientName = clientName; 
    }

    public Portfolio add(BankProduct p) { 
        products.add(p); 
        return this; 
    }

    public String getClientName() { 
        return clientName; 
    }

    public void accept(ProductVisitor visitor) {
        for (BankProduct p : products) p.accept(visitor);
    }

    public String describe() {
        StringBuilder sb = new StringBuilder();
        for (BankProduct p : products) sb.append(p).append("; ");
        return sb.toString().trim();
    }
}