package lab13.prutcredit.payments;
import lab13.prutcredit.common.EventLog;
import lab13.prutcredit.common.Money;

public class BankAccount {
    private final String name;
    private long balance;

    public BankAccount(String name, long initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        balance += amount;
        EventLog.log(name, "зараховано " + Money.format(amount) + ", залишок " + Money.format(balance));
    }

    public void withdraw(long amount) {
        if (balance < amount) {
            throw new IllegalStateException("недостатньо коштів на рахунку «" + name + "»: потрібно "
                    + Money.format(amount) + ", є " + Money.format(balance));
        }
        balance -= amount;
        EventLog.log(name, "списано " + Money.format(amount) + ", залишок " + Money.format(balance));
    }
}