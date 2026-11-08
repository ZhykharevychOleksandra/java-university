package lab10.src.bank.statements;

public record CsvStatement(StatementData data) implements StatementDocument {

    @Override
    public String format() {
        return "CSV";
    }

    @Override
    public String fileName() {
        return "statement-" + data.period() + ".csv";
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder("date;description;amount\n");
        for (Transaction t : data.operations()) {
            sb.append(t.date()).append(';').append(t.description()).append(';')
                    .append(t.amount().toPlainString()).append('\n');
        }
        sb.append("TOTAL_INCOME;;").append(data.totals().income().toPlainString()).append('\n');
        sb.append("TOTAL_EXPENSES;;").append(data.totals().expenses().toPlainString());
        return sb.toString();
    }
}