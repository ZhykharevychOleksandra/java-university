package lab09.src.bank;
import lab09.src.bank.singleton.*;
import lab09.src.bank.objectpool.*;
import lab09.src.bank.prototype.*;

public class MainBankLab {
    public static void main(String[] args) {
        System.out.println("--- ЛАБОРАТОРНА РОБОТА №9 ---");
        System.out.println("Банк «Прут-Кредит»");

        runSingletonScenario();
        runObjectPoolScenario();
        runPrototypeScenario();

        System.out.println("\n--- ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО ---");
    }

    // завдання 1 — singleton 
    public static void runSingletonScenario() {

        Logger.log("MAIN", "ЗАВДАННЯ 1. Singleton");

        ExchangeRateService treasury =
                ExchangeRateService.getInstance();

        treasury.setRate("USD", 41.20, 41.70);

        ExchangeRateService branch3 =
                ExchangeRateService.getInstance();

        ExchangeRateService branch5 =
                ExchangeRateService.getInstance();

        ExchangeRateService onlineBank =
                ExchangeRateService.getInstance();

        Logger.log("Відділення №3",
                "USD: " + branch3.getRate("USD"));

        Logger.log("Відділення №5",
                "USD: " + branch5.getRate("USD"));

        Logger.log("Онлайн-банкінг",
                "USD: " + onlineBank.getRate("USD"));

        treasury.setRate("USD", 41.50, 42.00);

        Logger.log("Відділення №3",
                "Новий USD: " + branch3.getRate("USD"));

        Logger.log("Відділення №5",
                "Новий USD: " + branch5.getRate("USD"));

        Logger.log("Онлайн-банкінг",
                "Новий USD: " + onlineBank.getRate("USD"));

        Logger.log("MAIN",
                "ID казначейства: " +
                System.identityHashCode(treasury));

        Logger.log("MAIN",
                "ID відділення №3: " +
                System.identityHashCode(branch3));

        Logger.log("MAIN",
                "ID відділення №5: " +
                System.identityHashCode(branch5));

        Logger.log("MAIN",
                "ID онлайн-банкінгу: " +
                System.identityHashCode(onlineBank));
    }

    // завдання 2 — object pool 

    public static void runObjectPoolScenario() {

        Logger.log("MAIN", "ЗАВДАННЯ 2. Object Pool");

        PaymentConnectionPool pool =
                new PaymentConnectionPool(4);

        for (int i = 1; i <= 7; i++) {

            Payment payment = new Payment(
                    1000 + i,
                    (i * 3000) + 3000,
                    "UA" + (100000 + i),
                    "UA" + (200000 + i)
            );

            pool.submit(payment);
        }

        Logger.log("MAIN",
                "Початкова кількість з'єднань: " +
                pool.getCreatedConnections());

        while (pool.hasActivePayments()) {
            pool.completeNext();
        }

        pool.printStatistics();
    }

    // завдання 3 — prototype 

    public static void runPrototypeScenario() {

        Logger.log("MAIN", "ЗАВДАННЯ 3. Prototype");

        Contract template = Contract.createTemplate();

        Contract contract1 =
                template.cloneForCustomer("Іваненко Іван", 50000);

        Contract contract2 =
                template.cloneForCustomer("Петренко Петро", 75000);

        Contract contract3 =
                template.cloneForCustomer("Коваленко Олена", 100000);

        contract1.addAgreement(
                new AdditionalAgreement(
                        "Дострокове зняття коштів дозволено"
                )
        );

        contract1.setAmount(60000);

        contract1.getSchedule().changePayment(
                0, "Змінений платіж: 60000 грн"
        );

        Logger.log("MAIN", "--- TEMPLATE CONTRACT ---"); 
        System.out.println(template);

        Logger.log("MAIN", "--- ДОГОВІР ІВАНЕНКА ---");
        System.out.println(contract1);

        Logger.log("MAIN", "--- ДОГОВІР ПЕТРЕНКА ---");
        System.out.println(contract2);

        Logger.log("MAIN", "--- ДОГОВІР КОВАЛЕНКО ---");
        System.out.println(contract3);
    }
}