package lab15.bank.interest;

public class DailyAccrual implements InterestStrategy {
    private final double annualRate;
    
    public DailyAccrual(double annualRate) { 
        this.annualRate = annualRate; 
    }

    @Override 
    public String name() { 
        return "Щоденне нарахування (" + Math.round(annualRate * 100) + "%)"; 
    }

    @Override 
    public double calculate(double p, int months) {
        long days = Math.round(months * 365 / 12.0);
        return p * Math.pow(1 + annualRate / 365, days) - p;
    }
}