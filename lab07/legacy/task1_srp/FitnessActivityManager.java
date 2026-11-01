package lab07.legacy.task1_srp;
import java.time.Instant;

public class FitnessActivityManager {
    private final WorkoutValidator validator;
    private final WorkoutCalculator calculator;
    private final WorkoutReportGenerator reportGenerator;
    private final WorkoutRepository repository;
    private final NotificationService notificationService;

    public FitnessActivityManager() {
        this.validator = new WorkoutValidator();
        this.calculator = new WorkoutCalculator();
        this.reportGenerator = new WorkoutReportGenerator();
        this.repository = new WorkoutRepository();
        this.notificationService = new NotificationService();
    }

    public void processWorkoutSession(WorkoutSessionData session) {

        // 1. Валідація тренування
        validator.validate(session);

        // 2. Розрахунок результатів
        double pace = calculator.calculatePace(session);

        double calories = calculator.calculateCalories(session);

        String heartRateZone =
                calculator.calculateHeartRateZone(session);

        // 3. Створення результату тренування
        WorkoutResult result = new WorkoutResult(
                session,
                pace,
                calories,
                heartRateZone,
                Instant.now()
        );

        // 4. Формування JSON-звіту
        String jsonReport = reportGenerator.generateJson(result);

        System.out.println("Результат тренування:");
        System.out.println(jsonReport);

        // 5. Збереження тренування
        repository.saveWorkout(result, jsonReport);

        // 6. Надсилання Push-сповіщення
        notificationService.sendWorkoutNotification(result);

        System.out.println("Обробку тренування завершено!");
    }
}