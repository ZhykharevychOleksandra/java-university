package lab07.legacy.task4_isp;

public class Main {
    public static void main(String[] args) {

        // Створення особистого кабінету
        SelfServiceCustomerPortal portal =
                new SelfServiceCustomerPortal();

        // Реєстрація користувача
        portal.registerUser(
                "user001",
                "password123"
        );

        // Автентифікація
        boolean authenticated = portal.authenticate(
                "user001",
                "password123"
        );

        System.out.println(
                "Authentication result: " + authenticated
        );

        // Скидання пароля
        portal.resetSecret("user001");

        // GDPR-експорт
        portal.exportUserDataGdpr(1);

        System.out.println("\n----------------------");

        // Створення сервісу автентифікації
        AuthGatewayService gateway =
                new AuthGatewayService();

        boolean gatewayResult = gateway.authenticate(
                "user002",
                "secret456"
        );

        System.out.println(
                "Gateway result: " + gatewayResult
        );

        gateway.resetSecret("user002");
    }
}