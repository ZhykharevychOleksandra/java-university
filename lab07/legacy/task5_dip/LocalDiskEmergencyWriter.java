package lab07.legacy.task5_dip;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LocalDiskEmergencyWriter implements EmergencyWriter {
    private final Path filePath;
    public LocalDiskEmergencyWriter(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeBackupLog(String data) {

        try {
            Files.writeString(
                    filePath,
                    data + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            System.out.println(
                    "[Emergency Disk Log] Резервний запис виконано."
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Помилка резервного запису телеметрії.",
                    e
            );
        }
    }
}