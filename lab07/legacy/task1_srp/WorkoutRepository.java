package lab07.legacy.task1_srp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class WorkoutRepository {

    public void saveWorkout(WorkoutResult result, String jsonOutput) {

        Objects.requireNonNull(
                result,
                "Workout result cannot be null"
        );

        Objects.requireNonNull(
                jsonOutput,
                "JSON output cannot be null"
        );

        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
        );

        String userId = result.getSession().getUserId();

        // Захист ідентифікатора під час формування імені файлу
        String safeUserId = userId.replaceAll(
                "[^a-zA-Z0-9_-]",
                "_"
        );

        String fileName = String.format(
                "workout_%s_%s.json",
                safeUserId,
                timestamp
        );

        try {
            Files.writeString(
                    Path.of(fileName),
                    jsonOutput
            );

            System.out.println(
                    "Тренування успішно збережено у файл: " + fileName
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Помилка збереження тренування.",
                    e
            );
        }
    }
}