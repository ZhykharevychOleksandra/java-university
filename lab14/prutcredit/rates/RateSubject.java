package lab14.prutcredit.rates;

public interface RateSubject {
    void subscribe(RateObserver observer);
    void unsubscribe(RateObserver observer);
}