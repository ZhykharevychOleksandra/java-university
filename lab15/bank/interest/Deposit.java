package lab15.bank.interest;
import lab15.bank.common.Log15;

public class Deposit {
    private final String id;
    private final double principal;
    private int months;
    private InterestStrategy strategy;

    public Deposit(String id, double principal, int months, InterestStrategy strategy) {
        this.id = id; 
        this.principal = principal; 
        this.months = months; 
        this.strategy = strategy;
        log("Відкрито депозит " + Log15.money(principal) + " на " + months + " міс., схема: " + strategy.name());
    }

    public void setStrategy(InterestStrategy s) {
        log("Схему змінено: «" + strategy.name() + "» → «" + s.name() + "»");
        this.strategy = s;
    }

    public void prolong(int extraMonths, InterestStrategy newStrategy) {
        log("Пролонгація на " + extraMonths + " міс.");
        this.months = extraMonths;
        setStrategy(newStrategy);
    }

    public double calculateIncome() {
        double income = strategy.calculate(principal, months);
        log("Розрахунок за схемою «" + strategy.name() + "», " + months + " міс.: дохід " + Log15.money(income));
        return income;
    }

    private void log(String e) { 
        Log15.log("Депозит " + id, e); 
    }
}