package lab15.bank.common;

public class Client {
    private final String name;
    private final double debt;

    public Client(String name, double debt) {
        this.name = name;
        this.debt = debt;
    }

    public String getName() { 
        return name; 
    }

    public double getDebt() { 
        return debt; 
    }
}