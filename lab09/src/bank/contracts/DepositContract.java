package bank.contracts;
import bank.common.Log;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class DepositContract implements Prototype<DepositContract> {
    private static final String SOURCE = "Договори";
    private static final AtomicInteger SEQ = new AtomicInteger(2000);

    private final String id;
    private final String productName;
    private final BigDecimal ratePercent;
    private final int termMonths;
    private final String earlyTerminationTerms;
    private final List<PaymentScheduleItem> schedule = new ArrayList<>();
    private final List<Addendum> addenda = new ArrayList<>();
    private BigDecimal amount;   // null у типовому договорі
    private Client client;       // null у типовому договорі

    private DepositContract(String id, String productName, BigDecimal ratePercent, int termMonths,
                            String earlyTerminationTerms) {
        this.id = id;
        this.productName = productName;
        this.ratePercent = ratePercent;
        this.termMonths = termMonths;
        this.earlyTerminationTerms = earlyTerminationTerms;
    }

    private DepositContract(DepositContract source) {
        this.id = "Д-" + SEQ.incrementAndGet();
        this.productName = source.productName;
        this.ratePercent = source.ratePercent;                 // BigDecimal незмінний
        this.termMonths = source.termMonths;
        this.earlyTerminationTerms = source.earlyTerminationTerms; // String незмінний
        for (PaymentScheduleItem item : source.schedule) {
            this.schedule.add(new PaymentScheduleItem(item));  // новий список і нові рядки
        }
        this.addenda.addAll(source.addenda);                   // новий список, угоди незмінні
        this.amount = source.amount;
        this.client = source.client;
    }

    public static DepositContract createTemplate(String productName, BigDecimal ratePercent, int termMonths) {
        Log.log(SOURCE, "формування типового договору «" + productName
                + "»: тип продукту, ставка, строк, графік виплат, умови розірвання (дорога операція)...");
        Log.pause(800);
        DepositContract template = new DepositContract("TPL-" + termMonths, productName, ratePercent, termMonths,
                "Дострокове розірвання: відсотки перераховуються за ставкою вкладу на вимогу");
        BigDecimal monthlyPercent = ratePercent.divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_UP);
        for (int month = 1; month <= termMonths; month++) {
            template.schedule.add(new PaymentScheduleItem(month, monthlyPercent));
        }
        Log.log(SOURCE, "типовий договір " + template.id + " готовий");
        return template;
    }

    @Override
    public DepositContract copy() {
        return new DepositContract(this);
    }

    public DepositContract issueFor(Client client, BigDecimal amount) {
        DepositContract contract = copy();
        contract.client = client;
        contract.amount = amount;
        Log.log(SOURCE, "оформлено " + contract.id + " для «" + client.getFullName() + "», "
                + Log.money(amount) + " грн (копія " + id + ", без повторного формування)");
        return contract;
    }

    public String getId() {
        return id;
    }

    public List<PaymentScheduleItem> getSchedule() {
        return schedule;
    }

    public void addAddendum(Addendum addendum) {
        addenda.add(addendum);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<String> describe(int maxItems) {
        List<String> lines = new ArrayList<>();
        lines.add("Номер: " + id);
        lines.add("Продукт: " + productName);
        lines.add("Ставка: " + Log.money(ratePercent) + "%, строк: " + termMonths + " міс.");
        lines.add("Клієнт: " + (client == null ? "— (типовий)" : client.getFullName()));
        lines.add("Сума: " + (amount == null ? "— (типовий)" : Log.money(amount) + " грн"));
        lines.add(earlyTerminationTerms);
        lines.add("Додаткові угоди (" + addenda.size() + "):");
        for (Addendum a : addenda) {
            lines.add("  " + a.getNumber() + ": " + a.getText());
        }
        lines.add("Графік виплат (" + schedule.size() + "):");
        int shown = 0;
        for (PaymentScheduleItem item : schedule) {
            if (shown++ == maxItems) {
                lines.add("  ...");
                break;
            }
            String payout = amount == null ? ""
                    : " = " + Log.money(amount.multiply(item.getPayoutPercent())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)) + " грн";
            lines.add("  міс. " + item.getMonth() + ": " + item.getPayoutPercent() + "%" + payout);
        }
        return lines;
    }

    public String snapshot() {
        return String.join("\n", describe(Integer.MAX_VALUE));
    }
}
