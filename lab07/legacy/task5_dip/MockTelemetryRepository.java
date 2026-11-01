package lab07.legacy.task5_dip;
import java.util.ArrayList;
import java.util.List;

public class MockTelemetryRepository implements TelemetryRepository {
    private final List<String> savedMetrics = new ArrayList<>();
    @Override
    public void insertMetric(
            String deviceId,
            double temperature,
            double pressure,
            long timestamp
    ) {

        String metric = String.format(
                "Device=%s, Temperature=%.2f, Pressure=%.2f, Timestamp=%d",
                deviceId,
                temperature,
                pressure,
                timestamp
        );

        savedMetrics.add(metric);

        System.out.println(
                "[MOCK DATABASE] " + metric
        );
    }

    public List<String> getSavedMetrics() {
        return List.copyOf(savedMetrics);
    }
}