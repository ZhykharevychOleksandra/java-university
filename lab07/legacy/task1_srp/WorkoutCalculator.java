package lab07.legacy.task1_srp;
import java.util.Objects;

public class WorkoutCalculator {

    // Розрахунок середнього темпу в хвилинах на кілометр
    public double calculatePace(WorkoutSessionData session) {

        Objects.requireNonNull(
                session,
                "Workout session cannot be null"
        );

        return session.getDurationMinutes()
                / session.getDistanceKm();
    }

    // Розрахунок спалених калорій
    // MET для бігу приблизно дорівнює 9.8
    public double calculateCalories(WorkoutSessionData session) {

        Objects.requireNonNull(
                session,
                "Workout session cannot be null"
        );

        double durationHours =
                session.getDurationMinutes() / 60.0;

        return 9.8 * session.getUserWeightKg() * durationHours;
    }

    // Визначення пульсової зони
    public String calculateHeartRateZone(WorkoutSessionData session) {

        Objects.requireNonNull(
                session,
                "Workout session cannot be null"
        );

        int heartRate = session.getAverageHeartRate();

        if (heartRate >= 160) {
            return "Анаеробна / Анаеробний поріг";
        }

        if (heartRate >= 130) {
            return "Аеробна / Спалювання жиру";
        }

        return "Розминка / Легка активність";
    }
}