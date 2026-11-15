package lab12.prutcredit;
import lab12.prutcredit.bridge.AccountStatement;
import lab12.prutcredit.bridge.BankDocument;
import lab12.prutcredit.bridge.DeliveryChannel;
import lab12.prutcredit.bridge.DiiaChannel;
import lab12.prutcredit.bridge.PaymentOrder;
import lab12.prutcredit.bridge.PaymentReceipt;
import lab12.prutcredit.bridge.PdfChannel;
import lab12.prutcredit.bridge.PrintChannel;
import lab12.prutcredit.bridge.SignedElectronicChannel;
import lab12.prutcredit.common.EventLog;
import lab12.prutcredit.composite.Cashier;
import lab12.prutcredit.composite.OrganizationUnit;
import lab12.prutcredit.composite.TreasuryReport;
import lab12.prutcredit.composite.UnitLevel;
import lab12.prutcredit.flyweight.Merchant;
import lab12.prutcredit.flyweight.MerchantFactory;
import lab12.prutcredit.flyweight.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Лабораторної роботи №12, варіант 7 — Банк «Прут-Кредит»
public class Main {

    public static void main(String[] args) {
        task1CompositeBankStructure();
        task2BridgeDocuments();
        task3FlyweightTransactionHistory();
    }

    // завдання 1. структура банку і звітність — composite 
    static void task1CompositeBankStructure() {
        EventLog.section("Завдання 1. Структура банку і звітність (Composite)");
        EventLog.setTime(9, 0);

        OrganizationUnit bank = new OrganizationUnit("Прут-Кредит", UnitLevel.HEAD_OFFICE);

        // 1: відділення №1 (+ міні-відділення в ньому) і відділення №2
        OrganizationUnit region1 = new OrganizationUnit("Чернівецьке управління", UnitLevel.REGION);
        OrganizationUnit branch1 = new OrganizationUnit("Відділення №1", UnitLevel.BRANCH);
        branch1.add(new Cashier("Каса 1.1", 420_000, 118))
                .add(new Cashier("Каса 1.2", 380_000, 97));
        OrganizationUnit mini1 = new OrganizationUnit("Міні-відділення №1-М", UnitLevel.MINI_BRANCH);
        mini1.add(new Cashier("Каса 1-М.1", 150_000, 41));
        branch1.add(mini1);

        OrganizationUnit branch2 = new OrganizationUnit("Відділення №2", UnitLevel.BRANCH);
        branch2.add(new Cashier("Каса 2.1", 510_000, 142))
                .add(new Cashier("Каса 2.2", 275_000, 63));
        region1.add(branch1).add(branch2);

        // 2: відділення №3, у ньому міні-відділення, а в тому — ще одне (глибша вкладеність)
        OrganizationUnit region2 = new OrganizationUnit("Івано-Франківське управління", UnitLevel.REGION);
        OrganizationUnit branch3 = new OrganizationUnit("Відділення №3", UnitLevel.BRANCH);
        branch3.add(new Cashier("Каса 3.1", 600_000, 160))
                .add(new Cashier("Каса 3.2", 330_000, 88));
        OrganizationUnit mini3a = new OrganizationUnit("Міні-відділення №3-А", UnitLevel.MINI_BRANCH);
        mini3a.add(new Cashier("Каса 3-А.1", 120_000, 35));
        OrganizationUnit mini3a1 = new OrganizationUnit("Міні-відділення №3-А1", UnitLevel.MINI_BRANCH);
        mini3a1.add(new Cashier("Каса 3-А1.1", 90_000, 22));
        mini3a.add(mini3a1);
        branch3.add(mini3a);
        region2.add(branch3);

        bank.add(region1).add(region2);
        EventLog.log("Структура", "Зібрано: банк → 2 регіональні управління → відділення (з вкладеними міні-відділеннями) → каси");

        EventLog.log("Структура", "Дерево банку із залишком і кількістю операцій кожного вузла:");
        bank.printTree(EventLog::raw);

        EventLog.log("Казначейство", "Той самий виклик TreasuryReport.report() для різних вузлів:");
        TreasuryReport.report(bank);
        TreasuryReport.report(region1);
        TreasuryReport.report(mini3a);          // міні-відділення з вкладеним міні-відділенням
        TreasuryReport.report(new Cashier("Каса-одинак", 10_000, 3)); // окрема каса — той самий інтерфейс

        // Підпорядкування відділення відділенню — без змін у коді звітності
        OrganizationUnit mini2 = new OrganizationUnit("Міні-відділення №2-М", UnitLevel.MINI_BRANCH);
        mini2.add(new Cashier("Каса 2-М.1", 80_000, 19));
        branch2.add(mini2);
        EventLog.log("Структура", "Міні-відділення №2-М підпорядковано відділенню №2 (код звітності не змінювався)");
        TreasuryReport.report(branch2);
        TreasuryReport.report(bank);
    }

