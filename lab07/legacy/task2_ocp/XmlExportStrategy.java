package lab07.legacy.task2_ocp;

public class XmlExportStrategy implements ExportStrategy {
    @Override
    public String export(ReportData data) {

        if (data == null) {
            throw new IllegalArgumentException(
                    "Report data cannot be null"
            );
        }

        StringBuilder builder = new StringBuilder();

        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<report>\n");

        builder.append("  <title>")
                .append(escapeXml(data.getTitle()))
                .append("</title>\n");

        builder.append("  <generatedAt>")
                .append(data.getGeneratedAt())
                .append("</generatedAt>\n");

        builder.append("  <totalRows>")
                .append(data.getRows().size())
                .append("</totalRows>\n");

        builder.append("</report>\n");

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
        return "XML";
    }
}