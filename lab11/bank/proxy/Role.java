package lab11.bank.proxy;

public enum Role {
    CREDIT_MANAGER("кредитний менеджер"),
    CASHIER("касир");

    private final String title;

    Role(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}