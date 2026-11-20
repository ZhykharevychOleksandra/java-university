package lab16.bank.iterator;

public interface TransactionCollection {
    TransactionIterator newestFirst(int limit);
    TransactionIterator yearChronological(int year);
    TransactionIterator aboveAmount(long thresholdUah);
}