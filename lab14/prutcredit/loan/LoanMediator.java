package lab14.prutcredit.loan;

public interface LoanMediator {
    void notify(LoanParticipant sender, LoanEvent event, LoanApplication app, String details);
}