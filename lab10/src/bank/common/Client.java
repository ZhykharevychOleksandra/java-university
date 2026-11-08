package lab10.src.bank.common;
import java.util.List;

// клієнт банку: ПІБ (або назва підприємства) та рахунки
public record Client(String fullName, List<String> accounts) {

    public Client(String fullName) {
        this(fullName, List.of());
    }

    @Override
    public String toString() {
        return fullName;
    }
}