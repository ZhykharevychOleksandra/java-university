package lab07.legacy.task3_lsp;
import java.math.BigDecimal;

public class SalariedEmployee extends Employee implements BonusEligible {

    private BigDecimal baseSalary;

    public SalariedEmployee(
            String employeeId,
            String fullName,
            BigDecimal baseSalary
    ) {
        super(employeeId, fullName);
        setSalary(baseSalary);
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setSalary(BigDecimal newSalary) {

        if (newSalary == null ||
                newSalary.compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Salary cannot be negative or null"
            );
        }

        this.baseSalary = newSalary;
    }

    @Override
    public BigDecimal calculateAnnualBonus(double performanceRating) {

        if (!Double.isFinite(performanceRating) ||
                performanceRating < 1.0 ||
                performanceRating > 5.0) {

            throw new IllegalArgumentException(
                    "Invalid performance rating"
            );
        }

        return baseSalary.multiply(
                BigDecimal.valueOf(0.10 * performanceRating)
        );
    }
}