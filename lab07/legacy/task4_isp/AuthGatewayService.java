package lab07.legacy.task4_isp;

public class AuthGatewayService implements
        UserAuthentication,
        SecretReset {

    @Override
    public boolean authenticate(String userIdentifier, String secretKey) {

        System.out.println(
                "Gateway authenticating: " + userIdentifier
        );

        return userIdentifier != null
                && !userIdentifier.isBlank()
                && secretKey != null
                && !secretKey.isBlank();
    }

    @Override
    public void resetSecret(String userIdentifier) {

        System.out.println(
                "Gateway secret reset for " + userIdentifier
        );
    }
}