package lab09.src.bank.objectpool;

public class Payment {
    private final int id;
    private final double amount;
    private final String fromAccount;
    private final String toAccount;

    public Payment(
            int id,
            double amount,
            String fromAccount,
            String toAccount
    ) {
        this.id = id;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }
}