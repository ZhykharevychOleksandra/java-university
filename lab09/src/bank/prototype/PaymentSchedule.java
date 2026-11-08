package lab09.src.bank.prototype;
import java.util.ArrayList;
import java.util.List;

public class PaymentSchedule {

    private List<String> payments;

    public PaymentSchedule(List<String> payments) {
        this.payments = new ArrayList<>(payments);
    }

    public PaymentSchedule copy() {
        return new PaymentSchedule(
                new ArrayList<>(payments));
    }

    public void changePayment(int index, String value) {
        payments.set(index, value);
    }

    @Override
    public String toString() {
        return payments.toString();
    }
}