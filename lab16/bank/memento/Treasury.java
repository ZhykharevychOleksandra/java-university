package lab16.bank.memento;
import java.util.ArrayList;
import java.util.List;

public class Treasury {
    private final List<LedgerMemento> checkpoints = new ArrayList<>();

    public void checkpoint(LedgerBook ledger, String label) {
        checkpoints.add(ledger.save(label));
    }

    public void rollbackTo(LedgerBook ledger, String label) {
        for (int i = checkpoints.size() - 1; i >= 0; i--) {
            if (checkpoints.get(i).label().equals(label)) {
                ledger.restore(checkpoints.get(i));
                return;
            }
        }
        throw new IllegalArgumentException("Немає контрольної точки: " + label);
    }

    public List<String> labels() {
        List<String> r = new ArrayList<>();
        for (LedgerMemento m : checkpoints) r.add(m.label());
        return r;
    }
}