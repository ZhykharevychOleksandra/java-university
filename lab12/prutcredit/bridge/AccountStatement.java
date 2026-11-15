package lab12.prutcredit.bridge;
import lab12.prutcredit.common.Money;

public class AccountStatement extends BankDocument {
    private final String account;
    private final long balanceKopecks;
    private final String date;

    public AccountStatement(String account, long balanceKopecks, String date, DeliveryChannel channel) {
        super(channel);
        this.account = account;
        this.balanceKopecks = balanceKopecks;
        this.date = date;
    }

    @Override
    public String getTitle() {
        return "Довідка про стан рахунку " + account;
    }

    @Override
    protected String buildContent() {
        return "станом на " + date + " залишок: " + Money.kopecks(balanceKopecks) + " ₴";
    }
}