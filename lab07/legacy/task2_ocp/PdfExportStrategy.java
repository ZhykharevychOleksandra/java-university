package lab07.legacy.task2_ocp;
import java.time.format.DateTimeFormatter;

public class PdfExportStrategy implements ExportStrategy {

    @Override
    public String export(ReportData data) {

        if (data == null) {
            throw new IllegalArgumentException(
                    "Report data cannot be null"
            );
        }

        StringBuilder builder = new StringBuilder();

        builder.append("%PDF-1.7 HEADER\n");

        builder.append("TITLE: ")
                .append(data.getTitle())
                .append("\n");

        builder.append("GENERATED: ")
                .append(data.getGeneratedAt().format(
                        DateTimeFormatter.ISO_LOCAL_DATE
                ))
                .append("\n");

        builder.append("ROWS: ")
                .append(data.getRows().size())
                .append("\n");

        builder.append("PDF TABLE CONTENT\n");

        for (var row : data.getRows()) {
            builder.append(String.join(" | ", row))
                    .append("\n");
        }

        return builder.toString();
    }

    @Override
    public String getFormatName() {
        return "PDF";
    }
}