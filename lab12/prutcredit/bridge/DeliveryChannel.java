package lab12.prutcredit.bridge;

public interface DeliveryChannel {
    String getName();
    void deliver(String title, String content);
}