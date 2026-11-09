package bank.payments;
import bank.common.Log;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public final class ConnectionPool {
    private static final String SOURCE = "Пул з'єднань";

    private final int maxSize;
    private final List<SecureConnection> all = new ArrayList<>();
    private final Deque<SecureConnection> idle = new ArrayDeque<>();
    private final Map<Payment, SecureConnection> inUse = new HashMap<>();
    private final Queue<Payment> waiting = new ArrayDeque<>();

    public ConnectionPool(int maxSize) {
        this.maxSize = maxSize;
        Log.log(SOURCE, "пул створено: ліміт одночасних з'єднань " + maxSize + ", черга FIFO");
    }

    public synchronized void submit(Payment payment) {
        SecureConnection connection = acquire();
        if (connection == null) {
            waiting.add(payment);
            Log.log(SOURCE, "платіж №" + payment.getId() + " (" + Log.uah(payment.getAmount())
                    + ") у черзі, позиція " + waiting.size() + ": вільних з'єднань немає");
            return;
        }
        start(connection, payment);
    }

    public synchronized void complete(Payment payment) {
        SecureConnection connection = inUse.remove(payment);
        if (connection == null) {
            throw new IllegalArgumentException("Платіж №" + payment.getId() + " не має з'єднання");
        }
        connection.execute();
        connection.reset();
        idle.addLast(connection);
        Log.log(SOURCE, connection.getId() + " повернено, дані платежу очищено"
                + (connection.isClean() ? "" : " (ПОМИЛКА: дані лишилися!)"));

        Payment next = waiting.poll();
        if (next != null) {
            start(idle.pollFirst(), next);
        }
    }

    public synchronized int busyCount() {
        return inUse.size();
    }

    public synchronized int waitingCount() {
        return waiting.size();
    }

    public synchronized int establishedCount() {
        return all.size();
    }

    public synchronized void printStatistics() {
        int total = 0;
        for (SecureConnection c : all) {
            Log.log("Статистика", c.getId() + " (сесія " + c.getSessionId() + "): платежів "
                    + c.getPaymentsHandled());
            total += c.getPaymentsHandled();
        }
        Log.log("Статистика", "усього платежів: " + total + "; встановлено з'єднань: " + all.size());
    }

    private SecureConnection acquire() {
        if (!idle.isEmpty()) {
            return idle.pollFirst();
        }
        if (all.size() < maxSize) {
            SecureConnection created = new SecureConnection("C-" + (all.size() + 1));
            created.establish();
            all.add(created);
            return created;
        }
        return null;
    }

    private void start(SecureConnection connection, Payment payment) {
        connection.assign(payment);
        inUse.put(payment, connection);
        Log.log(SOURCE, "З'єднання " + connection.getId() + " → платіж №" + payment.getId()
                + ", " + Log.uah(payment.getAmount()));
    }
}
