package lab16.bank.iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Client implements TransactionCollection {
    private final String name;
    private final List<Account> accounts = new ArrayList<>();

    public Client(String name) { 
        this.name = name; 
    }

    public void addAccount(Account a) { 
        accounts.add(a); 
    }

    public String getName() { 
        return name; 
    }

    public String describeStructure() {
        int blocks = 0, ops = 0;
        for (Account a : accounts)
            for (ArchiveBlock b : a.getBlocks()) { blocks++; ops += b.getOperations().size(); }
        return "рахунків: " + accounts.size() + ", архівних блоків: " + blocks + ", операцій: " + ops;
    }

    private List<Transaction> chronological() {
        List<Transaction> all = new ArrayList<>();
        for (Account a : accounts)
            for (ArchiveBlock b : a.getBlocks()) all.addAll(b.getOperations());
        all.sort(Comparator.comparing(Transaction::date).thenComparing(Transaction::id));
        return all;
    }

    @Override 
    public TransactionIterator newestFirst(int limit) {
        return new NewestFirstIterator(chronological(), limit);
    }

    @Override 
    public TransactionIterator yearChronological(int year) {
        return new FilteringIterator(chronological(), t -> t.date().getYear() == year,
                "за " + year + " рік, хронологічно");
    }

    @Override 
    public TransactionIterator aboveAmount(long thresholdUah) {
        return new FilteringIterator(chronological(), t -> Math.abs(t.amountUah()) > thresholdUah,
                "фінмоніторинг (понад " + thresholdUah + " грн)");
    }
}