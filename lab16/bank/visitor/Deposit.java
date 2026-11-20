package lab16.bank.visitor;

public record Deposit(String id, double principal, double annualRate) implements BankProduct {
    @Override 
    public void accept(ProductVisitor v) { 
        v.visit(this); 
    }
}