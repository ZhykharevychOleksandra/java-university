package lab15.bank.card;
import bank.common.Log;

public class Card {
    public static final int MAX_PIN_ATTEMPTS = 3;

    private final String number;
    private final String pin;
    private double balance;
    private int failedAttempts;
    private CardState state;

    public Card(String number, String pin, double balance) {
        this.number = number; 
        this.pin = pin; 
        this.balance = balance;
        this.state = new IssuedState();
        log("Картку випущено, стан «" + state.name() + "»");
    }

    public void activate() { 
        state.activate(this); 
    }

    public void pay(double amount) { 
        state.pay(this, amount); 
    }

    public void enterPin(String p) { 
        state.enterPin(this, p); 
    }

    public void unblock() { 
        state.unblock(this); 
    }

    public void blockPermanently() { 
        state.blockPermanently(this); 
    }

    public void reissue() { 
        state.reissue(this); 
    }

    public void expire() { 
        state.expire(this); 
    }

    void setState(CardState s) {
        log("Перехід: «" + state.name() + "» → «" + s.name() + "»");
        this.state = s;
    }

    boolean pinMatches(String p) { 
        return pin.equals(p); 
    }

    int incFailed() { 
        return ++failedAttempts; 
    }
    void resetFailed() { 
        failedAttempts = 0; 
    }

    double getBalance() { 
        return balance; 
    }

    void withdraw(double a) { 
        balance -= a; 
    }

    void log(String event) { 
        Log.log("Картка *" + number.substring(number.length() - 4), event); 
    }
}