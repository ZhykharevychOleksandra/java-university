package lab08.medicalclinic.domain;

public class InsurancePolicy {
    private final double coverageRate;

    public InsurancePolicy(double coverageRate) {
        if (coverageRate < 0 || coverageRate > 1) {
            throw new IllegalArgumentException(
                "Некоректний відсоток страхового покриття");
        }

        this.coverageRate = coverageRate;
    }

    public double calculateCoverage(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                "Сума не може бути від'ємною");
            }
        return amount * coverageRate;
    }
}