package lab07.legacy.task1_srp;

public class WorkoutSessionData {

    private final String userId;
    private final String deviceToken;
    private final double distanceKm;
    private final int durationMinutes;
    private final int averageHeartRate;
    private final double userWeightKg;

    public WorkoutSessionData(
            String userId,
            String deviceToken,
            double distanceKm,
            int durationMinutes,
            int averageHeartRate,
            double userWeightKg
    ) {

        this.userId = userId;
        this.deviceToken = deviceToken;
        this.distanceKm = distanceKm;
        this.durationMinutes = durationMinutes;
        this.averageHeartRate = averageHeartRate;
        this.userWeightKg = userWeightKg;
    }

    public String getUserId() {
        return userId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getAverageHeartRate() {
        return averageHeartRate;
    }

    public double getUserWeightKg() {
        return userWeightKg;
    }
}