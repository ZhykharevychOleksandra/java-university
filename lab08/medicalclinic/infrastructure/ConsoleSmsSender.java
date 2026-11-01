package lab08.medicalclinic.infrastructure;

public class ConsoleSmsSender implements SmsSender {
    @Override
    public void send(String phone, String message) {
        System.out.println("[SMS] " + phone);
        System.out.println(message);
    }
}