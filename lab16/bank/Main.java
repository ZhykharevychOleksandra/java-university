package lab16.bank;
import static lab16.bank.common.Log.header;
import static lab16.bank.common.Log.log;
import static lab16.bank.common.Log.uah;
import lab16.bank.iterator.Account;
import lab16.bank.iterator.Client;
import lab16.bank.iterator.Transaction;
import lab16.bank.iterator.TransactionIterator;
import lab16.bank.memento.LedgerBook;
import lab16.bank.memento.Treasury;
import lab16.bank.visitor.*;
import java.time.LocalDate;

public class Main {
    private static int seq = 0;
    public static void main(String[] args) {
        situation1Iterator();
        situation2Memento();
        situation3Visitor();
    }

    // завдання 1: iterator 
    private static Transaction tx(Account a, String date, long amount, String desc) {
        Transaction t = new Transaction(String.format("T%02d", ++seq), a.getNumber(), LocalDate.parse(date), amount, desc);
        a.addOperation(t);
        return t;
    }

    private static void situation1Iterator() {
        header("Завдання 1. Обхід операцій клієнта (Iterator)");
        Client client = new Client("Іваненко І.І.");
        Account current = new Account("UA11-поточний");
        Account saving = new Account("UA22-накопичувальний");
        client.addAccount(current);
        client.addAccount(saving);
        tx(current, "2025-11-05", 85_000, "зарплата");
        tx(current, "2025-12-20", -12_000, "оплата послуг");
        tx(current, "2026-01-10", 650_000, "продаж авто");
        tx(current, "2026-02-14", -3_500, "ресторан");
        tx(current, "2026-03-01", 90_000, "зарплата");
        tx(current, "2026-03-15", -420_000, "купівля обладнання");
        tx(current, "2026-05-22", -7_800, "техніка");
        tx(current, "2026-08-30", 95_000, "зарплата");
        tx(current, "2026-10-12", -15_000, "оренда");
        tx(saving, "2025-12-01", 200_000, "внесок");
        tx(saving, "2026-02-02", 500_000, "переказ із депозиту");
        tx(saving, "2026-04-18", -50_000, "зняття");
        tx(saving, "2026-06-09", 410_000, "надходження від продажу");
        tx(saving, "2026-09-21", -1_200, "комісія");
        tx(saving, "2026-11-03", 30_000, "внесок");
        tx(saving, "2026-11-15", -25_000, "зняття");

        log("Сховище", "Структура ДО обходів: " + client.describeStructure());

        TransactionIterator recent = client.newestFirst(10);
        log("Онлайн-банкінг", "Обхід «" + recent.name() + "», курсор: віддано " + recent.returned());
        while (recent.hasNext()) log("Онлайн-банкінг", recent.next().toString());
        log("Онлайн-банкінг", "Обхід завершено, віддано " + recent.returned());

        TransactionIterator year = client.yearChronological(2026);
        log("Податковий звіт", "Обхід «" + year.name() + "», віддано " + year.returned());
        while (year.hasNext()) log("Податковий звіт", year.next().toString());
        log("Податковий звіт", "Обхід завершено, віддано " + year.returned());

        TransactionIterator aml = client.aboveAmount(400_000);
        TransactionIterator other = client.newestFirst(3);
        log("Фінмоніторинг", "Старт «" + aml.name() + "» і паралельно «" + other.name() + "» (незалежні курсори)");
        while (aml.hasNext() || other.hasNext()) {
            if (aml.hasNext()) log("Фінмоніторинг", aml.next().toString());
            if (other.hasNext()) log("Онлайн-банкінг", other.next().toString());
        }
        log("Фінмоніторинг", "Віддано: " + aml.returned() + "; другий обхід: " + other.returned());
        log("Сховище", "Структура ПІСЛЯ обходів: " + client.describeStructure() + " (колекція не змінена)");
    }

    // завдання 2: memento 
    private static void situation2Memento() {
        header("Завдання 2. Відкат операційного дня (Memento)");
        LedgerBook ledger = new LedgerBook();
        ledger.open("1001-каса", 1_000_000);
        ledger.open("1002-резерви", 500_000);
        ledger.open("2001-кошти клієнтів", 250_000);
        Treasury treasury = new Treasury();

        log("Книга рахунків", "Початковий стан: " + ledger.describe());
        treasury.checkpoint(ledger, "09:00 початок дня");
        log("Казначейство", "Збережено контрольну точку «09:00 початок дня»");

        ledger.adjust("1001-каса", -200_000);
        ledger.adjust("2001-кошти клієнтів", 200_000);
        log("Казначейство", "Проведено операції. Стан: " + ledger.describe());
        treasury.checkpoint(ledger, "13:00 після операцій");
        log("Казначейство", "Збережено контрольну точку «13:00 після операцій»; точки: " + treasury.labels());

        log("Казначейство", "Стан ДО помилкових коригувань: " + ledger.describe());
        ledger.adjust("1001-каса", 5_000_000);
        ledger.adjust("1002-резерви", -499_000);
        log("Казначейство", "Помилкові коригування. Стан ПІСЛЯ: " + ledger.describe());

        treasury.rollbackTo(ledger, "13:00 після операцій");
        log("Казначейство", "Відновлено «13:00 після операцій». Залишки: " + ledger.describe());
        treasury.rollbackTo(ledger, "09:00 початок дня");
        log("Казначейство", "Відновлено «09:00 початок дня». Залишки: " + ledger.describe());
    }

    // завдання 3: visitor 
    private static void situation3Visitor() {
        header("Завдання 3. Операції над банківськими продуктами (Visitor)");
        Portfolio portfolio = new Portfolio("Іваненко І.І.")
                .add(new Deposit("DEP-1", 300_000, 0.12))
                .add(new Loan("LOAN-1", 800_000, 0.18, 45))
                .add(new Card("CARD-1", 100_000, 40_000, 0.36))
                .add(new InvestmentAccount("INV-1", 500_000, 0.01));
        log("Портфель", "Стан продуктів ДО: " + portfolio.describe());

        ReserveVisitor reserves = new ReserveVisitor();
        portfolio.accept(reserves);
        log("Резерви НБУ", "Разом резерв: " + uah(reserves.getTotal()));

        ProfitabilityVisitor profit = new ProfitabilityVisitor();
        portfolio.accept(profit);
        log("Прибутковість", "Разом річна прибутковість для банку: " + uah(profit.getTotal()));

        log("Розробка", "Додаємо нову операцію «звіт для клієнта» — один новий клас, продукти не чіпаємо");
        ClientReportVisitor report = new ClientReportVisitor();
        portfolio.accept(report);
        log("Звіт клієнта", "Сформовано звіт, рядків: " + report.getReport().split("\n").length);

        log("Портфель", "Стан продуктів ПІСЛЯ: " + portfolio.describe());
    }
}