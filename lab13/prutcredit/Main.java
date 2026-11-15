package lab13.prutcredit;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lab13.prutcredit.approval.ApprovalChain;
import lab13.prutcredit.approval.LimitApprover;
import lab13.prutcredit.approval.LoanApplication;
import lab13.prutcredit.approval.SecurityCheckHandler;
import lab13.prutcredit.common.EventLog;
import lab13.prutcredit.common.Money;
import lab13.prutcredit.common.SimClock;
import lab13.prutcredit.payments.BankAccount;
import lab13.prutcredit.payments.DepositTopUp;
import lab13.prutcredit.payments.PaymentScheduler;
import lab13.prutcredit.payments.Repeat;
import lab13.prutcredit.payments.TransferPayment;
import lab13.prutcredit.payments.UtilityPayment;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        runLoanApproval();
        runDeferredPayments();
    }

    // cитуація 1 — chain of responsibility
    static void runLoanApproval() {
        header("СИТУАЦІЯ 1. Погодження кредиту (Chain of Responsibility)");
        SimClock.reset(LocalDateTime.of(2026, 11, 16, 9, 0));

        LimitApprover branchHead = new LimitApprover("Керівник відділення", 500_000);
        ApprovalChain chain = new ApprovalChain()
                .addLast(new LimitApprover("Кредитний менеджер", 50_000))
                .addLast(branchHead)
                .addLast(new LimitApprover("Кредитний комітет", 5_000_000))
                .addLast(new LimitApprover("Правління", LimitApprover.UNLIMITED));
        EventLog.log("Система погодження", "ланцюг: " + chain.describe());

        step("Заявки на 30 000, 300 000 і 3 000 000 грн");
        chain.submit(new LoanApplication("Іваненко І.", 30_000));
        chain.submit(new LoanApplication("ФОП Мельник", 300_000));
        chain.submit(new LoanApplication("ТОВ «Буковина-Агро»", 3_000_000));

        step("Заявка на 10 000 000 грн");
        chain.submit(new LoanApplication("ТОВ «Прут-Лайн»", 10_000_000));

        step("Додаємо службу безпеки для сум понад 1 000 000 грн (існуючі класи не змінюються)");
        chain.addFirst(new SecurityCheckHandler("Служба безпеки", 1_000_000,
                app -> !app.getApplicant().contains("Сумнівний")));
        EventLog.log("Система погодження", "ланцюг: " + chain.describe());
        chain.submit(new LoanApplication("ТОВ «Буковина-Агро»", 3_000_000));

        step("Додатково: служба безпеки відхиляє неблагонадійного заявника");
        chain.submit(new LoanApplication("ТОВ «Сумнівний трейд»", 2_000_000));

        step("Додатково: ліміт змінюється під час роботи (керівник відділення: 500 000 → 800 000 грн)");
        branchHead.setLimit(800_000);
        EventLog.log(branchHead.getName(), "ліміт змінено на " + Money.format(branchHead.getLimit()));
        chain.submit(new LoanApplication("ФОП Коваль", 700_000));
    }

    // cитуація 2 — command
    static void runDeferredPayments() {
        header("СИТУАЦІЯ 2. Відкладені платежі (Command)");
        SimClock.reset(LocalDateTime.of(2026, 11, 16, 9, 0));
        LocalDate today = SimClock.today();

        BankAccount client = new BankAccount("Поточний рахунок клієнта", 20_000);
        BankAccount petrenko = new BankAccount("Рахунок Петренка О.", 0);
        BankAccount waterUtility = new BankAccount("КП «Чернівціводоканал»", 0);
        BankAccount deposit = new BankAccount("Депозит «Накопичувальний»", 0);
        PaymentScheduler scheduler = new PaymentScheduler();

        step("Створюємо 3 платежі на різні дати");
        TransferPayment transfer = new TransferPayment(client, petrenko, 4_000,
                today.plusDays(1), Repeat.NONE, "позика");
        UtilityPayment water = new UtilityPayment(client, waterUtility, "Водопостачання", "100245",
                2_300, today.plusDays(2), Repeat.MONTHLY);
        DepositTopUp topUp = new DepositTopUp(client, deposit, 5_000, today.plusDays(3), Repeat.NONE);
        scheduler.schedule(transfer);
        scheduler.schedule(water);
        scheduler.schedule(topUp);
        scheduler.printQueue();

        step("Скасовуємо переказ до виконання");
        scheduler.cancel(transfer.getId());
        scheduler.printQueue();

        step("«Переходимо» до дати виконання решти платежів");
        scheduler.advanceTo(today.plusDays(3));

        step("Сторнуємо поповнення депозиту (той самий операційний день)");
        scheduler.reverse(topUp.getId());

        step("Спроба сторнувати комунальний платіж (операційний день уже закрито)");
        scheduler.reverse(water.getId());

        step("Підсумок");
        scheduler.printQueue();
        System.out.println("Журнал операцій:");
        scheduler.getJournal().print();
        System.out.println("Залишки рахунків:");
        for (BankAccount a : new BankAccount[]{client, petrenko, waterUtility, deposit}) {
            EventLog.log(a.getName(), "залишок " + Money.format(a.getBalance()));
        }
    }

    private static void header(String text) {
        System.out.println();
        System.out.println("==================================================================");
        System.out.println(text);
        System.out.println("==================================================================");
    }

    private static void step(String text) {
        System.out.println();
        System.out.println("--- " + text + " ---");
    }
}