package lab15.bank.interest;

public class TieredRate implements InterestStrategy {
    @Override 
    public String name() { 
        return "Ступінчаста ставка залежно від суми"; 
    }

    @Override 
    public double calculate(double p, int months) {
        double rate = p < 50_000 ? 0.08 : p < 100_000 ? 0.09 : 0.11;
        return p * rate * months / 12;
    }
}