package lab16.bank.iterator;
import java.util.List;

public class NewestFirstIterator implements TransactionIterator {
    private final List<Transaction> chronological;
    private final int limit;
    private int index;
    private int returned;

    NewestFirstIterator(List<Transaction> chronological, int limit) {
        this.chronological = chronological;
        this.limit = limit;
        this.index = chronological.size() - 1;
    }

    @Override 
    public boolean hasNext() { 
        return index >= 0 && returned < limit; 
    }

    @Override 
    public Transaction next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        returned++;
        return chronological.get(index--);
    }

    @Override 
    public String name() { 
        return "від найновіших (ліміт " + limit + ")"; 
    }

    @Override 
    public int returned() { 
        return returned; 
    }
}