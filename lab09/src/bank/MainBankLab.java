package bank;
import bank.common.Log;
import bank.contracts.Addendum;
import bank.contracts.Client;
import bank.contracts.DepositContract;
import bank.payments.ConnectionPool;
import bank.payments.Payment;
import bank.rates.Branch;
import bank.rates.ExchangeRateService;
import bank.rates.OnlineBanking;
import bank.rates.Treasury;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class MainBankLab {
    private static final String SRC = "Раннер";
    public static void main(String[] args) {
        situation1ExchangeRates();
        situation2PaymentConnections();
        situation3Contracts();
    }

    // singleton 
    static void situation1ExchangeRates() {
        Log.log(SRC, "--- Завдання 1. Курси валют (Singleton) ---");
        Treasury treasury = new Treasury();
        Branch branch3 = new Branch(3);
        Branch branch5 = new Branch(5);
        OnlineBanking online = new OnlineBanking();

        treasury.setRate("USD", new BigDecimal("41.20"), new BigDecimal("41.70"));
        List<String> ids = new ArrayList<>();
        ids.add(branch3.showRate("USD"));
        ids.add(branch5.showRate("USD"));
        ids.add(online.showRate("USD"));

        Log.log(SRC, "казначейство змінює курс");
        treasury.setRate("USD", new BigDecimal("41.35"), new BigDecimal("41.85"));
        ids.add(branch3.showRate("USD"));
        ids.add(branch5.showRate("USD"));
        ids.add(online.showRate("USD"));

        boolean sameId = ids.stream().allMatch(ids.get(0)::equals);
        boolean sameObject = ExchangeRateService.getInstance() == ExchangeRateService.getInstance();
        Log.log(SRC, "перевірка: ідентифікатор служби однаковий з усіх місць — " + (sameId ? "так" : "НІ")
                + "; getInstance() повертає один об'єкт — " + (sameObject ? "так" : "НІ"));
    }

    // object pool 
    static void situation2PaymentConnections() {
        Log.log(SRC, "--- Завдання 2. З'єднання з платіжною системою (Object Pool) ---");
        ConnectionPool pool = new ConnectionPool(4);
        long[] amounts = {8_500, 22_000, 3_200, 15_000, 47_500, 1_250, 9_900};
        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < amounts.length; i++) {
            payments.add(new Payment(1001 + i, amounts[i], "UA01-" + (100 + i), "UA99-" + (500 + i)));
        }

        Log.log(SRC, "7 платежів надходять одночасно");
        for (Payment p : payments) {
            pool.submit(p);
        }
        Log.log(SRC, "стан: у роботі " + pool.busyCount() + ", у черзі " + pool.waitingCount()
                + ", встановлено з'єднань " + pool.establishedCount());

        Log.log(SRC, "платіжна система підтверджує платежі по черзі");
        for (Payment p : payments) {
            pool.complete(p);
        }
        pool.printStatistics();
    }

    // prototype 
    static void situation3Contracts() {
        Log.log(SRC, "--- Завдання 3. Оформлення договорів (Prototype) ---");
        DepositContract template = DepositContract.createTemplate(
                "Депозит Стабільний, 12 міс.", new BigDecimal("14.00"), 12);

        Client k1 = new Client("Коваленко Олена Петрівна", List.of("UA01-0001"));
        Client k2 = new Client("Шевченко Ігор Андрійович", List.of("UA01-0002"));
        Client k3 = new Client("Мельник Оксана Василівна", List.of("UA01-0003"));

        DepositContract c1 = template.issueFor(k1, new BigDecimal("100000"));
        DepositContract c2 = template.issueFor(k2, new BigDecimal("50000"));
        DepositContract c3 = template.issueFor(k3, new BigDecimal("250000"));

        String templateBefore = template.snapshot();
        String c2Before = c2.snapshot();
        String c3Before = c3.snapshot();

        Log.log(SRC, "у договорі " + c1.getId() + " додаємо додаткову угоду, змінюємо графік виплат і суму");
        c1.addAddendum(new Addendum("ДУ-1", "Автоматична пролонгація на 12 міс."));
        c1.getSchedule().get(0).setPayoutPercent(new BigDecimal("2.5000"));
        c1.setAmount(new BigDecimal("300000"));

        Log.log(SRC, "типовий договір (ліворуч) і договір клієнта " + c1.getId() + " (праворуч):");
        printSideBySide(template.describe(3), c1.describe(3));

        Log.log(SRC, "перевірка: типовий договір не змінився — " + yesNo(templateBefore.equals(template.snapshot())));
        Log.log(SRC, "перевірка: договір " + c2.getId() + " не змінився — " + yesNo(c2Before.equals(c2.snapshot())));
        Log.log(SRC, "перевірка: договір " + c3.getId() + " не змінився — " + yesNo(c3Before.equals(c3.snapshot())));
    }

    private static void printSideBySide(List<String> left, List<String> right) {
        int rows = Math.max(left.size(), right.size());
        for (int i = 0; i < rows; i++) {
            String l = i < left.size() ? left.get(i) : "";
            String r = i < right.size() ? right.get(i) : "";
            Log.log("Звіт", String.format("%-72s | %s", l, r));
        }
    }

    private static String yesNo(boolean value) {
        return value ? "так" : "НІ";
    }
}