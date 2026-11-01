package lab06.components;

import lab06.annotations.Component;

@Component(name = "lamp", version = "2.0")
public class Lamp {
    private String status;

    public Lamp() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> світло увімкнено";
    }

    @Override
    public String toString() {
        return "Lamp{status='" + status + "'}";
    }
}