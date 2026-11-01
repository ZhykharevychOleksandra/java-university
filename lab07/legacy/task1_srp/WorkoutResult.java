package lab07.legacy.task1_srp;
import java.time.Instant;
import java.util.Objects;

public class WorkoutResult {

    private final WorkoutSessionData session;
    private final double paceMinutesPerKm;
    private final double caloriesBurned;
    private final String heartRateZone;
    private final Instant timestamp;

    public WorkoutResult(
            WorkoutSessionData session,
            double paceMinutesPerKm,
            double caloriesBurned,
            String heartRateZone,
            Instant timestamp
    ) {

        this.session = Objects.requireNonNull(
                session,
                "Workout session cannot be null"
        );

        if (!Double.isFinite(paceMinutesPerKm)
                || paceMinutesPerKm <= 0) {

            throw new IllegalArgumentException(
                    "Invalid workout pace"
            );
        }

        if (!Double.isFinite(caloriesBurned)
                || caloriesBurned < 0) {

            throw new IllegalArgumentException(
                    "Invalid calories value"
            );
        }

        this.paceMinutesPerKm = paceMinutesPerKm;
        this.caloriesBurned = caloriesBurned;

        this.heartRateZone = Objects.requireNonNull(
                heartRateZone,
                "Heart rate zone cannot be null"
        );

        this.timestamp = Objects.requireNonNull(
                timestamp,
                "Timestamp cannot be null"
        );
    }

    public WorkoutSessionData getSession() {
        return session;
    }

    public double getPaceMinutesPerKm() {
        return paceMinutesPerKm;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getHeartRateZone() {
        return heartRateZone;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}