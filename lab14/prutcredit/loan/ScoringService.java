package lab14.prutcredit.loan;
import lab14.prutcredit.common.EventLog;

public class ScoringService extends LoanParticipant implements Checker {
    private static final int MIN_SCORE = 600;

    public ScoringService(LoanMediator mediator) { super("Скоринг", mediator); }

    @Override public void check(LoanApplication app) {
        boolean ok = app.getCreditScore() >= MIN_SCORE;
        EventLog.log(name, "оцінка " + app.getCreditScore() + " (мінімум " + MIN_SCORE + ") → "
                + (ok ? "позитивний висновок" : "негативний висновок"));
        mediator.notify(this, ok ? LoanEvent.CHECK_PASSED : LoanEvent.CHECK_FAILED, app,
                ok ? null : "низький скоринговий бал");
    }
}