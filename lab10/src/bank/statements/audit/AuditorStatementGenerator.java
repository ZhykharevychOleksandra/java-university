package lab10.src.bank.statements.audit;
import lab10.src.bank.statements.StatementData;
import lab10.src.bank.statements.StatementDocument;
import lab10.src.bank.statements.StatementGenerator;
import lab10.src.bank.statements.Transaction;
import java.util.List;

public class AuditorStatementGenerator extends StatementGenerator {
    public AuditorStatementGenerator(List<Transaction> ledger) {
        super(ledger);
    }

    @Override
    protected String recipient() {
        return "Аудитор";
    }

    @Override
    protected StatementDocument createDocument(StatementData data) {
        return new XlsxStatement(data);
    }
}
