package lab13.prutcredit.payments;
import java.time.LocalDate;

public enum Repeat {
    NONE("одноразовий"),
    WEEKLY("щотижня"),
    MONTHLY("щомісяця");

    private final String label;

    Repeat(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public LocalDate next(LocalDate from) {
        return switch (this) {
            case NONE -> null;
            case WEEKLY -> from.plusWeeks(1);
            case MONTHLY -> from.plusMonths(1);
        };
    }
}