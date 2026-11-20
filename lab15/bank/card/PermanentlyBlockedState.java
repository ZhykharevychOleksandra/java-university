package lab15.bank.card;

public class PermanentlyBlockedState extends BaseCardState {
    @Override 
    public String name() { 
        return "Заблокована назавжди"; 
    }

    @Override 
    public void reissue(Card c) { 
        c.setState(new IssuedState()); 
    }
}