package bank.payments;
import bank.common.Log;
import java.util.UUID;

public final class SecureConnection {
    private final String id;
    private String sessionId;
    private int paymentsHandled;

    private int paymentId;
    private String operationId;
    private long amount;
    private String fromAccount;
    private String toAccount;

    SecureConnection(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    String getSessionId() {
        return sessionId;
    }

    int getPaymentsHandled() {
        return paymentsHandled;
    }

    void establish() {
        Log.log("З'єднання " + id,
                "встановлення: обмін сертифікатами, автентифікація, узгодження ключів...");
        Log.pause(300);
        sessionId = "S-" + UUID.randomUUID().toString().substring(0, 6);
        Log.log("З'єднання " + id, "встановлено, сесія " + sessionId);
    }

    void assign(Payment payment) {
        paymentId = payment.getId();
        operationId = payment.getOperationId();
        amount = payment.getAmount();
        fromAccount = payment.getFromAccount();
        toAccount = payment.getToAccount();
        paymentsHandled++;
    }

    void execute() {
        Log.pause(150);
        Log.log("З'єднання " + id, "платіж №" + paymentId + " проведено (" + operationId + ", "
                + fromAccount + " → " + toAccount + ", " + Log.uah(amount) + ")");
    }

    void reset() {
        paymentId = 0;
        operationId = null;
        amount = 0;
        fromAccount = null;
        toAccount = null;
    }

    boolean isClean() {
        return operationId == null && amount == 0 && fromAccount == null && toAccount == null;
    }
}
