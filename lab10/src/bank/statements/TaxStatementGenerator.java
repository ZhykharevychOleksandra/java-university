package lab10.src.bank.statements;
import java.util.List;

public class TaxStatementGenerator extends StatementGenerator {
    public TaxStatementGenerator(List<Transaction> ledger) {
        super(ledger);
    }

    @Override
    protected String recipient() {
        return "Податкова";
    }

    @Override
    protected StatementDocument createDocument(StatementData data) {
        return new XmlStatement(data);
    }
}