package lab06.components;

public class Sensor {
    private String status;

    public Sensor() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> знімає показники";
    }

    @Override
    public String toString() {
        return "Sensor{status='" + status + "'}";
    }
}