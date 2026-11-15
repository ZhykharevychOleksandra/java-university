package lab12.prutcredit.composite;
import lab12.prutcredit.common.Money;
import java.util.function.Consumer;

public interface BankUnit {
    String getName();
    String getKind();
    long getCashBalance();
    int getOperationsCount();
    void printTree(String prefix, boolean isLast, boolean isRoot, Consumer<String> out);

    default void printTree(Consumer<String> out) {
        printTree("", true, true, out);
    }

    default String describe() {
        return String.format("%s (%s) — залишок: %s ₴, операцій: %d",
                getName(), getKind(), Money.uah(getCashBalance()), getOperationsCount());
    }
}
