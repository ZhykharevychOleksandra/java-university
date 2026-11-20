package lab15.bank.interest;

public class SimpleInterest implements InterestStrategy {
    private final double annualRate;
    
    public SimpleInterest(double annualRate) { 
        this.annualRate = annualRate; 
    }

    @Override 
    public String name() { 
        return "Прості відсотки в кінці строку (" + Math.round(annualRate * 100) + "%)"; 
    }

    @Override 
    public double calculate(double p, int months) { 
        return p * annualRate * months / 12; 
    }
}