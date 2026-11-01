package lab07.legacy.task2_ocp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReportData {

    private final String title;
    private final LocalDateTime generatedAt;
    private final List<String> headers;
    private final List<List<String>> rows;

    public ReportData(String title, LocalDateTime generatedAt) {

        this.title = Objects.requireNonNull(
                title,
                "Report title cannot be null"
        );

        this.generatedAt = Objects.requireNonNull(
                generatedAt,
                "Generation date cannot be null"
        );

        this.headers = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public List<String> getHeaders() {
        return Collections.unmodifiableList(headers);
    }

    public List<List<String>> getRows() {

        List<List<String>> result = new ArrayList<>();

        for (List<String> row : rows) {
            result.add(Collections.unmodifiableList(row));
        }

        return Collections.unmodifiableList(result);
    }

    public void addHeader(String header) {

        Objects.requireNonNull(
                header,
                "Header cannot be null"
        );

        headers.add(header);
    }

    public void addRow(List<String> row) {

        Objects.requireNonNull(
                row,
                "Row cannot be null"
        );

        rows.add(new ArrayList<>(row));
    }
}