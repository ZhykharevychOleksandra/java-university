package lab06.components;
import lab06.annotations.Component;

@Component(name = "camera", version = "3.1")
public class Camera {
    private String status;

    public Camera() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> запис відео розпочато";
    }

    @Override
    public String toString() {
        return "Camera{status='" + status + "'}";
    }
}