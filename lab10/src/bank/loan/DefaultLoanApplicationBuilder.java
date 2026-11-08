package lab10.src.bank.loan;
import lab10.src.bank.common.Client;
import lab10.src.bank.common.Log;
import lab10.src.bank.common.Money;
import lab10.src.bank.loan.LoanApplication.Collateral;
import lab10.src.bank.loan.LoanApplication.Guarantor;
import lab10.src.bank.loan.LoanApplication.IncomeCertificate;
import lab10.src.bank.loan.LoanApplication.Insurance;
import java.math.BigDecimal;

public class DefaultLoanApplicationBuilder implements LoanApplicationBuilder {
    private static final String SOURCE = "LoanApplicationBuilder";
    private Client client;
    private BigDecimal amount;
    private Integer termMonths;
    private String purpose = "не вказано";
    private Collateral collateral;
    private Guarantor guarantor;
    private IncomeCertificate incomeCertificate;
    private Insurance insurance;

    @Override
    public LoanApplicationBuilder forClient(Client client) {
        this.client = client;
        Log.log(SOURCE, "Крок: клієнт — " + client);
        return this;
    }

    @Override
    public LoanApplicationBuilder amount(BigDecimal amount) {
        this.amount = amount;
        Log.log(SOURCE, "Крок: сума — " + Money.uah(amount));
        return this;
    }

    @Override
    public LoanApplicationBuilder termMonths(int months) {
        this.termMonths = months;
        Log.log(SOURCE, "Крок: строк — " + months + " міс.");
        return this;
    }

    @Override
    public LoanApplicationBuilder purpose(String purpose) {
        this.purpose = purpose;
        Log.log(SOURCE, "Крок: мета — " + purpose);
        return this;
    }

    @Override
    public LoanApplicationBuilder collateral(String description, BigDecimal estimatedValue) {
        this.collateral = new Collateral(description, estimatedValue);
        Log.log(SOURCE, "Крок: застава — " + description);
        return this;
    }

    @Override
    public LoanApplicationBuilder guarantor(String fullName, BigDecimal monthlyIncome) {
        this.guarantor = new Guarantor(fullName, monthlyIncome);
        Log.log(SOURCE, "Крок: поручитель — " + fullName);
        return this;
    }

    @Override
    public LoanApplicationBuilder incomeCertificate(String issuer, BigDecimal monthlyIncome) {
        this.incomeCertificate = new IncomeCertificate(issuer, monthlyIncome);
        Log.log(SOURCE, "Крок: довідка про доходи — " + Money.uah(monthlyIncome) + "/міс.");
        return this;
    }

    @Override
    public LoanApplicationBuilder insurance(String policyType, String insurer) {
        this.insurance = new Insurance(policyType, insurer);
        Log.log(SOURCE, "Крок: страхування — " + policyType);
        return this;
    }

    @Override
    public LoanApplication build() {
        String error = firstError();
        if (error != null) {
            Log.log(SOURCE, "Неможливо сформувати заявку: " + error);
            throw new InvalidLoanApplicationException(error);
        }
        LoanApplication application = new LoanApplication(client, amount, termMonths, purpose,
                collateral, guarantor, incomeCertificate, insurance);
        Log.log(SOURCE, "Заявку сформовано та перевірено, передано на скоринг");
        reset();
        return application;
    }

    @Override
    public void reset() {
        client = null;
        amount = null;
        termMonths = null;
        purpose = "не вказано";
        collateral = null;
        guarantor = null;
        incomeCertificate = null;
        insurance = null;
    }

    private String firstError() {
        if (client == null) {
            return "не вказано клієнта";
        }
        if (amount == null) {
            return "не вказано суму кредиту";
        }
        if (amount.signum() <= 0) {
            return "сума кредиту має бути більшою за нуль";
        }
        if (termMonths == null) {
            return "не вказано строк кредиту";
        }
        if (termMonths <= 0) {
            return "строк кредиту має бути більшим за нуль";
        }
        return null;
    }
}