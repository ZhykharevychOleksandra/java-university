import java.util.EnumSet;
import java.util.Set;

// завдання 3
public enum StorageConditions {
    FRAGILE(1, "Крихке"),
    PERISHABLE(2, "Швидкопсувне"),
    TOXIC(4, "Токсичне"),
    FLAMMABLE(8, "Легкозаймисте");

    private final int code;
    private final String description;

    // конструктор enum завжди приватний
    private StorageConditions(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }

    public String getDescription() { return description; }

    public static int toMask(Set<StorageConditions> conditions) {
        int mask = 0;
        for (StorageConditions c : conditions) {
            mask |= c.code;
        }
        return mask;
    }

    // і зворотне перетворення: маска -> набір умов
    public static Set<StorageConditions> fromMask(int mask) {
        EnumSet<StorageConditions> result = EnumSet.noneOf(StorageConditions.class);
        for (StorageConditions c : values()) {
            if ((mask & c.code) != 0) {
                result.add(c);
            }
        }
        return result;
    }
}