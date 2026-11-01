package lab07.legacy.task3_lsp;

public abstract class Employee {

    private final String employeeId;
    private final String fullName;

    public Employee(String employeeId, String fullName) {

        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException(
                    "Employee ID cannot be empty"
            );
        }

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException(
                    "Employee name cannot be empty"
            );
        }

        this.employeeId = employeeId;
        this.fullName = fullName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }
}