package lab14.prutcredit.loan;
import lab14.prutcredit.common.EventLog;

public class LoanManager extends LoanParticipant {
    public LoanManager(LoanMediator mediator) { super("Кредитний менеджер", mediator); }

    public void submit(LoanApplication app) {
        EventLog.log(name, "подає заявку " + app);
        mediator.notify(this, LoanEvent.APPLICATION_SUBMITTED, app, null);
    }

    public void onRejected(LoanApplication app, String reason) {
        EventLog.log(name, "отримав повідомлення: заявка " + app + " відхилена. Причина: " + reason);
    }

    public void onIssued(LoanApplication app) {
        EventLog.log(name, "отримав повідомлення: за заявкою " + app + " кошти видано");
    }
}