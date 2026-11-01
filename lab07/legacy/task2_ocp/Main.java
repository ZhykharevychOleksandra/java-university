package lab07.legacy.task2_ocp;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Створення даних звіту
        ReportData report = new ReportData(
                "Звіт про продажі",
                LocalDateTime.now()
        );

        report.addHeader("Працівник");
        report.addHeader("Відділ");
        report.addHeader("Зарплата");

        report.addRow(List.of("Іван", "IT", "30000"));
        report.addRow(List.of("Олена", "HR", "25000"));
        report.addRow(List.of("Петро", "Finance", "35000"));

        // PDF
        testExport(report, new PdfExportStrategy());

        // EXCEL
        testExport(report, new ExcelExportStrategy());

        // CSV
        testExport(report, new CsvExportStrategy());

        // JSON
        testExport(report, new JsonExportStrategy());

        // XML — новий формат
        testExport(report, new XmlExportStrategy());
    }

    private static void testExport(
            ReportData report,
            ExportStrategy strategy
    ) {

        ReportExporter exporter = new ReportExporter(strategy);

        System.out.println("\n========== "
                + strategy.getFormatName()
                + " ==========");

        String result = exporter.export(report);

        System.out.println(result);
    }
}