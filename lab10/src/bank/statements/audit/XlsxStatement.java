package lab10.src.bank.statements.audit;
import lab10.src.bank.common.Money;
import lab10.src.bank.statements.StatementData;
import lab10.src.bank.statements.StatementDocument;
import lab10.src.bank.statements.Transaction;

public record XlsxStatement(StatementData data) implements StatementDocument {

    @Override
    public String format() {
        return "XLSX";
    }

    @Override
    public String fileName() {
        return "statement-" + data.period() + ".xlsx";
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder("XLSX, аркуш «Операції»\n");
        sb.append(String.format("%-12s | %-28s | %14s%n", "Дата", "Опис", "Сума"));
        for (Transaction t : data.operations()) {
            sb.append(String.format("%-12s | %-28s | %14s%n", t.date(), t.description(), Money.uah(t.amount())));
        }
        sb.append("XLSX, аркуш «Підсумки»: операцій ").append(data.totals().count())
                .append(", різниця ").append(Money.uah(data.totals().net()));
        return sb.toString();
    }
}
