package lab15.bank.card;
import lab15.bank.common.Log15;

public class ActiveState extends BaseCardState {
    @Override 
    public String name() { 
        return "Активна"; 
    }

    @Override 
    public void pay(Card c, double amount) {
        if (amount > c.getBalance()) {
            c.log("Платіж " + Log15.money(amount) + " відхилено: недостатньо коштів (баланс "
                    + Log15.money(c.getBalance()) + ")");
            return;
        }
        c.withdraw(amount);
        c.log("Платіж " + Log15.money(amount) + " проведено, залишок " + Log15.money(c.getBalance()));
    }

    @Override 
    public void enterPin(Card c, String pin) {
        if (c.pinMatches(pin)) {
            c.resetFailed();
            c.log("PIN вірний");
            return;
        }
        int n = c.incFailed();
        c.log("Невірний PIN, спроба " + n + " з " + Card.MAX_PIN_ATTEMPTS);
        if (n >= Card.MAX_PIN_ATTEMPTS) c.setState(new TemporarilyBlockedState());
    }

    @Override 
    public void blockPermanently(Card c) { 
        c.setState(new PermanentlyBlockedState()); 
    }

    @Override 
    public void expire(Card c) { 
        c.setState(new ExpiredState()); 
    }
}