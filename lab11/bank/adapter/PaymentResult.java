package lab11.bank.adapter;

public record PaymentResult(boolean accepted, String reason) {
    public static PaymentResult ok() {
        return new PaymentResult(true, "платіж прийнято");
    }

    public static PaymentResult rejected(String reason) {
        return new PaymentResult(false, reason);
    }

    @Override
    public String toString() {
        return (accepted ? "ПРИЙНЯТО" : "ВІДХИЛЕНО") + " (" + reason + ")";
    }
}