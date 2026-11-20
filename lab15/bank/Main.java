package lab15.bank;
import lab15.bank.card.Card;
import lab15.bank.closing.*;
import lab15.bank.common.Client;
import lab15.bank.interest.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("##### Ситуація 1: Template Method — закриття продуктів #####");
        situation1Closure();
        System.out.println("\n##### Ситуація 2: State — стан картки #####");
        situation2CardState();
        System.out.println("\n##### Ситуація 3: Strategy — нарахування відсотків #####");
        situation3Interest();
    }

    static void situation1Closure() {
        Client ok = new Client("Іваненко І.І.", 0);
        Client debtor = new Client("Петренко П.П.", 1500);

        new DepositClosure(100_000, 0.10, 12).close(ok);
        System.out.println();
        new LoanClosure(20_000).close(debtor);   
        System.out.println();
        new CardClosure(350.75).close(ok);      
    }

    static void situation2CardState() {
        Card card = new Card("4149000011112222", "1234", 1000);
        card.pay(100);                
        card.activate();
        card.pay(200);
        card.enterPin("0000");
        card.enterPin("1111");
        card.enterPin("2222");         
        card.pay(50);                  
        card.unblock();
        card.blockPermanently();
        card.unblock();                
    }

    static void situation3Interest() {
        double sum = 100_000;
        Deposit d = new Deposit("D-1", sum, 12, new SimpleInterest(0.10));
        d.calculateIncome();
        d.setStrategy(new MonthlyCapitalization(0.10));
        d.calculateIncome();
        d.setStrategy(new DailyAccrual(0.10));
        d.calculateIncome();

        System.out.println();
        d.prolong(12, new TieredRate());    
        d.calculateIncome();

        System.out.println();
        d.setStrategy(new BonusRate(0.10, 0.02)); 
        d.calculateIncome();
    }
}