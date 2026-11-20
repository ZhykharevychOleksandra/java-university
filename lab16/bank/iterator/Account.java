package lab16.bank.iterator;
import java.time.YearMonth;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Account {
    private final String number;
    private final Map<YearMonth, ArchiveBlock> blocks = new TreeMap<>();

    public Account(String number) { 
        this.number = number; 
    }

    public void addOperation(Transaction t) {
        blocks.computeIfAbsent(YearMonth.from(t.date()), ArchiveBlock::new).add(t);
    }

    public String getNumber() { 
        return number; 
    }

    public Collection<ArchiveBlock> getBlocks() { 
        return Collections.unmodifiableCollection(blocks.values()); 
    }
}