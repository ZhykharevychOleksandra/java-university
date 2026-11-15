package lab12.prutcredit.flyweight;
import lab12.prutcredit.common.EventLog;
import java.util.HashMap;
import java.util.Map;

public class MerchantFactory {
    private final Map<String, Merchant> pool = new HashMap<>();
    private int requests = 0;

    public Merchant getMerchant(String id, String name, String logo, String category, int mcc, String address) {
        requests++;
        Merchant merchant = pool.get(id);
        if (merchant == null) {
            merchant = new Merchant(id, name, logo, category, mcc, address);
            pool.put(id, merchant);
            EventLog.log("Фабрика торговців", "Створено нового торговця: " + merchant);
        }
        return merchant;
    }

    public int getPoolSize() {
        return pool.size();
    }

    public int getRequests() {
        return requests;
    }
}
