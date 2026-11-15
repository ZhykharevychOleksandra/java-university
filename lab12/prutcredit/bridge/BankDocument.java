package lab12.prutcredit.bridge;
import lab12.prutcredit.common.EventLog;

public abstract class BankDocument {

    private DeliveryChannel channel;

    protected BankDocument(DeliveryChannel channel) {
        this.channel = channel;
    }

    public void setChannel(DeliveryChannel channel) {
        this.channel = channel;
    }

    public DeliveryChannel getChannel() {
        return channel;
    }

    public abstract String getTitle();

    protected abstract String buildContent();

    public void issue() {
        EventLog.log("Документ", "Формується «" + getTitle() + "», спосіб надання: " + channel.getName());
        channel.deliver(getTitle(), buildContent());
    }
}