package lab07.legacy.task2_ocp;

public class JsonExportStrategy implements ExportStrategy {
    @Override
    public String export(ReportData data) {

        if (data == null) {
            throw new IllegalArgumentException(
                    "Report data cannot be null"
            );
        }

        StringBuilder builder = new StringBuilder();

        builder.append("{\n");

        builder.append("  \"title\": \"")
                .append(escapeJson(data.getTitle()))
                .append("\",\n");

        builder.append("  \"generatedAt\": \"")
                .append(escapeJson(String.valueOf(data.getGeneratedAt())))
                .append("\",\n");

        builder.append("  \"headers\": [");

        for (int i = 0; i < data.getHeaders().size(); i++) {

            builder.append("\"")
                    .append(escapeJson(data.getHeaders().get(i)))
                    .append("\"");

            if (i < data.getHeaders().size() - 1) {
                builder.append(", ");
            }
        }

        builder.append("],\n");
        builder.append("  \"rows\": [\n");

        for (int i = 0; i < data.getRows().size(); i++) {

            builder.append("    [");

            var row = data.getRows().get(i);

            for (int j = 0; j < row.size(); j++) {

                builder.append("\"")
                        .append(escapeJson(row.get(j)))
                        .append("\"");

                if (j < row.size() - 1) {
                    builder.append(", ");
                }
            }

            builder.append("]");

            if (i < data.getRows().size() - 1) {
                builder.append(",");
            }

            builder.append("\n");
        }

        builder.append("  ]\n");
        builder.append("}\n");

        return builder.toString();
    }

    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    @Override
    public String getFormatName() {
        return "JSON";
    }
}