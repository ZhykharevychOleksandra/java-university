package lab13.prutcredit.approval;
import java.util.function.Predicate;
import lab13.prutcredit.common.Money;

public class SecurityCheckHandler extends ApprovalHandler {
    private final long threshold;
    private final Predicate<LoanApplication> check;

    public SecurityCheckHandler(String name, long threshold, Predicate<LoanApplication> check) {
        super(name);
        this.threshold = threshold;
        this.check = check;
    }

    @Override
    protected Decision decide(LoanApplication application) {
        if (application.getAmount() <= threshold) {
            log("сума не перевищує " + Money.format(threshold) + " — перевірка не потрібна");
            return Decision.FORWARD;
        }
        if (check.test(application)) {
            log("перевірку заявника «" + application.getApplicant() + "» пройдено");
            return Decision.FORWARD;
        }
        log("перевірку заявника «" + application.getApplicant() + "» НЕ пройдено");
        return Decision.REJECT;
    }
}
