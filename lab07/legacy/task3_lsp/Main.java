package lab07.legacy.task3_lsp;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        // Створення звичайного працівника
        SalariedEmployee employee = new SalariedEmployee(
                "EMP001",
                "Іван Петренко",
                new BigDecimal("30000")
        );

        // Створення волонтера
        VolunteerEmployee volunteer = new VolunteerEmployee(
                "VOL001",
                "Олена Коваленко",
                "Допомога поруч"
        );

        PayrollService payrollService = new PayrollService();

        // Нарахування бонусу працівнику
        payrollService.processYearEndBonuses(employee, 4.5);

        // Спроба обробки бонусу волонтера
        payrollService.processYearEndBonuses(volunteer, 5.0);

        // Демонстрація спільного базового типу
        Employee employee1 = employee;
        Employee employee2 = volunteer;

        System.out.println("\nПрацівник: " + employee1.getFullName());
        System.out.println("Працівник: " + employee2.getFullName());
    }
}