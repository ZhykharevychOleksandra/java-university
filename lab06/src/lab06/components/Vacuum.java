package lab06.components;

public class Vacuum {
    private String status;

    public Vacuum() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> прибирання розпочато";
    }

    @Override
    public String toString() {
        return "Vacuum{status='" + status + "'}";
    }
}