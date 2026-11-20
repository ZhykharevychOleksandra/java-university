package lab15.bank.card;

public interface CardState {
    String name();
    void activate(Card card);
    void pay(Card card, double amount);
    void enterPin(Card card, String pin);
    void unblock(Card card);
    void blockPermanently(Card card);
    void reissue(Card card);
    void expire(Card card);
}