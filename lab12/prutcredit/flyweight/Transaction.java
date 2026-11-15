package lab12.prutcredit.flyweight;
import lab12.prutcredit.common.Money;
import java.time.LocalDate;

public class Transaction {
    private final int id;
    private final long amountKopecks;
    private final LocalDate date;
    private final String account;
    private final Merchant merchant;

    public Transaction(int id, long amountKopecks, LocalDate date, String account, Merchant merchant) {
        this.id = id;
        this.amountKopecks = amountKopecks;
        this.date = date;
        this.account = account;
        this.merchant = merchant;
    }

    public int getId() {
        return id;
    }

    public long getAmountKopecks() {
        return amountKopecks;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAccount() {
        return account;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    @Override
    public String toString() {
        return String.format("операція #%d: %s ₴, %s, рахунок %s, торговець «%s»",
                id, Money.kopecks(amountKopecks), date, account, merchant.getName());
    }
}
