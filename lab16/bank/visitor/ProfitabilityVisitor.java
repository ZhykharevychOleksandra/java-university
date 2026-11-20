package lab16.bank.visitor;
import lab16.bank.common.Log;

public class ProfitabilityVisitor implements ProductVisitor {
    private static final String SRC = "Прибутковість";
    private double total;

    @Override 
    public void visit(Deposit d) {
        double v = -d.principal() * d.annualRate();
        total += v;
        Log.log(SRC, d.id() + " (депозит): виплата відсотків " + Log.uah(v));
    }

    @Override 
    public void visit(Loan l) {
        double v = l.outstanding() * l.annualRate();
        total += v;
        Log.log(SRC, l.id() + " (кредит): відсотковий дохід " + Log.uah(v));
    }

    @Override 
    public void visit(Card c) {
        double v = c.usedLimit() * c.annualRate();
        total += v;
        Log.log(SRC, c.id() + " (картка): відсотковий дохід " + Log.uah(v));
    }

    @Override 
    public void visit(InvestmentAccount a) {
        double v = a.marketValue() * a.managementFee();
        total += v;
        Log.log(SRC, a.id() + " (інвестрахунок): комісія за управління " + Log.uah(v));
    }

    public double getTotal() { 
        return total; 
    }
}