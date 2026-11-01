package lab07.legacy.task2_ocp;
import java.util.Objects;

public class ReportExporter {

    private final ExportStrategy strategy;

    public ReportExporter(ExportStrategy strategy) {
        this.strategy = Objects.requireNonNull(
                strategy,
                "Стратегія експорту не може бути null"
        );
    }

    public String export(ReportData data) {

        Objects.requireNonNull(
                data,
                "Дані звіту не можуть бути null"
        );

        String result = strategy.export(data);

        System.out.println(
                "[EXPORTER] Успішно створено звіт: "
                        + data.getTitle()
                        + " у форматі "
                        + strategy.getFormatName()
        );

        return result;
    }
}