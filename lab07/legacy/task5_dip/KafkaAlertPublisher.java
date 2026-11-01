package lab07.legacy.task5_dip;

public class KafkaAlertPublisher implements AlertPublisher {
    @Override
    public void sendEmergencyAlert(String topic, String payload) {
        System.out.println(
                "[Apache Kafka Broker] Опубліковано тривогу у топік '"
                        + topic + "': " + payload
        );
    }
}