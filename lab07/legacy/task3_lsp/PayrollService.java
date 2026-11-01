package lab07.legacy.task3_lsp;

import java.math.BigDecimal;
import java.util.Objects;

public class PayrollService {
    public void processYearEndBonuses(
            Employee employee,
            double rating
    ) {

        Objects.requireNonNull(
                employee,
                "Employee cannot be null"
        );

        if (!Double.isFinite(rating)
                || rating < 1.0
                || rating > 5.0) {
            throw new IllegalArgumentException(
                    "Performance rating must be between 1.0 and 5.0"
            );
        }

        if (employee instanceof BonusEligible bonusEligible) {

            BigDecimal bonus =
                    Objects.requireNonNull(
                            bonusEligible.calculateAnnualBonus(rating),
                            "Bonus calculation cannot return null"
                    );

            System.out.println(
                    "Bonus for "
                            + employee.getFullName()
                            + ": $"
                            + bonus
            );

        } else {
            System.out.println(
                    employee.getFullName()
                            + " не має права на фінансовий бонус."
            );
        }
    }
}