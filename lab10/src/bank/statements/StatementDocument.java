package lab10.src.bank.statements;

public interface StatementDocument {
    String format();
    String fileName();
    String render();
}