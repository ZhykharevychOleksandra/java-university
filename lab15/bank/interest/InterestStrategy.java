package lab15.bank.interest;

public interface InterestStrategy {
    String name();
    double calculate(double principal, int months);
}