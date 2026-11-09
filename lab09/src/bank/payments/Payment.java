package bank.payments;

public final class Payment {
    private final int id;
    private final long amount;
    private final String fromAccount;
    private final String toAccount;
    private final String operationId;

    public Payment(int id, long amount, String fromAccount, String toAccount) {
        this.id = id;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.operationId = "OP-" + id;
    }

    public int getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getOperationId() {
        return operationId;
    }
}
