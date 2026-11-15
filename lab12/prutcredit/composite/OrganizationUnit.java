package lab12.prutcredit.composite;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OrganizationUnit implements BankUnit {

    private final String name;
    private final UnitLevel level;
    private final List<BankUnit> children = new ArrayList<>();

    public OrganizationUnit(String name, UnitLevel level) {
        this.name = name;
        this.level = level;
    }

    public OrganizationUnit add(BankUnit unit) {
        if (unit == this) {
            throw new IllegalArgumentException("Підрозділ не може містити сам себе");
        }
        children.add(unit);
        return this;
    }

    public void remove(BankUnit unit) {
        children.remove(unit);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKind() {
        return level.getLabel();
    }

    @Override
    public long getCashBalance() {
        long sum = 0;
        for (BankUnit child : children) {
            sum += child.getCashBalance();
        }
        return sum;
    }

    @Override
    public int getOperationsCount() {
        int sum = 0;
        for (BankUnit child : children) {
            sum += child.getOperationsCount();
        }
        return sum;
    }

    @Override
    public void printTree(String prefix, boolean isLast, boolean isRoot, Consumer<String> out) {
        out.accept(isRoot ? describe() : prefix + (isLast ? "└── " : "├── ") + describe());
        String childPrefix = isRoot ? "" : prefix + (isLast ? "    " : "│   ");
        for (int i = 0; i < children.size(); i++) {
            children.get(i).printTree(childPrefix, i == children.size() - 1, false, out);
        }
    }
}
