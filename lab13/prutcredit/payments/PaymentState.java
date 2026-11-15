package lab13.prutcredit.payments;

public enum PaymentState {
    SCHEDULED("у черзі"),
    EXECUTED("виконано"),
    CANCELLED("скасовано"),
    REVERSED("сторновано"),
    FAILED("помилка виконання");

    private final String label;

    PaymentState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}