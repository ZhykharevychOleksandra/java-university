package lab16.bank.visitor;

public interface ProductVisitor {
    void visit(Deposit deposit);
    void visit(Loan loan);
    void visit(Card card);
    void visit(InvestmentAccount account);
}