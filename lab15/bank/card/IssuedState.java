package lab15.bank.card;

public class IssuedState extends BaseCardState {
    @Override 
    public String name() { 
        return "Випущена"; 
    }

    @Override 
    public void activate(Card c) { 
        c.setState(new ActiveState()); 
    }

    @Override 
    public void blockPermanently(Card c) { 
        c.setState(new PermanentlyBlockedState()); 
    }
}