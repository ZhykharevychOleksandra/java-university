package lab16.bank.visitor;
import lab16.bank.common.Log;

public class ReserveVisitor implements ProductVisitor {
    private static final String SRC = "Резерви НБУ";
    private double total;

    @Override 
    public void visit(Deposit d) {
        Log.log(SRC, d.id() + " (депозит): зобов'язання банку, резерв 0");
    }

    @Override 
    public void visit(Loan l) {
        double rate = l.overdueDays() == 0 ? 0.02 : l.overdueDays() <= 30 ? 0.05
                : l.overdueDays() <= 90 ? 0.20 : 1.00;
        double reserve = l.outstanding() * rate;
        total += reserve;
        Log.log(SRC, l.id() + " (кредит): прострочення " + l.overdueDays() + " дн., ставка резерву "
                + (int) (rate * 100) + "% -> " + Log.uah(reserve));
    }

    @Override 
    public void visit(Card c) {
        double reserve = c.usedLimit() * 0.03;
        total += reserve;
        Log.log(SRC, c.id() + " (картка): використано " + Log.uah(c.usedLimit()) + ", резерв 3% -> " + Log.uah(reserve));
    }

    @Override 
    public void visit(InvestmentAccount a) {
        Log.log(SRC, a.id() + " (інвестрахунок): активи клієнта, резерв 0");
    }

    public double getTotal() { 
        return total; 
    }
}