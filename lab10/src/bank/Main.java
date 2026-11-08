package lab10.src.bank;
import lab10.src.bank.common.Client;
import lab10.src.bank.common.Log;
import lab10.src.bank.common.Money;
import lab10.src.bank.loan.DefaultLoanApplicationBuilder;
import lab10.src.bank.loan.InvalidLoanApplicationException;
import lab10.src.bank.loan.LoanApplication;
import lab10.src.bank.loan.LoanApplicationBuilder;
import lab10.src.bank.loan.LoanApplicationDirector;
import lab10.src.bank.products.Branch;
import lab10.src.bank.products.BusinessProductFactory;
import lab10.src.bank.products.PremiumProductFactory;
import lab10.src.bank.products.RetailProductFactory;
import lab10.src.bank.products.ServicePackage;
import lab10.src.bank.products.students.StudentProductFactory;
import lab10.src.bank.statements.AccountingStatementGenerator;
import lab10.src.bank.statements.ClientStatementGenerator;
import lab10.src.bank.statements.StatementDocument;
import lab10.src.bank.statements.StatementGenerator;
import lab10.src.bank.statements.TaxStatementGenerator;
import lab10.src.bank.statements.Transaction;
import lab10.src.bank.statements.audit.AuditorStatementGenerator;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

// окремий метод для кожної проблемної ситуації варіанта 7 «Банк «Прут-Кредит»»
public class Main {
    private static final String RUNNER = "Runner";

    public static void main(String[] args) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

        situation1CreditApplication();
        situation2ProductPackages();
        situation3Statements();
    }

    // завдання 1: Builder
    static void situation1CreditApplication() {
        Log.header("СИТУАЦІЯ 1. Кредитна заявка — шаблон Builder");

        Client client = new Client("Мельник Андрій Петрович", List.of("UA21 3052 0000 2600 0000 0000 123"));
        LoanApplicationBuilder builder = new DefaultLoanApplicationBuilder();

        Log.log(RUNNER, "Сценарій 1.1: заявка на 300 000 грн на 36 місяців із заставою і поручителем");
        LoanApplication application = builder
                .forClient(client)
                .amount(Money.of(300_000))
                .termMonths(36)
                .purpose("Ремонт житла")
                .collateral("Квартира, м. Чернівці, вул. Головна, 10", Money.of(1_450_000))
                .guarantor("Мельник Ірина Василівна", Money.of(28_000))
                .build();
        Log.print(application);

        Log.log(RUNNER, "Сценарій 1.2: типова заявка «Споживчий кредит»");
        LoanApplicationDirector director = new LoanApplicationDirector(builder);
        LoanApplication consumer = director.consumerLoan(client, Money.of(80_000), 24, Money.of(32_000));
        Log.print(consumer);

        Log.log(RUNNER, "Сценарій 1.2а: типова заявка «Автокредит» (та сама послідовність кроків в одному місці)");
        LoanApplication auto = director.autoLoan(client, Money.of(450_000), 60,
                "Skoda Octavia, 2024 р.", Money.of(620_000));
        Log.print(auto);

        Log.log(RUNNER, "Сценарій 1.3: спроба завершити формування без суми");
        builder.reset();
        try {
            builder.forClient(client).termMonths(12).purpose("Навчання").build();
        } catch (InvalidLoanApplicationException e) {
            Log.log(RUNNER, "Заявку не передано на скоринг (отримано помилку, об'єкт не створено)");
        }

        Log.log(RUNNER, "Сценарій 1.4: спроба завершити формування з нульовим строком");
        builder.reset();
        try {
            builder.forClient(client).amount(Money.of(50_000)).termMonths(0).build();
        } catch (InvalidLoanApplicationException e) {
            Log.log(RUNNER, "Заявку не передано на скоринг (отримано помилку, об'єкт не створено)");
        }
        builder.reset();
    }

    // завдання 2: Abstract Factory
    static void situation2ProductPackages() {
        Log.header("СИТУАЦІЯ 2. Пакети продуктів для сегментів — шаблон Abstract Factory");

        Branch branch = new Branch("Відділення №3");

        Log.log(RUNNER, "Сценарій 2.1: відкриття обслуговування трьом сегментам одним кодом відділення");
        ServicePackage retail = branch.openService(new Client("Іваненко Петро Олегович"),
                new RetailProductFactory());
        Log.print(retail);
        ServicePackage premium = branch.openService(new Client("Коваль Олена Михайлівна"),
                new PremiumProductFactory());
        Log.print(premium);
        ServicePackage business = branch.openService(new Client("ТОВ «Буковинський хліб»"),
                new BusinessProductFactory());
        Log.print(business);

        Log.log(RUNNER, "Сценарій 2.2: додано студентський сегмент новими класами; код Branch не змінювався");
        ServicePackage student = branch.openService(new Client("Гнатюк Марія Іванівна"),
                new StudentProductFactory());
        Log.print(student);
    }

    // завдання 3: Factory Method
    static void situation3Statements() {
        Log.header("СИТУАЦІЯ 3. Формування виписок — шаблон Factory Method");

        Client client = new Client("ТОВ «Буковинський хліб»");
        YearMonth period = YearMonth.of(2026, 10);
        List<Transaction> ledger = List.of(
                new Transaction(LocalDate.of(2026, 9, 28), "Оплата сировини", Money.of(-12_000)),
                new Transaction(LocalDate.of(2026, 10, 2), "Надходження від покупця", Money.of(85_000)),
                new Transaction(LocalDate.of(2026, 10, 9), "Оренда приміщення", Money.of(-18_500)),
                new Transaction(LocalDate.of(2026, 10, 15), "Надходження від покупця", Money.of(42_300)),
                new Transaction(LocalDate.of(2026, 10, 25), "Заробітна плата", Money.of(-60_000)),
                new Transaction(LocalDate.of(2026, 11, 3), "Надходження від покупця", Money.of(30_000)));

        Log.log(RUNNER, "Сценарій 3.1: виписка за " + period + " для клієнта, податкової та бухгалтерії");
        List<StatementGenerator> generators = List.of(
                new ClientStatementGenerator(ledger),
                new TaxStatementGenerator(ledger),
                new AccountingStatementGenerator(ledger));
        for (StatementGenerator generator : generators) {
            StatementDocument document = generator.generate(client, period);
            Log.print(document.render());
        }

        Log.log(RUNNER, "Сценарій 3.2: додано адресата «аудитор» новими класами; StatementGenerator не змінювався");
        StatementDocument audit = new AuditorStatementGenerator(ledger).generate(client, period);
        Log.print(audit.render());
    }
}
