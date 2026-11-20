package lab16.bank.visitor;

public record InvestmentAccount(String id, double marketValue, double managementFee) implements BankProduct {
    @Override 
    public void accept(ProductVisitor v) { 
        v.visit(this); 
    }
}