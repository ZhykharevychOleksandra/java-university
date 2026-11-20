package lab16.bank.iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteringIterator implements TransactionIterator {
    private final List<Transaction> chronological;
    private final Predicate<Transaction> filter;
    private final String name;
    private int index = 0;
    private int returned = 0;

    FilteringIterator(List<Transaction> chronological, Predicate<Transaction> filter, String name) {
        this.chronological = chronological;
        this.filter = filter;
        this.name = name;
    }

    @Override 
    public boolean hasNext() {
        while (
            index < chronological.size() && !filter.test(chronological.get(index))
        ) index++;
        return index < chronological.size();
    }

    @Override 
    public Transaction next() {
        if (!hasNext()) throw new NoSuchElementException();
        returned++;
        return chronological.get(index++);
    }

    @Override 
    public String name() { 
        return name; 
    }

    @Override 
    public int returned() { 
        return returned; 
    }
}