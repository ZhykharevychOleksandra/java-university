package lab07.legacy.task2_ocp;
import java.util.List;

public class ExcelExportStrategy implements ExportStrategy {

    @Override
    public String export(ReportData data) {

        StringBuilder builder = new StringBuilder();

        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<Workbook>\n");

        builder.append("  <Worksheet name=\"")
                .append(escapeXml(data.getTitle()))
                .append("\">\n");

        builder.append("    <Table>\n");

        // Заголовки таблиці
        builder.append("      <Row>\n");

        for (String header : data.getHeaders()) {

            builder.append("        <Cell><Data>")
                    .append(escapeXml(header))
                    .append("</Data></Cell>\n");
        }

        builder.append("      </Row>\n");

        // Дані таблиці
        for (List<String> row : data.getRows()) {

            builder.append("      <Row>\n");

            for (String cell : row) {

                builder.append("        <Cell><Data>")
                        .append(escapeXml(cell))
                        .append("</Data></Cell>\n");
            }

            builder.append("      </Row>\n");
        }

        builder.append("    </Table>\n");
        builder.append("  </Worksheet>\n");
        builder.append("</Workbook>\n");

        return builder.toString();
    }

    // Екранування спеціальних символів XML
    private String escapeXml(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    @Override
    public String getFormatName() {
        return "EXCEL";
    }
}