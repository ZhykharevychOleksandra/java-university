package lab12.prutcredit.bridge;
import lab12.prutcredit.common.EventLog;

public class PrintChannel implements DeliveryChannel {
    private final String branch;

    public PrintChannel(String branch) {
        this.branch = branch;
    }

    @Override
    public String getName() {
        return "друк (" + branch + ")";
    }

    @Override
    public void deliver(String title, String content) {
        EventLog.log("Друк", branch + ": надруковано «" + title + "» | " + content);
    }
}
