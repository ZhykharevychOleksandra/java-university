package lab13.prutcredit.approval;
import lab13.prutcredit.common.Money;

public class LimitApprover extends ApprovalHandler {
    public static final long UNLIMITED = Long.MAX_VALUE;

    private long limit;

    public LimitApprover(String name, long limit) {
        super(name);
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    @Override
    protected Decision decide(LoanApplication application) {
        if (application.getAmount() <= limit) {
            return Decision.APPROVE;
        }
        log("сума " + Money.format(application.getAmount()) + " перевищує мій ліміт "
                + Money.format(limit));
        return Decision.FORWARD;
    }
}
