package lab10.src.bank.statements;
import lab10.src.bank.common.Money;
import java.time.format.TextStyle;
import java.util.Locale;

public record PdfStatement(StatementData data) implements StatementDocument {

    @Override
    public String format() {
        return "PDF";
    }

    @Override
    public String fileName() {
        return "statement-" + data.period() + ".pdf";
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("PDF, сторінка 1\n");
        sb.append("Виписка за ")
                .append(data.period().getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.of("uk")))
                .append(' ').append(data.period().getYear()).append('\n');
        sb.append("Клієнт: ").append(data.client()).append('\n');
        for (Transaction t : data.operations()) {
            sb.append(String.format("  %s  %-28s %14s%n", t.date(), t.description(), Money.uah(t.amount())));
        }
        sb.append("Разом: надходження ").append(Money.uah(data.totals().income()))
                .append(", витрати ").append(Money.uah(data.totals().expenses().negate()));
        return sb.toString();
    }
}