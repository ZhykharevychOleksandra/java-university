package lab14.prutcredit.loan;

public abstract class LoanParticipant {
    protected final String name;
    protected final LoanMediator mediator;

    protected LoanParticipant(String name, LoanMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() { return name; }
}