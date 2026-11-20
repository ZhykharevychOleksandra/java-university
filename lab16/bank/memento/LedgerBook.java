package lab16.bank.memento;
import bank.common.Log;
import java.util.LinkedHashMap;
import java.util.Map;

public class LedgerBook {
    private final Map<String, Long> balances = new LinkedHashMap<>();

    public void open(String account, long balance) { 
        balances.put(account, balance); 
    }

    public void adjust(String account, long delta) {
        if (!balances.containsKey(account)) 
            throw new IllegalArgumentException("Невідомий рахунок " + account);
        balances.merge(account, delta, Long::sum);
    }

    public String describe() {
        StringBuilder sb = new StringBuilder();
        balances.forEach((k, v) -> sb.append(k).append('=').append(Log.uah(v)).append("; "));
        return sb.toString().trim();
    }

    public LedgerMemento save(String label) {
        return new Snapshot(label, new LinkedHashMap<>(balances));
    }

    public void restore(LedgerMemento memento) {
        if (!(memento instanceof Snapshot s)) 
            throw new IllegalArgumentException("Чужий memento");
        balances.clear();
        balances.putAll(s.state);
    }

    private static final class Snapshot implements LedgerMemento {
        private final String label;
        private final Map<String, Long> state;

        private Snapshot(String label, Map<String, Long> state) {
            this.label = label;
            this.state = java.util.Collections.unmodifiableMap(state);
        }

        @Override 
        public String label() { 
            return label; 
        }
    }
}