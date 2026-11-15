package lab13.prutcredit.approval;
import lab13.prutcredit.common.Money;

public class LoanApplication {
    private static int counter = 0;

    private final int id;
    private final String applicant;
    private final long amount;
    private LoanStatus status = LoanStatus.PENDING;
    private String decidedBy;

    public LoanApplication(String applicant, long amount) {
        this.id = ++counter;
        this.applicant = applicant;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getApplicant() {
        return applicant;
    }

    public long getAmount() {
        return amount;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public String getDecidedBy() {
        return decidedBy;
    }

    public void approve(String by) {
        this.status = LoanStatus.APPROVED;
        this.decidedBy = by;
    }

    public void reject(String by) {
        this.status = LoanStatus.REJECTED;
        this.decidedBy = by;
    }

    public void markUnprocessed() {
        this.status = LoanStatus.UNPROCESSED;
    }

    public String describe() {
        return "заявку №" + id + " (" + applicant + ", " + Money.format(amount) + ")";
    }

    public String result() {
        String who = decidedBy == null ? "" : " — " + decidedBy;
        return "заявка №" + id + ": " + status.getLabel() + who;
    }
}
