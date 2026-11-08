package lab09.src.bank.objectpool;
import lab09.src.bank.Logger;
import java.util.*;

public class PaymentConnectionPool {

    private final int maxConnections;
    private int createdConnections = 0;

    private final Queue<PaymentConnection> available =
            new ArrayDeque<>();

    private final Queue<PaymentConnection> active =
            new ArrayDeque<>();

    private final Queue<Payment> waiting =
            new ArrayDeque<>();

    private final List<PaymentConnection> allConnections =
            new ArrayList<>();

    public PaymentConnectionPool(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public void submit(Payment payment) {

        if (!available.isEmpty()) {

            PaymentConnection connection = available.poll();
            assignPayment(connection, payment);

        } else if (createdConnections < maxConnections) {

            createdConnections++;

            PaymentConnection connection =
                    new PaymentConnection("C-" + createdConnections);

            allConnections.add(connection);
            assignPayment(connection, payment);

        } else {

            waiting.offer(payment);

            Logger.log("PaymentPool",
                    "Платіж №" + payment.getId() +
                    " поставлено в чергу");
        }
    }

    private void assignPayment(
            PaymentConnection connection,
            Payment payment
    ) {
        active.offer(connection);

        Logger.log("PaymentPool",
                "З'єднання " + connection.getId() +
                " → платіж №" + payment.getId() +
                ", " + payment.getAmount() + " грн");

        connection.process(payment);
    }

    public void completeNext() {

        PaymentConnection connection = active.poll();

        if (connection == null) {
            return;
        }

        connection.clear();
        available.offer(connection);

        if (!waiting.isEmpty()) {

            Payment nextPayment = waiting.poll();
            PaymentConnection freeConnection = available.poll();

            assignPayment(freeConnection, nextPayment);
        }
    }

    public boolean hasActivePayments() {
        return !active.isEmpty();
    }

    public int getCreatedConnections() {
        return createdConnections;
    }

    public void printStatistics() {

        Logger.log("PaymentPool",
                "===== СТАТИСТИКА ПУЛУ =====");

        for (PaymentConnection connection : allConnections) {

            Logger.log("Statistics",
                    connection.getId() +
                    ": платежів проведено = " +
                    connection.getPaymentsProcessed());
        }

        Logger.log("Statistics",
                "Загальна кількість створених з'єднань: " +
                createdConnections);

        Logger.log("Statistics",
                "Платежів у черзі: " + waiting.size());
    }
}