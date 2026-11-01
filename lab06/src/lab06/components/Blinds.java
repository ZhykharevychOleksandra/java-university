package lab06.components;

public class Blinds {
    private String status;

    public Blinds() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> жалюзі опущено";
    }

    @Override
    public String toString() {
        return "Blinds{status='" + status + "'}";
    }
}