// 1.3
import java.io.*;
import java.util.ArrayList;

public class MatrixReader implements AutoCloseable {
    private final BufferedReader reader;

    public MatrixReader(String fileName) throws IOException {
        this.reader = new BufferedReader(new FileReader(fileName));
    }
    public int[][] read() throws IOException {
        ArrayList<int[]> rows = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(" ");
            int[] row = new int[parts.length];
            for (int j = 0; j < parts.length; j++) {
                row[j] = Integer.parseInt(parts[j]);
            }
            rows.add(row);
        }
        return rows.toArray(new int[0][]);
    }
    @Override 
    public void close() throws IOException {
        reader.close();
    }
}