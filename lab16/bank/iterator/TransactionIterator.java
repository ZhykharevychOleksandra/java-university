package lab16.bank.iterator;

public interface TransactionIterator {
    boolean hasNext();
    Transaction next();
    String name();
    int returned();
}