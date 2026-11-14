package lab11.bank.proxy;
import bank.common.Log;

public class LoanModule {
    private static final String SOURCE = "LoanModule";
    private final CreditBureau bureau;

    public LoanModule(CreditBureau bureau) {
        this.bureau = bureau;
    }

    public void checkClient(Employee employee, String clientId) {
        Log.log(SOURCE, employee + " запитує кредитну історію клієнта " + clientId);
        try {
            CreditReport r = bureau.getHistory(employee, clientId);
            Log.log(SOURCE, "отримано історію: рейтинг " + r.score() + ", " + r.summary());
        } catch (AccessDeniedException e) {
            Log.log(SOURCE, "запит відхилено: " + e.getMessage());
        }
    }
}