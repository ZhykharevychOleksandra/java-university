package lab10.src.bank.loan;
import lab10.src.bank.common.Client;
import lab10.src.bank.common.Money;
import java.math.BigDecimal;
import java.util.Optional;

public final class LoanApplication {
    // застава (необов'язкова) 
    public record Collateral(String description, BigDecimal estimatedValue) {
    }

    // поручитель (необов'язковий)
    public record Guarantor(String fullName, BigDecimal monthlyIncome) {
    }

    // довідка про доходи (необов'язкова)
    public record IncomeCertificate(String issuer, BigDecimal monthlyIncome) {
    }

    // страхування (необов'язкове)
    public record Insurance(String policyType, String insurer) {
    }

    private final Client client;
    private final BigDecimal amount;
    private final int termMonths;
    private final String purpose;
    private final Collateral collateral;
    private final Guarantor guarantor;
    private final IncomeCertificate incomeCertificate;
    private final Insurance insurance;

    LoanApplication(Client client, BigDecimal amount, int termMonths, String purpose,
                    Collateral collateral, Guarantor guarantor,
                    IncomeCertificate incomeCertificate, Insurance insurance) {
        this.client = client;
        this.amount = amount;
        this.termMonths = termMonths;
        this.purpose = purpose;
        this.collateral = collateral;
        this.guarantor = guarantor;
        this.incomeCertificate = incomeCertificate;
        this.insurance = insurance;
    }

    public Client client() {
        return client;
    }

    public BigDecimal amount() {
        return amount;
    }

    public int termMonths() {
        return termMonths;
    }

    public String purpose() {
        return purpose;
    }

    public Optional<Collateral> collateral() {
        return Optional.ofNullable(collateral);
    }

    public Optional<Guarantor> guarantor() {
        return Optional.ofNullable(guarantor);
    }

    public Optional<IncomeCertificate> incomeCertificate() {
        return Optional.ofNullable(incomeCertificate);
    }

    public Optional<Insurance> insurance() {
        return Optional.ofNullable(insurance);
    }

    @Override
    public String toString() {
        return "Кредитна заявка\n"
                + "  Клієнт:             " + client + "\n"
                + "  Сума:               " + Money.uah(amount) + "\n"
                + "  Строк:              " + termMonths + " міс.\n"
                + "  Мета:               " + purpose + "\n"
                + "  Застава:            " + collateral().map(c -> c.description()
                        + " (оцінка " + Money.uah(c.estimatedValue()) + ")").orElse("—") + "\n"
                + "  Поручитель:         " + guarantor().map(g -> g.fullName()
                        + " (дохід " + Money.uah(g.monthlyIncome()) + "/міс.)").orElse("—") + "\n"
                + "  Довідка про доходи: " + incomeCertificate().map(i -> i.issuer()
                        + ", " + Money.uah(i.monthlyIncome()) + "/міс.").orElse("—") + "\n"
                + "  Страхування:        " + insurance().map(i -> i.policyType()
                        + " (" + i.insurer() + ")").orElse("—");
    }
}
