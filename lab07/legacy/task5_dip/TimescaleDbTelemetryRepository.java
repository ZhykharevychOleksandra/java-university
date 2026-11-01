package lab07.legacy.task5_dip;

public class TimescaleDbTelemetryRepository
        implements TelemetryRepository {
    @Override
    public void insertMetric(
            String deviceId,
            double temperature,
            double pressure,
            long timestamp
    ) {
        System.out.println(
                "[TimescaleDB] Записано метрику для "
                        + deviceId
                        + ": Temp=" + temperature
                        + "°C, Press=" + pressure
                        + "bar, Timestamp=" + timestamp
        );
    }
}