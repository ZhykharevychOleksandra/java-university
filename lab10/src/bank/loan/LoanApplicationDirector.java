package lab10.src.bank.loan;
import lab10.src.bank.common.Client;
import lab10.src.bank.common.Log;
import java.math.BigDecimal;

public class LoanApplicationDirector {
    private static final String SOURCE = "LoanApplicationDirector";
    private final LoanApplicationBuilder builder;

    public LoanApplicationDirector(LoanApplicationBuilder builder) {
        this.builder = builder;
    }

    public LoanApplication autoLoan(Client client, BigDecimal amount, int termMonths,
                                    String carDescription, BigDecimal carValue) {
        Log.log(SOURCE, "Типова заявка «Автокредит»");
        builder.reset();
        return builder.forClient(client)
                .amount(amount)
                .termMonths(termMonths)
                .purpose("Придбання автомобіля")
                .collateral(carDescription, carValue)
                .insurance("КАСКО", "СК «Буковина»")
                .build();
    }

    public LoanApplication consumerLoan(Client client, BigDecimal amount, int termMonths,
                                        BigDecimal monthlyIncome) {
        Log.log(SOURCE, "Типова заявка «Споживчий кредит»");
        builder.reset();
        return builder.forClient(client)
                .amount(amount)
                .termMonths(termMonths)
                .purpose("Споживчі потреби")
                .incomeCertificate("Роботодавець клієнта", monthlyIncome)
                .build();
    }
}
