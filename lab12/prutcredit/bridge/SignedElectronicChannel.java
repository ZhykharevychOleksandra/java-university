package lab12.prutcredit.bridge;
import lab12.prutcredit.common.EventLog;

public class SignedElectronicChannel implements DeliveryChannel {

    @Override
    public String getName() {
        return "електронний документ з цифровим підписом";
    }

    @Override
    public void deliver(String title, String content) {
        String signature = String.format("%08X", (title + content).hashCode());
        EventLog.log("ЕЦП", "Документ «" + title + "» підписано КЕП (підпис " + signature
                + ") і передано державному органу | " + content);
    }
}