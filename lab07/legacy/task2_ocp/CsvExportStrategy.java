package lab07.legacy.task2_ocp;
import java.util.List;

public class CsvExportStrategy implements ExportStrategy {

    @Override
    public String export(ReportData data) {

        StringBuilder builder = new StringBuilder();

        builder.append(formatRow(data.getHeaders()));

        for (List<String> row : data.getRows()) {
            builder.append(formatRow(row));
        }

        return builder.toString();
    }

    private String formatRow(List<String> values) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.size(); i++) {

            String value = values.get(i);

            if (value == null) {
                value = "";
            }

            // Екранування лапок
            value = value.replace("\"", "\"\"");

            result.append("\"")
                    .append(value)
                    .append("\"");

            if (i < values.size() - 1) {
                result.append(",");
            }
        }

        result.append("\n");

        return result.toString();
    }

    @Override
    public String getFormatName() {
        return "CSV";
    }
}