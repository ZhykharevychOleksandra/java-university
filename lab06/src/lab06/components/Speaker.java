package lab06.components;
import lab06.annotations.Component;

@Component(name = "speaker", version = "1.4")
public class Speaker {
    private String status;

    public Speaker() {
        this.status = "idle";
    }

    public void execute() {
        status = status + " -> відтворює музику";
    }

    @Override
    public String toString() {
        return "Speaker{status='" + status + "'}";
    }
}