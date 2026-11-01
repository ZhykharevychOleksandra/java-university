package lab07.legacy.task5_dip;

public interface AlertPublisher {
    void sendEmergencyAlert(String topic, String payload);
}