package lab08.medicalclinic.infrastructure;

public interface SmsSender {
    void send(String phone, String message);
}