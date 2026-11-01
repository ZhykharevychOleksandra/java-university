package lab07.legacy.task1_srp;
public class NotificationService {

    public void sendWorkoutNotification(WorkoutResult result) {

        WorkoutSessionData session = result.getSession();

        String pushPayload = String.format(
                """
                {
                  "to": "%s",
                  "title": "Чудове тренування!",
                  "body": "Ви подолали %.1f км та спалили %.0f ккал!"
                }
                """,
                escapeJson(session.getDeviceToken()),
                session.getDistanceKm(),
                result.getCaloriesBurned()
        );

        System.out.println(
                "Надсилання Push-сповіщення через push-gateway.local"
        );

        System.out.println("Payload:");
        System.out.println(pushPayload);

        System.out.println("Push-сповіщення успішно відправлено.");
    }

    private String escapeJson(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}