    // завдання 2. платіжні документи — bridge 
    static void task2BridgeDocuments() {
        EventLog.section("Завдання 2. Платіжні документи (Bridge)");
        EventLog.setTime(10, 0);

        DeliveryChannel print = new PrintChannel("Відділення №1");
        DeliveryChannel pdf = new PdfChannel();
        DeliveryChannel signed = new SignedElectronicChannel();

        // 1) квитанція: для друку і в pdf (спосіб змінюється під час виконання)
        PaymentReceipt receipt = new PaymentReceipt("R-1001", "Іваненко О. П.",
                "оплата комунальних послуг", 125_050, print);
        receipt.issue();
        receipt.setChannel(pdf);
        EventLog.log("Раннер", "Для тієї ж квитанції під час виконання змінено спосіб надання на PDF");
        receipt.issue();

        // 2) довідка про стан рахунку з цифровим підписом
        AccountStatement statement = new AccountStatement("UA21 3052 9900 0002 6001 0000 1234",
                8_450_075, "15.11.2026", signed);
        statement.issue();

        // 3) новий спосіб «надсилання в Дію» — один новий клас DiiaChannel
        DeliveryChannel diia = new DiiaChannel();
        EventLog.log("Раннер", "Додано новий спосіб надання: DiiaChannel (1 новий клас, жоден документ не змінено)");
        statement.setChannel(diia);
        statement.issue();

        // перевірка комбінацій: будь-який вид × будь-який спосіб
        EventLog.log("Раннер", "Перевірка матриці: 3 види документів × 4 способи = 12 комбінацій без окремих класів");
        List<BankDocument> documents = List.of(
                new PaymentOrder("P-2001", "ТОВ «Буковина-Агро»", "ДПС України", 4_500_000, print),
                receipt,
                statement);
        List<DeliveryChannel> channels = List.of(print, pdf, signed, diia);
        int combos = 0;
        for (BankDocument document : documents) {
            for (DeliveryChannel channel : channels) {
                document.setChannel(channel);
                document.issue();
                combos++;
            }
        }
        EventLog.log("Раннер", "Виконано комбінацій: " + combos
                + ". Класів: 3 види + 4 способи = 7 (без Bridge було б 3 × 4 = 12)");
    }

    // завдання 3. історія операцій — flyweight
    private record MerchantSpec(String id, String name, String logo, String category, int mcc, String address) {
    }

    static void task3FlyweightTransactionHistory() {
        EventLog.section("Завдання 3. Історія операцій (Flyweight)");
        EventLog.setTime(11, 0);

        List<MerchantSpec> specs = merchantSpecs();
        String[] accounts = {
                "UA21 3052 9900 0002 6001 0000 1234",
                "UA55 3052 9900 0002 6002 0000 5678",
                "UA89 3052 9900 0002 6003 0000 9012"
        };

        MerchantFactory factory = new MerchantFactory();
        Random random = new Random(42);
        LocalDate start = LocalDate.of(2026, 11, 1);

        EventLog.log("Історія", "Формування історії: 50 000 операцій по " + specs.size() + " торговцях");
        List<Transaction> history = new ArrayList<>(50_000);
        for (int i = 1; i <= 50_000; i++) {
            MerchantSpec spec = specs.get(random.nextInt(specs.size()));
            Merchant merchant = factory.getMerchant(spec.id(), spec.name(), spec.logo(),
                    spec.category(), spec.mcc(), spec.address());
            long amount = 1_000 + random.nextInt(500_000); // у копійках
            LocalDate date = start.plusDays(random.nextInt(15));
            String account = accounts[random.nextInt(accounts.length)];
            history.add(new Transaction(i, amount, date, account, merchant));
        }
        EventLog.log("Історія", "Історію сформовано");

        EventLog.log("Лічильник", "Кількість операцій: " + history.size());
        EventLog.log("Лічильник", "Запитів до фабрики торговців: " + factory.getRequests());
        EventLog.log("Лічильник", "Кількість об'єктів торговців у пулі: " + factory.getPoolSize());
        EventLog.log("Лічильник", "Фактично створено об'єктів Merchant: " + Merchant.getInstanceCount()
                + " (без Flyweight було б " + history.size() + ")");
        if (Merchant.getInstanceCount() != 20) {
            EventLog.log("Лічильник", "УВАГА: очікувалось 20 об'єктів торговців");
        }

        // дві операції в одному супермаркеті посилаються на той самий об'єкт
        List<Transaction> inSupermarket = new ArrayList<>();
        for (Transaction t : history) {
            if (t.getMerchant().getId().equals("silpo")) {
                inSupermarket.add(t);
                if (inSupermarket.size() == 2) {
                    break;
                }
            }
        }
        Transaction first = inSupermarket.get(0);
        Transaction second = inSupermarket.get(1);
        EventLog.log("Історія", "Перша операція у супермаркеті: " + first);
        EventLog.log("Історія", "Друга операція у супермаркеті: " + second);
        EventLog.log("Перевірка", "Об'єкт торговця (перша операція): @"
                + Integer.toHexString(System.identityHashCode(first.getMerchant())));
        EventLog.log("Перевірка", "Об'єкт торговця (друга операція): @"
                + Integer.toHexString(System.identityHashCode(second.getMerchant())));
        EventLog.log("Перевірка", "first.getMerchant() == second.getMerchant() → "
                + (first.getMerchant() == second.getMerchant()));

        // повторний запит уже використаного торговця не створює нового об'єкта
        int before = Merchant.getInstanceCount();
        MerchantSpec silpo = specs.get(0);
        Merchant again = factory.getMerchant(silpo.id(), silpo.name(), silpo.logo(),
                silpo.category(), silpo.mcc(), silpo.address());
        EventLog.log("Перевірка", "Повторний запит торговця «" + again.getName() + "»: об'єктів було "
                + before + ", стало " + Merchant.getInstanceCount()
                + "; той самий об'єкт: " + (again == first.getMerchant()));
    }

