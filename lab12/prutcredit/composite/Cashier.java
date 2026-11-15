package lab12.prutcredit.composite;
import java.util.function.Consumer;

public class Cashier implements BankUnit {
    private final String name;
    private final long cashBalance;
    private final int operationsCount;

    public Cashier(String name, long cashBalance, int operationsCount) {
        this.name = name;
        this.cashBalance = cashBalance;
        this.operationsCount = operationsCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKind() {
        return "Каса";
    }

    @Override
    public long getCashBalance() {
        return cashBalance;
    }

    @Override
    public int getOperationsCount() {
        return operationsCount;
    }

    @Override
    public void printTree(String prefix, boolean isLast, boolean isRoot, Consumer<String> out) {
        out.accept(isRoot ? describe() : prefix + (isLast ? "└── " : "├── ") + describe());
    }
}
