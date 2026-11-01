package lab06.components;
import lab06.annotations.Component;

@Component(name = "thermostat", version = "1.2")
public class Thermostat {
    private String status;

    public Thermostat() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> підтримує температуру 22°C";
    }

    @Override
    public String toString() {
        return "Thermostat{status='" + status + "'}";
    }
}