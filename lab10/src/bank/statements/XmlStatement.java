package lab10.src.bank.statements;

public record XmlStatement(StatementData data) implements StatementDocument {

    @Override
    public String format() {
        return "XML";
    }

    @Override
    public String fileName() {
        return "statement-" + data.period() + ".xml";
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<statement period=\"").append(data.period())
                .append("\" client=\"").append(data.client()).append("\">\n");
        for (Transaction t : data.operations()) {
            sb.append("  <operation date=\"").append(t.date())
                    .append("\" amount=\"").append(t.amount().toPlainString()).append("\">")
                    .append(t.description()).append("</operation>\n");
        }
        sb.append("  <totals income=\"").append(data.totals().income().toPlainString())
                .append("\" expenses=\"").append(data.totals().expenses().toPlainString())
                .append("\" net=\"").append(data.totals().net().toPlainString()).append("\"/>\n");
        sb.append("</statement>");
        return sb.toString();
    }
}
