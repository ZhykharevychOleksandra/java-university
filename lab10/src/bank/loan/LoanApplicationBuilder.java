package lab10.src.bank.loan;
import lab10.src.bank.common.Client;
import java.math.BigDecimal;

public interface LoanApplicationBuilder {
    // обов'язкові частини
    LoanApplicationBuilder forClient(Client client);

    LoanApplicationBuilder amount(BigDecimal amount);

    LoanApplicationBuilder termMonths(int months);

    // мета (за замовчуванням «не вказано»)
    LoanApplicationBuilder purpose(String purpose);

    // необов'язкові частини: можна пропускати
    LoanApplicationBuilder collateral(String description, BigDecimal estimatedValue);

    LoanApplicationBuilder guarantor(String fullName, BigDecimal monthlyIncome);

    LoanApplicationBuilder incomeCertificate(String issuer, BigDecimal monthlyIncome);

    LoanApplicationBuilder insurance(String policyType, String insurer);

    // перевірка та видача незмінної заявки
    LoanApplication build();

    // cкидає накопичений стан, щоб почати нову заявку
    void reset();
}
