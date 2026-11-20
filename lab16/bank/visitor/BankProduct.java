package lab16.bank.visitor;

public interface BankProduct {
    String id();
    void accept(ProductVisitor visitor);
}