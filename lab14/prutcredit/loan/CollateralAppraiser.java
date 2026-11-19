package lab14.prutcredit.loan;
import lab14.prutcredit.common.EventLog;

public class CollateralAppraiser extends LoanParticipant implements Checker {
    public CollateralAppraiser(LoanMediator mediator) { super("Оцінювач застави", mediator); }

    @Override public void check(LoanApplication app) {
        boolean ok = app.getCollateralValue() >= app.getAmount();
        EventLog.log(name, "вартість застави " + app.getCollateralValue() + " грн → "
                + (ok ? "достатньо" : "недостатньо"));
        mediator.notify(this, ok ? LoanEvent.CHECK_PASSED : LoanEvent.CHECK_FAILED, app,
                ok ? null : "недостатня застава");
    }
}