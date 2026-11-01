package lab07.legacy.task5_dip;

public class TelemetryPacket {
    private final String deviceId;
    private final double temperature;
    private final double pressure;
    private final long timestamp;

    public TelemetryPacket(
            String deviceId,
            double temperature,
            double pressure,
            long timestamp
    ) {

        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException(
                    "Device ID cannot be empty"
            );
        }

        if (!Double.isFinite(temperature)
                || !Double.isFinite(pressure)) {
            throw new IllegalArgumentException(
                    "Temperature and pressure must be finite"
            );
        }

        this.deviceId = deviceId;
        this.temperature = temperature;
        this.pressure = pressure;
        this.timestamp = timestamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public long getTimestamp() {
        return timestamp;
    }
}