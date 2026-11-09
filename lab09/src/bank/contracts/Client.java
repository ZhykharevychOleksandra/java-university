package bank.contracts;
import java.util.List;

public final class Client {
    private final String fullName;
    private final List<String> accounts;

    public Client(String fullName, List<String> accounts) {
        this.fullName = fullName;
        this.accounts = List.copyOf(accounts);
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getAccounts() {
        return accounts;
    }
}