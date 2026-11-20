package lab16.bank.visitor;

public record Loan(String id, double outstanding, double annualRate, int overdueDays) implements BankProduct {
    @Override public void accept(ProductVisitor v) { v.visit(this); }
}