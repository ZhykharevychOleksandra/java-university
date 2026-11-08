package lab10.src.bank.statements;
import lab10.src.bank.common.Client;
import lab10.src.bank.common.Log;
import lab10.src.bank.common.Money;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

public abstract class StatementGenerator {
    private final List<Transaction> ledger;

    protected StatementGenerator(List<Transaction> ledger) {
        this.ledger = ledger;
    }

    // адресат виписки (клієнт, податкова, бухгалтерія)
    protected abstract String recipient();

    // фабричний метод: підклас вирішує, який документ створити
    protected abstract StatementDocument createDocument(StatementData data);

    public final StatementDocument generate(Client client, YearMonth period) {
        String source = getClass().getSimpleName();
        Log.log(source, "Формування виписки за " + period + " для адресата «" + recipient() + "»");

        List<Transaction> operations = selectOperations(period);
        Log.log(source, "Вибрано операцій за період: " + operations.size());

        Totals totals = calculateTotals(operations);
        Log.log(source, "Підсумки: надходження " + Money.uah(totals.income())
                + ", витрати " + Money.uah(totals.expenses().negate())
                + ", різниця " + Money.uah(totals.net()));

        StatementDocument document = createDocument(new StatementData(client, period, operations, totals));
        Log.log(source, "Сформовано документ " + document.fileName() + " (формат " + document.format() + ")");

        send(document, source);
        return document;
    }

    private List<Transaction> selectOperations(YearMonth period) {
        return ledger.stream()
                .filter(t -> YearMonth.from(t.date()).equals(period))
                .toList();
    }

    private Totals calculateTotals(List<Transaction> operations) {
        BigDecimal income = BigDecimal.ZERO;
        BigDecimal expenses = BigDecimal.ZERO;
        for (Transaction t : operations) {
            if (t.amount().signum() >= 0) {
                income = income.add(t.amount());
            } else {
                expenses = expenses.add(t.amount());
            }
        }
        return new Totals(operations.size(), income, expenses);
    }

    private void send(StatementDocument document, String source) {
        Log.log(source, "Надіслано адресату «" + recipient() + "»: " + document.fileName());
    }
}
