package lab16.bank.iterator;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArchiveBlock {
    private final YearMonth period;
    private final List<Transaction> operations = new ArrayList<>();

    public ArchiveBlock(YearMonth period) { 
        this.period = period; 
    }

    void add(Transaction t) { 
        operations.add(t); 
    }

    public YearMonth getPeriod() { 
        return period; 
    }

    public List<Transaction> getOperations() { 
        return Collections.unmodifiableList(operations); 
    }
}