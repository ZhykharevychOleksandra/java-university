package lab15.bank.card;

public class ExpiredState extends BaseCardState {
    @Override 
    public String name() { 
        return "Термін дії закінчився"; 
    }

    @Override 
    public void reissue(Card c) { 
        c.setState(new IssuedState()); 
    }
}