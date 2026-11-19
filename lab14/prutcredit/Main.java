package lab14.prutcredit;
import lab14.prutcredit.common.EventLog;
import lab14.prutcredit.loan.*;
import lab14.prutcredit.rates.*;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        section("Завдання 1 / сценарій А: усі висновки позитивні");
        loanApproved();
        section("Завдання 1 / сценарій Б: служба безпеки відмовляє");
        loanRejectedBySecurity();
        section("Завдання 1 / розширення: додано оцінювача застави (існуючі учасники не змінювались)");
        loanWithCollateralAppraiser();
        section("Завдання 2: зміна курсу валют");
        exchangeRateChange();
    }

    private static LoanManager buildBank(boolean withAppraiser) {
        LoanCoordinator c = new LoanCoordinator();
        LoanManager manager = new LoanManager(c);
        c.setManager(manager);
        c.setLegal(new LegalDepartment(c));
        c.setCashier(new Cashier(c));
        c.addCheck(new ScoringService(c));
        c.addCheck(new SecurityService(c));
        if (withAppraiser) c.addCheck(new CollateralAppraiser(c));
        return manager;
    }

    static void loanApproved() {
        EventLog.reset();
        buildBank(false).submit(new LoanApplication(1, "Іваненко О.", 200_000, 720, false, 0));
    }

    static void loanRejectedBySecurity() {
        EventLog.reset();
        buildBank(false).submit(new LoanApplication(2, "Петренко В.", 150_000, 700, true, 0));
    }

    static void loanWithCollateralAppraiser() {
        EventLog.reset();
        buildBank(true).submit(new LoanApplication(3, "Коваль Т.", 500_000, 750, false, 800_000));
    }

    static void exchangeRateChange() {
        EventLog.reset();
        ExchangeRateService service = new ExchangeRateService("USD/UAH", new BigDecimal("41.20"));
        RateObserver board = new BranchBoard();
        RateObserver online = new OnlineBanking();
        RateObserver client = new NotifiedClient("Мельник І.", new BigDecimal("41.00"));

        service.subscribe(board);
        service.subscribe(online);
        service.subscribe(client);

        service.setRate(new BigDecimal("41.50")); // клієнт не сповіщається
        service.setRate(new BigDecimal("40.90")); // клієнт отримує сповіщення

        service.unsubscribe(client);
        service.subscribe(new ConverterModule()); // підписка під час роботи програми
        service.setRate(new BigDecimal("40.80")); // решта отримувачів отримують курс
    }

    private static void section(String title) { System.out.println("\n=== " + title + " ==="); }
}