    private static List<MerchantSpec> merchantSpecs() {
        return List.of(
                new MerchantSpec("silpo", "Сільпо", "logos/silpo.png", "Супермаркети", 5411, "мережа, вул. Головна, 12, Чернівці"),
                new MerchantSpec("atb", "АТБ-Маркет", "logos/atb.png", "Супермаркети", 5411, "мережа, вул. Руська, 87, Чернівці"),
                new MerchantSpec("fora", "Фора", "logos/fora.png", "Супермаркети", 5411, "мережа, просп. Незалежності, 5, Чернівці"),
                new MerchantSpec("okko", "ОККО", "logos/okko.png", "АЗС", 5541, "мережа, вул. Хотинська, 3, Чернівці"),
                new MerchantSpec("wog", "WOG", "logos/wog.png", "АЗС", 5541, "мережа, вул. Сторожинецька, 40, Чернівці"),
                new MerchantSpec("novapost", "Нова Пошта", "logos/novapost.png", "Поштові послуги", 4215, "мережа, вул. Червоноармійська, 9, Чернівці"),
                new MerchantSpec("kyivstar", "Київстар", "logos/kyivstar.png", "Телеком", 4814, "онлайн-сервіс"),
                new MerchantSpec("anc", "Аптека АНЦ", "logos/anc.png", "Аптеки", 5912, "мережа, вул. Ольги Кобилянської, 21, Чернівці"),
                new MerchantSpec("mcd", "McDonald's", "logos/mcd.png", "Фастфуд", 5814, "мережа, вул. Головна, 60, Чернівці"),
                new MerchantSpec("kfc", "KFC", "logos/kfc.png", "Фастфуд", 5814, "мережа, вул. Українська, 18, Чернівці"),
                new MerchantSpec("rozetka", "Rozetka", "logos/rozetka.png", "Електроніка", 5732, "інтернет-магазин"),
                new MerchantSpec("comfy", "Comfy", "logos/comfy.png", "Електроніка", 5732, "мережа, вул. Герцена, 2, Чернівці"),
                new MerchantSpec("epicentr", "Епіцентр", "logos/epicentr.png", "Будматеріали", 5200, "мережа, вул. Каспрука, 1, Чернівці"),
                new MerchantSpec("reserved", "Reserved", "logos/reserved.png", "Одяг", 5651, "ТРЦ «Депот», Чернівці"),
                new MerchantSpec("eva", "EVA", "logos/eva.png", "Косметика", 5977, "мережа, вул. Шевченка, 7, Чернівці"),
                new MerchantSpec("uklon", "Uklon", "logos/uklon.png", "Таксі", 4121, "онлайн-сервіс"),
                new MerchantSpec("planeta", "Планета Кіно", "logos/planeta.png", "Кінотеатри", 7832, "ТРЦ «Депот», Чернівці"),
                new MerchantSpec("yakaboo", "Yakaboo", "logos/yakaboo.png", "Книги", 5942, "інтернет-магазин"),
                new MerchantSpec("buhtyv", "Чернівціводоканал", "logos/vodokanal.png", "Комунальні послуги", 4900, "вул. Фастівська, 4, Чернівці"),
                new MerchantSpec("toys", "Дитячий світ", "logos/toys.png", "Іграшки", 5945, "мережа, вул. Універсальна, 11, Чернівці"));
    }
}
