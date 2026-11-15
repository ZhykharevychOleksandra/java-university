package lab12.prutcredit.composite;
import lab12.prutcredit.common.EventLog;
import lab12.prutcredit.common.Money;

public final class TreasuryReport {

    private TreasuryReport() {
    }

    public static void report(BankUnit unit) {
        EventLog.log("Казначейство", String.format("Звіт по «%s» (%s): залишок %s ₴, операцій за день: %d",
                unit.getName(), unit.getKind(),
                Money.uah(unit.getCashBalance()), unit.getOperationsCount()));
    }
}