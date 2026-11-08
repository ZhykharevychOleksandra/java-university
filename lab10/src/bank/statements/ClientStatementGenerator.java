package lab10.src.bank.statements;
import java.util.List;

public class ClientStatementGenerator extends StatementGenerator {
    public ClientStatementGenerator(List<Transaction> ledger) {
        super(ledger);
    }

    @Override
    protected String recipient() {
        return "Клієнт";
    }

    @Override
    protected StatementDocument createDocument(StatementData data) {
        return new PdfStatement(data);
    }
}