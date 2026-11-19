package lab14.prutcredit.loan;
import lab14.prutcredit.common.EventLog;
import java.util.*;

public class LoanCoordinator implements LoanMediator {
    private static final String NAME = "Координатор";

    private final List<Checker> checks = new ArrayList<>();
    private final Map<Integer, Set<Checker>> pending = new HashMap<>();
    private LoanManager manager;
    private LegalDepartment legal;
    private Cashier cashier;

    public void setManager(LoanManager manager) { this.manager = manager; }
    public void setLegal(LegalDepartment legal) { this.legal = legal; }
    public void setCashier(Cashier cashier) { this.cashier = cashier; }

    public void addCheck(Checker checker) { checks.add(checker); }

    @Override
    public void notify(LoanParticipant sender, LoanEvent event, LoanApplication app, String details) {
        switch (event) {
            case APPLICATION_SUBMITTED -> startChecks(app);
            case CHECK_PASSED -> onCheckPassed(sender, app);
            case CHECK_FAILED -> onCheckFailed(sender, app, details);
            case CONTRACT_SIGNED -> {
                EventLog.log(NAME, "договір підписано → передає заявку " + app + " касиру");
                cashier.issueFunds(app);
            }
            case FUNDS_ISSUED -> {
                app.setStatus(LoanStatus.ISSUED);
                EventLog.log(NAME, "кошти видано → повідомляє менеджера, процес завершено");
                manager.onIssued(app);
            }
        }
    }

    private void startChecks(LoanApplication app) {
        app.setStatus(LoanStatus.IN_REVIEW);
        pending.put(app.getId(), new HashSet<>(checks));
        EventLog.log(NAME, "отримав заявку " + app + " → запускає перевірки (" + checks.size() + ")");
        for (Checker c : new ArrayList<>(checks)) {
            if (app.getStatus() != LoanStatus.IN_REVIEW) break; // процес уже зупинено
            c.check(app);
        }
    }

    private void onCheckPassed(LoanParticipant sender, LoanApplication app) {
        if (app.getStatus() != LoanStatus.IN_REVIEW) return;
        Set<Checker> left = pending.get(app.getId());
        left.remove((Checker) sender);
        if (left.isEmpty()) {
            app.setStatus(LoanStatus.CONTRACT_STAGE);
            EventLog.log(NAME, "усі висновки позитивні → передає заявку " + app + " юристам");
            legal.prepareContract(app);
        } else {
            EventLog.log(NAME, sender.getName() + ": позитивно; очікує ще " + left.size());
        }
    }

    private void onCheckFailed(LoanParticipant sender, LoanApplication app, String reason) {
        if (app.getStatus() == LoanStatus.REJECTED) return;
        app.setStatus(LoanStatus.REJECTED);
        EventLog.log(NAME, sender.getName() + " відмовила/відмовив → зупиняє процес, повідомляє менеджера");
        manager.onRejected(app, reason);
    }
}