package lab13.prutcredit.approval;
import lab13.prutcredit.common.EventLog;

public abstract class ApprovalHandler {
    private final String name;
    private ApprovalHandler next;

    protected ApprovalHandler(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ApprovalHandler getNext() {
        return next;
    }

    public void setNext(ApprovalHandler next) {
        this.next = next;
    }

    public final void handle(LoanApplication application) {
        log("отримав " + application.describe());
        Decision decision = decide(application);
        switch (decision) {
            case APPROVE -> {
                application.approve(name);
                log("ПОГОДИВ " + application.describe());
            }
            case REJECT -> {
                application.reject(name);
                log("ВІДХИЛИВ " + application.describe());
            }
            case FORWARD -> {
                if (next == null) {
                    log("наступного рівня немає — заявку не оброблено");
                } else {
                    log("передає заявку далі → " + next.getName());
                    next.handle(application);
                }
            }
        }
    }

    protected abstract Decision decide(LoanApplication application);

    protected void log(String event) {
        EventLog.log(name, event);
    }
}
