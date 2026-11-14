package lab11.bank.adapter;
import bank.common.Log;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SwiftPaymentAdapter implements PaymentGateway {
    private static final String SOURCE = "SwiftPaymentAdapter";
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyMMdd");

    private final SwiftLibrary library;
    private final LocalDate valueDate;
    private int sequence = 0;

    public SwiftPaymentAdapter(SwiftLibrary library, LocalDate valueDate) {
        this.library = library;
        this.valueDate = valueDate;
    }

    @Override
    public PaymentResult send(Payment payment) {
        Log.log(SOURCE, "отримано платіж казначейства: " + payment.sender() + " -> " + payment.receiver()
                + ", " + payment.amount() + " " + payment.currency());
        String message = buildMessage(payment);
        Log.log(SOURCE, "сформовано SWIFT-повідомлення, передаю бібліотеці");
        String status = library.transmit(message);
        PaymentResult result = parseStatus(status);
        Log.log(SOURCE, "статус \"" + status + "\" перетворено у формат казначейства: " + result);
        return result;
    }

    private String buildMessage(Payment p) {
        String amount = String.format(Locale.ROOT, "%.2f", p.amount()).replace('.', ',');
        return ":20:PRUT" + String.format("%06d", ++sequence) + "\n"
                + ":32A:" + valueDate.format(DATE) + p.currency() + amount + "\n"
                + ":50K:" + p.sender() + "\n"
                + ":59:" + p.receiver();
    }

    private PaymentResult parseStatus(String code) {
        return switch (code) {
            case "ACSC" -> PaymentResult.ok();
            case "RJCT/AC04" -> PaymentResult.rejected("рахунок отримувача закрито");
            case "RJCT/AM04" -> PaymentResult.rejected("недостатньо коштів");
            case "RJCT/RR04" -> PaymentResult.rejected("відмова з регуляторних причин (санкції)");
            default -> PaymentResult.rejected("невідомий статус бібліотеки: " + code);
        };
    }
}