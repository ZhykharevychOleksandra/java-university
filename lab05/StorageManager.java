package lab05;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class StorageManager<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public T find(Predicate<T> condition) {
        for (T item : items) {
            if (condition.test(item)) {
                return item;
            }
        }
        return null;
    }

    public List<T> filter(Predicate<T> condition) {
        List<T> result = new ArrayList<>();

        for (T item : items) {
            if (condition.test(item)) {
                result.add(item);
            }
        }

        return Collections.unmodifiableList(result);
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }
}
