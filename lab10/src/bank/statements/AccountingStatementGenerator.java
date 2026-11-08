package lab10.src.bank.statements;
import java.util.List;

public class AccountingStatementGenerator extends StatementGenerator {
    public AccountingStatementGenerator(List<Transaction> ledger) {
        super(ledger);
    }

    @Override
    protected String recipient() {
        return "Бухгалтерія підприємства";
    }

    @Override
    protected StatementDocument createDocument(StatementData data) {
        return new CsvStatement(data);
    }
}