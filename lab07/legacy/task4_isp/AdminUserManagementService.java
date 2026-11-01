package lab07.legacy.task4_isp;

public class AdminUserManagementService implements
        RoleManagement,
        UserAudit {

    @Override
    public void assignRole(int userId, String role) {

        if (userId <= 0 || role == null || role.isBlank()) {
            throw new IllegalArgumentException(
                    "Invalid user ID or role"
            );
        }

        System.out.println(
                "Role " + role + " assigned to user " + userId
        );
    }

    @Override
    public void auditUserLogs(int userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid user ID"
            );
        }

        System.out.println(
                "Auditing logs for user " + userId
        );
    }
}