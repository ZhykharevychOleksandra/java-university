package lab06.components;
import lab06.annotations.Component;

@Component(name = "heater", version = "2.2")
public class Heater {
    private String status;

    public Heater() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> нагрівання запущено";
    }

    @Override
    public String toString() {
        return "Heater{status='" + status + "'}";
    }
}