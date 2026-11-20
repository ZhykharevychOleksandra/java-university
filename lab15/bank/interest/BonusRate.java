package lab15.bank.interest;

public class BonusRate implements InterestStrategy {
    private final double baseRate, bonus;
    
    public BonusRate(double baseRate, double bonus) { 
        this.baseRate = baseRate; 
        this.bonus = bonus; 
    }

    @Override 
    public String name() { 
        return "Бонусна ставка (" + Math.round(baseRate * 100) + "% + " + Math.round(bonus * 100) + "%)"; 
    }

    @Override 
    public double calculate(double p, int months) { 
        return p * (baseRate + bonus) * months / 12; 
    }
}