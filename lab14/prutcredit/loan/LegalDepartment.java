package lab14.prutcredit.loan;
import lab14.prutcredit.common.EventLog;

public class LegalDepartment extends LoanParticipant {
    public LegalDepartment(LoanMediator mediator) { super("Юридичний відділ", mediator); }

    public void prepareContract(LoanApplication app) {
        EventLog.log(name, "готує договір для заявки " + app);
        EventLog.log(name, "договір підписано клієнтом");
        mediator.notify(this, LoanEvent.CONTRACT_SIGNED, app, null);
    }
}