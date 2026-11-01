package lab07.legacy.task5_dip;
import java.util.Objects;

public class IoTTelemetryProcessor {

    private final TelemetryRepository telemetryRepository;
    private final AlertPublisher alertPublisher;
    private final EmergencyWriter emergencyWriter;

    // Constructor Injection
    public IoTTelemetryProcessor(
            TelemetryRepository telemetryRepository,
            AlertPublisher alertPublisher,
            EmergencyWriter emergencyWriter
    ) {

        this.telemetryRepository = Objects.requireNonNull(
                telemetryRepository,
                "TelemetryRepository cannot be null"
        );

        this.alertPublisher = Objects.requireNonNull(
                alertPublisher,
                "AlertPublisher cannot be null"
        );

        this.emergencyWriter = Objects.requireNonNull(
                emergencyWriter,
                "EmergencyWriter cannot be null"
        );
    }

    public void processTelemetry(TelemetryPacket packet) {

        if (packet == null) {
            throw new IllegalArgumentException(
                    "Некоректний пакет телеметрії."
            );
        }

        if (packet.getDeviceId() == null
                || packet.getDeviceId().isBlank()) {
            throw new IllegalArgumentException(
                    "Ідентифікатор пристрою не може бути порожнім."
            );
        }

        if (!Double.isFinite(packet.getTemperature())
                || !Double.isFinite(packet.getPressure())) {
            throw new IllegalArgumentException(
                    "Некоректні показники температури або тиску."
            );
        }

        // 1. Збереження телеметрії
        telemetryRepository.insertMetric(
                packet.getDeviceId(),
                packet.getTemperature(),
                packet.getPressure(),
                packet.getTimestamp()
        );

        // 2. Резервне збереження
        String backupData = String.format(
                "DEV:%s|T:%.2f|P:%.2f",
                packet.getDeviceId(),
                packet.getTemperature(),
                packet.getPressure()
        );

        emergencyWriter.writeBackupLog(backupData);

        // 3. Перевірка критичних показників
        if (packet.getTemperature() > 85.0
                || packet.getPressure() > 10.0) {

            alertPublisher.sendEmergencyAlert(
                    "iot-critical-alerts",
                    "Критичний перегрів або тиск на пристрої "
                            + packet.getDeviceId() + "!"
            );
        }

        System.out.println(
                "Обробку телеметрії завершено!"
        );
    }
}