package lab07.legacy.task5_dip;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        System.out.println("PRODUCTION MODE");

        // Створення реальних реалізацій
        TelemetryRepository productionRepository =
                new TimescaleDbTelemetryRepository();

        AlertPublisher kafkaPublisher =
                new KafkaAlertPublisher();

        EmergencyWriter emergencyWriter =
                new LocalDiskEmergencyWriter(
                        Path.of("emergency_telemetry.log")
                );

        // Впровадження залежностей
        IoTTelemetryProcessor productionProcessor =
                new IoTTelemetryProcessor(
                        productionRepository,
                        kafkaPublisher,
                        emergencyWriter
                );

        // Створення пакета телеметрії
        TelemetryPacket packet = new TelemetryPacket(
                "DEVICE_001",
                92.5,
                12.0,
                System.currentTimeMillis()
        );

        // Обробка телеметрії
        productionProcessor.processTelemetry(packet);


        System.out.println("\n===== MOCK MODE =====");

        // Заміна реального сховища тестовим
        MockTelemetryRepository mockRepository =
                new MockTelemetryRepository();

        IoTTelemetryProcessor testProcessor =
                new IoTTelemetryProcessor(
                        mockRepository,
                        kafkaPublisher,
                        emergencyWriter
                );

        TelemetryPacket testPacket = new TelemetryPacket(
                "TEST_DEVICE",
                25.0,
                2.5,
                System.currentTimeMillis()
        );

        testProcessor.processTelemetry(testPacket);

        System.out.println(
                "\nКількість збережених тестових записів: "
                        + mockRepository.getSavedMetrics().size()
        );
    }
}