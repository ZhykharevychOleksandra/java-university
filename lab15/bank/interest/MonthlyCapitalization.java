package lab15.bank.interest;

public class MonthlyCapitalization implements InterestStrategy {
    private final double annualRate;
    
    public MonthlyCapitalization(double annualRate) { 
        this.annualRate = annualRate; 
    }

    @Override 
    public String name() { 
        return "Щомісячна капіталізація (" + Math.round(annualRate * 100) + "%)"; 
    }

    @Override 
    public double calculate(double p, int months) {
        return p * Math.pow(1 + annualRate / 12, months) - p;
    }
}