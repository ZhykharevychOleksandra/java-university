package lab15.bank.card;

public abstract class BaseCardState implements CardState {
    protected void deny(Card card, String op, String hint) {
        card.log("Операція «" + op + "» відхилена: картка у стані «" + name() + "». " + hint);
    }

    @Override 
    public void activate(Card c) { 
        deny(c, "активація", "Активувати можна лише випущену картку."); 
    }

    @Override 
    public void pay(Card c, double a) { 
        deny(c, "платіж " + a, "Платити можна лише активною карткою."); 
    }

    @Override 
    public void enterPin(Card c, String p) { 
        deny(c, "введення PIN", "PIN приймається лише в активному стані."); 
    }

    @Override 
    public void unblock(Card c) { 
        deny(c, "розблокування", "Розблокувати можна лише тимчасово заблоковану картку."); 
    }

    @Override 
    public void blockPermanently(Card c) { 
        deny(c, "блокування назавжди", "Картка вже недоступна."); 
    }

    @Override 
    public void reissue(Card c) { 
        deny(c, "перевипуск", "Перевипустити можна заблоковану назавжди або прострочену картку."); 
    }

    @Override 
    public void expire(Card c) { 
        deny(c, "закінчення терміну", "Для цього стану не застосовується."); 
    }
}