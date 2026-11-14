package lab11.bank.adapter;

public interface PaymentGateway {
    PaymentResult send(Payment payment);
}