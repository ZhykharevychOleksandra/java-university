package lab11.bank.proxy;
import bank.common.Log;
import java.util.HashMap;
import java.util.Map;

public class CreditBureauProxy implements CreditBureau {
    private static final String SOURCE = "CreditBureauProxy";

    private final CreditBureau real;
    private final Map<String, CreditReport> cache = new HashMap<>();

    public CreditBureauProxy(CreditBureau real) {
        this.real = real;
    }

    @Override
    public CreditReport getHistory(Employee requester, String clientId) {
        if (requester.role() != Role.CREDIT_MANAGER) {
            Log.log(SOURCE, "ВІДМОВА: " + requester + " не може отримати кредитну історію. Недостатньо прав");
            throw new AccessDeniedException("Недостатньо прав");
        }
        CreditReport cached = cache.get(clientId);
        if (cached != null) {
            Log.log(SOURCE, "результат по клієнту " + clientId + " з КЕШУ, звернення до бюро немає");
            return cached;
        }
        Log.log(SOURCE, "у кеші немає клієнта " + clientId + ", передаю запит реальному бюро");
        CreditReport report = real.getHistory(requester, clientId);
        cache.put(clientId, report);
        return report;
    }

    public void startNewWorkDay() {
        cache.clear();
        Log.log(SOURCE, "новий робочий день, кеш очищено");
    }
}