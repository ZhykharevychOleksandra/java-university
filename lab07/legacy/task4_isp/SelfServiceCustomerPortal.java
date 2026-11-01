package lab07.legacy.task4_isp;

public class SelfServiceCustomerPortal implements
        UserRegistration,
        UserAuthentication,
        SecretReset,
        GdprExport {

    @Override
    public void registerUser(String userIdentifier, String secretKey) {
        System.out.println(
                "User registered: " + userIdentifier
        );
    }

    @Override
    public boolean authenticate(String userIdentifier, String secretKey) {
        System.out.println(
                "Authenticating user: " + userIdentifier
        );

        return userIdentifier != null
                && !userIdentifier.isBlank()
                && secretKey != null
                && !secretKey.isBlank();
    }

    @Override
    public void resetSecret(String userIdentifier) {
        System.out.println(
                "Secret reset sent for " + userIdentifier
        );
    }

    @Override
    public void exportUserDataGdpr(int userId) {
        System.out.println(
                "Exported GDPR archive for user " + userId
        );
    }
}