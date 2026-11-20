package lab15.bank.card;

public class TemporarilyBlockedState extends BaseCardState {
    @Override 
    public String name() { 
        return "Тимчасово заблокована"; 
    }

    @Override 
    public void unblock(Card c) {
        c.resetFailed();
        c.setState(new ActiveState());
    }

    @Override 
    public void blockPermanently(Card c) { 
        c.setState(new PermanentlyBlockedState()); 
    }
}