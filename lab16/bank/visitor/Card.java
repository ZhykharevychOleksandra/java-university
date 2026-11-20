package lab16.bank.visitor;

public record Card(String id, double creditLimit, double usedLimit, double annualRate) implements BankProduct {
    @Override 
    public void accept(ProductVisitor v) { 
        v.visit(this); 
    }
}