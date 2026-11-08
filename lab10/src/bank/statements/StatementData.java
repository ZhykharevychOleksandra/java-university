package lab10.src.bank.statements;
import lab10.src.bank.common.Client;
import java.time.YearMonth;
import java.util.List;

// дані виписки, з яких формувач будує документ будь-якого формату
public record StatementData(Client client, YearMonth period, List<Transaction> operations, Totals totals) {
}