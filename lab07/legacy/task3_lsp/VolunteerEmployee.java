package lab07.legacy.task3_lsp;

// (Завдання 3: LSP). Нащадок порушує контракт Employee
public class VolunteerEmployee extends Employee {

    private final String organizationName;

    public VolunteerEmployee(
            String employeeId,
            String fullName,
            String organizationName
    ) {
        super(employeeId, fullName);

        if (organizationName == null ||
                organizationName.isBlank()) {

            throw new IllegalArgumentException(
                    "Organization name cannot be empty"
            );
        }

        this.organizationName = organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getEmploymentType() {
        return "Volunteer";
    }
}