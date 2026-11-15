package lab13.prutcredit.approval;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lab13.prutcredit.common.EventLog;

public class ApprovalChain {
    private static final String SOURCE = "Система погодження";

    private final List<ApprovalHandler> handlers = new ArrayList<>();

    public ApprovalChain addLast(ApprovalHandler handler) {
        handlers.add(handler);
        relink();
        return this;
    }

    public ApprovalChain addFirst(ApprovalHandler handler) {
        handlers.add(0, handler);
        relink();
        return this;
    }

    public ApprovalChain insertBefore(String existingName, ApprovalHandler handler) {
        int index = indexOf(existingName);
        handlers.add(index, handler);
        relink();
        return this;
    }

    public boolean remove(String name) {
        for (ApprovalHandler h : handlers) {
            if (h.getName().equals(name)) {
                h.setNext(null); // відв'язуємо видалений рівень, щоб не лишилось «висячого» next
            }
        }
        boolean removed = handlers.removeIf(h -> h.getName().equals(name));
        relink();
        return removed;
    }

    public void submit(LoanApplication application) {
        EventLog.log(SOURCE, "подано " + application.describe());
        if (handlers.isEmpty()) {
            EventLog.log(SOURCE, "ланцюг порожній");
        } else {
            handlers.get(0).handle(application);
        }
        if (application.getStatus() == LoanStatus.PENDING) {
            application.markUnprocessed();
        }
        EventLog.log(SOURCE, "РЕЗУЛЬТАТ: " + application.result());
    }

    public String describe() {
        return handlers.stream().map(ApprovalHandler::getName).collect(Collectors.joining(" → "));
    }

    private int indexOf(String name) {
        for (int i = 0; i < handlers.size(); i++) {
            if (handlers.get(i).getName().equals(name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Рівня «" + name + "» немає в ланцюгу");
    }

    private void relink() {
        for (int i = 0; i < handlers.size(); i++) {
            handlers.get(i).setNext(i + 1 < handlers.size() ? handlers.get(i + 1) : null);
        }
    }
}
