package lab10.src.bank.products;
import lab10.src.bank.common.Client;

// пакет обслуговування клієнта: картка + поточний рахунок + тарифний план
public record ServicePackage(Client client, Card card, CurrentAccount account, TariffPlan tariff) {
    @Override
    public String toString() {
        return "Пакет обслуговування клієнта " + client + "\n"
                + "  Картка:          " + card.describe() + "\n"
                + "  Поточний рахунок: " + account.describe() + "\n"
                + "  Тарифний план:   " + tariff.describe();
    }
}