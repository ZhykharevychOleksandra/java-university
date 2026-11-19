package lab14.prutcredit.loan;
import lab14.prutcredit.common.EventLog;

public class Cashier extends LoanParticipant {
    public Cashier(LoanMediator mediator) { super("Касир", mediator); }

    public void issueFunds(LoanApplication app) {
        EventLog.log(name, "видає " + app.getAmount() + " грн клієнту " + app.getClientName());
        mediator.notify(this, LoanEvent.FUNDS_ISSUED, app, null);
    }
}