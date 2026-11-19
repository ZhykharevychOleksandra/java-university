package lab14.prutcredit.loan;
import lab14.prutcredit.common.EventLog;

public class SecurityService extends LoanParticipant implements Checker {
    public SecurityService(LoanMediator mediator) { super("Служба безпеки", mediator); }

    @Override public void check(LoanApplication app) {
        boolean ok = !app.isSecurityFlagged();
        EventLog.log(name, ok ? "перевірка клієнта пройдена → позитивний висновок"
                              : "клієнт у списку ризику → ВІДМОВА");
        mediator.notify(this, ok ? LoanEvent.CHECK_PASSED : LoanEvent.CHECK_FAILED, app,
                ok ? null : "відмова служби безпеки");
    }
}