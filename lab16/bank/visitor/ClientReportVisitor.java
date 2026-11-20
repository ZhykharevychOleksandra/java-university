package lab16.bank.visitor;
import lab16.bank.common.Log;

public class ClientReportVisitor implements ProductVisitor {
    private static final String SRC = "Звіт клієнта";
    private final StringBuilder report = new StringBuilder();

    @Override 
    public void visit(Deposit d) {
        line(d.id() + ": депозит " + Log.uah(d.principal()) + " під " + pct(d.annualRate()));
    }

    @Override 
    public void visit(Loan l) {
        line(l.id() + ": кредит, залишок боргу " + Log.uah(l.outstanding())
                + (l.overdueDays() > 0 ? ", ПРОСТРОЧЕНО " + l.overdueDays() + " дн." : ", без прострочення"));
    }

    @Override 
    public void visit(Card c) {
        line(c.id() + ": картка, доступно " + Log.uah(c.creditLimit() - c.usedLimit())
                + " із ліміту " + Log.uah(c.creditLimit()));
    }

    @Override 
    public void visit(InvestmentAccount a) {
        line(a.id() + ": інвестрахунок, ринкова вартість " + Log.uah(a.marketValue()));
    }

    private void line(String s) {
        report.append(" - ").append(s).append('\n');
        Log.log(SRC, s);
    }

    private static String pct(double r) { 
        return String.format(java.util.Locale.US, "%.1f%%", r * 100); 
    }

    public String getReport() { 
        return report.toString(); 
    }
}