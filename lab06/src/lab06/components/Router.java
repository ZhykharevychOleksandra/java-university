package lab06.components;

public class Router {
    private String status;

    public Router() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> роздає Wi-Fi";
    }

    @Override
    public String toString() {
        return "Router{status='" + status + "'}";
    }
}