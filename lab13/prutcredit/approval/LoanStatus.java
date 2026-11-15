package lab13.prutcredit.approval;

public enum LoanStatus {
    PENDING("очікує розгляду"),
    APPROVED("погоджено"),
    REJECTED("відхилено"),
    UNPROCESSED("не оброблено жодним рівнем");

    private final String label;

    LoanStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}