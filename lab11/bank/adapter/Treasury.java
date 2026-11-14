package lab11.bank.adapter;
import bank.common.Log;

public class Treasury {
    private static final String SOURCE = "Treasury";
    private final PaymentGateway gateway;

    public Treasury(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public PaymentResult sendInternationalPayment(Payment payment) {
        Log.log(SOURCE, "відправляю міжнародний платіж " + payment.amount() + " " + payment.currency()
                + " отримувачу " + payment.receiver());
        PaymentResult result = gateway.send(payment);
        Log.log(SOURCE, "результат платежу: " + result);
        return result;
    }
}