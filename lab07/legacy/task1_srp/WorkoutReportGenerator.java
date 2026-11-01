package lab07.legacy.task1_srp;
import java.util.Objects;

public class WorkoutReportGenerator {

    public String generateJson(WorkoutResult result) {

        Objects.requireNonNull(
                result,
                "Workout result cannot be null"
        );

        WorkoutSessionData session = result.getSession();

        int paceMinutes =
                (int) result.getPaceMinutesPerKm();

        int paceSeconds = (int) Math.round(
                (result.getPaceMinutesPerKm() - paceMinutes) * 60
        );

        // Корекція округлення секунд
        if (paceSeconds == 60) {
            paceMinutes++;
            paceSeconds = 0;
        }

        return String.format(
                """
                {
                  "UserId": "%s",
                  "DistanceKm": %.2f,
                  "Pace": "%d:%02d хв/км",
                  "Calories": %.0f,
                  "IntensityZone": "%s",
                  "Timestamp": "%s"
                }
                """,
                escapeJson(session.getUserId()),
                session.getDistanceKm(),
                paceMinutes,
                paceSeconds,
                result.getCaloriesBurned(),
                escapeJson(result.getHeartRateZone()),
                result.getTimestamp()
        );
    }

    // Екранування спеціальних символів JSON
    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}