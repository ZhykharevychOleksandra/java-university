package lab07.legacy.task5_dip;

public interface TelemetryRepository {
    void insertMetric(
            String deviceId,
            double temperature,
            double pressure,
            long timestamp
    );
}