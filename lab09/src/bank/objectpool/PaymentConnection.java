package lab09.src.bank.objectpool;
import lab09.src.bank.Logger;

public class PaymentConnection {

    private final String id;
    private Payment currentPayment;
    private int paymentsProcessed = 0;

    public PaymentConnection(String id) {
        this.id = id;

        Logger.log("PaymentConnection",
                "Встановлення захищеного з'єднання " + id);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }

        Logger.log("PaymentConnection",
                id + ": сертифікати перевірено, " +
                "автентифікацію виконано, ключі узгоджено");
    }

    public void process(Payment payment) {
        currentPayment = payment;
        paymentsProcessed++;

        Logger.log(id,
                "Платіж №" + payment.getId() +
                ", " + payment.getAmount() + " грн" +
                ", рахунок " + payment.getFromAccount() +
                " → " + payment.getToAccount());

        Logger.log(id,
                "Платіж №" + payment.getId() +
                " успішно проведено");
    }

    public void clear() {
        currentPayment = null;

        Logger.log(id,
                "Повернено, дані платежу очищено");
    }

    public String getId() {
        return id;
    }

    public int getPaymentsProcessed() {
        return paymentsProcessed;
    }
}