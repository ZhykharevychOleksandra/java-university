package lab11.bank;
import lab11.bank.adapter.Payment;
import lab11.bank.adapter.SwiftLibrary;
import lab11.bank.adapter.SwiftPaymentAdapter;
import lab11.bank.adapter.Treasury;
import lab11.bank.common.Log11;
import bank.common.Log;
import lab11.bank.decorator.BasicCard;
import lab11.bank.decorator.CashbackOption;
import lab11.bank.decorator.Card;
import lab11.bank.decorator.HigherLimitOption;
import lab11.bank.decorator.LoungeAccessOption;
import lab11.bank.decorator.SmsInfoOption;
import lab11.bank.decorator.TravelInsuranceOption;
import lab11.bank.facade.Account;
import lab11.bank.facade.AccountOpeningFacade;
import lab11.bank.facade.AccountService;
import lab11.bank.facade.BranchChannel;
import lab11.bank.facade.CardService;
import lab11.bank.facade.Client;
import lab11.bank.facade.ContractService;
import lab11.bank.facade.IdentificationService;
import lab11.bank.facade.OnlineBankingService;
import lab11.bank.facade.OnlineChannel;
import lab11.bank.facade.SanctionsService;
import lab11.bank.proxy.CreditBureauProxy;
import lab11.bank.proxy.Employee;
import lab11.bank.proxy.LoanModule;
import lab11.bank.proxy.RealCreditBureau;
import lab11.bank.proxy.Role;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        situation1CreditBureauProxy();
        situation2CardDecorator();
        situation3SwiftAdapter();
        situation4AccountOpeningFacade();
    }

    // завдання 1: рroxy (кеш + контроль доступу)
    static void situation1CreditBureauProxy() {
        Log11.section("Ситуація 1. Бюро кредитних історій (Proxy)");
        RealCreditBureau realBureau = new RealCreditBureau();
        CreditBureauProxy proxy = new CreditBureauProxy(realBureau);
        LoanModule loanModule = new LoanModule(proxy);

        Employee manager = new Employee("Ковальчук", Role.CREDIT_MANAGER);
        Employee cashier = new Employee("Мельник", Role.CASHIER);

        loanModule.checkClient(manager, "CLIENT-1001");
        loanModule.checkClient(manager, "CLIENT-1001");
        loanModule.checkClient(manager, "CLIENT-1001");
        Log.log("Main", "кількість платних запитів до бюро: " + realBureau.getPaidRequests());
        loanModule.checkClient(cashier, "CLIENT-1001");
    }

    // завдання 2: decorator (опції картки)
    static void situation2CardDecorator() {
        Log11.section("Ситуація 2. Опції банківської картки (Decorator)");
        Card basic = new BasicCard("Іваненко І.");
        Log.log("Main", "картка: " + basic.getDescription());
        Log.log("Main", "вартість обслуговування: " + basic.getMonthlyCost() + " грн");

        Card card1 = new SmsInfoOption(new TravelInsuranceOption(new CashbackOption(new BasicCard("Іваненко І."))));
        Log.log("Main", "картка з опціями: " + card1.getDescription());
        Log.log("Main", "підсумкова вартість: " + card1.getMonthlyCost() + " грн");

        Card card2 = new LoungeAccessOption(new HigherLimitOption(new BasicCard("Петренко П.")));
        Log.log("Main", "інша картка: " + card2.getDescription());
        Log.log("Main", "підсумкова вартість: " + card2.getMonthlyCost() + " грн");
    }

    // завдання 3: аdapter (swift)
    static void situation3SwiftAdapter() {
        Log11.section("Ситуація 3. SWIFT-платежі (Adapter)");
        Treasury treasury = new Treasury(new SwiftPaymentAdapter(new SwiftLibrary(), LocalDate.of(2026, 11, 16)));

        treasury.sendInternationalPayment(
                new Payment("Прут-Кредит, ТОВ Карпати", "Alpen Handel GmbH", new BigDecimal("1000"), "EUR"));
        treasury.sendInternationalPayment(
                new Payment("Прут-Кредит, ТОВ Карпати", "CLOSED Account Ltd", new BigDecimal("500"), "EUR"));
    }

    // завдання 4: facade (відкриття рахунку)
    static void situation4AccountOpeningFacade() {
        Log11.section("Ситуація 4. Відкриття рахунку (Facade)");
        CardService cardService = new CardService();
        AccountOpeningFacade facade = new AccountOpeningFacade(
                new IdentificationService(), new SanctionsService(), new AccountService(),
                cardService, new OnlineBankingService(), new ContractService());

        BranchChannel branch = new BranchChannel(facade);
        OnlineChannel online = new OnlineChannel(facade);

        Account a1 = branch.openAccount(new Client("C-1", "Шевченко Олена"));
        online.openAccount(new Client("C-2", "Бойко Андрій"));

        Log.log("Main", "окремий перевипуск картки без відкриття нового рахунку");
        cardService.reissueCard(a1);
    }
}