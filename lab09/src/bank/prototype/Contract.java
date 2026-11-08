package lab09.src.bank.prototype;
import lab09.src.bank.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contract {

    private String productType;
    private double interestRate;
    private int termMonths;
    private double amount;
    private String customer;
    private String earlyTerminationConditions;

    private PaymentSchedule schedule;

    private List<AdditionalAgreement> agreements;

    public Contract(
            String productType,
            double interestRate,
            int termMonths,
            double amount,
            String customer,
            String earlyTerminationConditions,
            PaymentSchedule schedule,
            List<AdditionalAgreement> agreements
    ) {
        this.productType = productType;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.amount = amount;
        this.customer = customer;
        this.earlyTerminationConditions =
                earlyTerminationConditions;
        this.schedule = schedule;
        this.agreements = agreements;
    }

    public static Contract createTemplate() {

        Logger.log("Contract",
                "Початок дорогого формування типового договору");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }

        PaymentSchedule schedule = new PaymentSchedule(
                Arrays.asList(
                        "Місяць 3: нарахування відсотків",
                        "Місяць 6: нарахування відсотків",
                        "Місяць 9: нарахування відсотків",
                        "Місяць 12: повернення депозиту"
                )
        );

        Contract template = new Contract(
                "Депозит Стабільний",
                12.5,
                12,
                0,
                "Типовий клієнт",
                "Дострокове розірвання згідно з умовами банку",
                schedule,
                new ArrayList<>()
        );

        Logger.log("Contract",
                "Типовий договір сформовано");

        return template;
    }

    public Contract cloneForCustomer(
            String customer,
            double amount
    ) {

        List<AdditionalAgreement> copiedAgreements =
                new ArrayList<>();

        for (AdditionalAgreement agreement : agreements) {
            copiedAgreements.add(agreement.copy());
        }

        return new Contract(
                productType,
                interestRate,
                termMonths,
                amount,
                customer,
                earlyTerminationConditions,
                schedule.copy(),
                copiedAgreements
        );
    }

    public void addAgreement(AdditionalAgreement agreement) {
        agreements.add(agreement);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {

        return "\nТип продукту: " + productType +
                "\nКлієнт: " + customer +
                "\nСума: " + amount + " грн" +
                "\nСтавка: " + interestRate + "%" +
                "\nСтрок: " + termMonths + " місяців" +
                "\nУмови дострокового розірвання: " +
                earlyTerminationConditions +
                "\nГрафік платежів: " + schedule +
                "\nДодаткові угоди: " + agreements +
                "\n";
    }
}