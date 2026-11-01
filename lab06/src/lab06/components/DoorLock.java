package lab06.components;
import lab06.annotations.Component;

@Component(name = "door-lock", version = "1.0")
public class DoorLock {
    private String status;

    public DoorLock() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> двері зачинено";
    }

    @Override
    public String toString() {
        return "DoorLock{status='" + status + "'}";
    }
}