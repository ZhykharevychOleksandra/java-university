package lab12.prutcredit.bridge;
import lab12.prutcredit.common.Money;

public class PaymentReceipt extends BankDocument {

    private final String number;
    private final String payer;
    private final String purpose;
    private final long amountKopecks;

    public PaymentReceipt(String number, String payer, String purpose, long amountKopecks, DeliveryChannel channel) {
        super(channel);
        this.number = number;
        this.payer = payer;
        this.purpose = purpose;
        this.amountKopecks = amountKopecks;
    }

    @Override
    public String getTitle() {
        return "Квитанція про оплату №" + number;
    }

    @Override
    protected String buildContent() {
        return "платник: " + payer + ", призначення: " + purpose + ", сплачено: " + Money.kopecks(amountKopecks) + " ₴";
    }
}