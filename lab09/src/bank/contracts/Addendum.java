package bank.contracts;

public final class Addendum {
    private final String number;
    private final String text;

    public Addendum(String number, String text) {
        this.number = number;
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }
}