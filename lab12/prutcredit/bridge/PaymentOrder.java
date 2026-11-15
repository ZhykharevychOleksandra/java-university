package lab12.prutcredit.bridge;
import lab12.prutcredit.common.Money;

public class PaymentOrder extends BankDocument {
    private final String number;
    private final String payer;
    private final String receiver;
    private final long amountKopecks;

    public PaymentOrder(String number, String payer, String receiver, long amountKopecks, DeliveryChannel channel) {
        super(channel);
        this.number = number;
        this.payer = payer;
        this.receiver = receiver;
        this.amountKopecks = amountKopecks;
    }

    @Override
    public String getTitle() {
        return "Платіжне доручення №" + number;
    }

    @Override
    protected String buildContent() {
        return "платник: " + payer + ", отримувач: " + receiver + ", сума: " + Money.kopecks(amountKopecks) + " ₴";
    }
}