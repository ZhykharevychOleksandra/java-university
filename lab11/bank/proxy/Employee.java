package lab11.bank.proxy;

public record Employee(String name, Role role) {
    @Override
    public String toString() {
        return role.title() + " " + name;
    }
}