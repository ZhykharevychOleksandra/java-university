package lab12.prutcredit.bridge;
import lab12.prutcredit.common.EventLog;

public class DiiaChannel implements DeliveryChannel {
    @Override
    public String getName() {
        return "надсилання в Дію";
    }

    @Override
    public void deliver(String title, String content) {
        EventLog.log("Дія", "Документ «" + title + "» надіслано в застосунок Дія | " + content);
    }
}