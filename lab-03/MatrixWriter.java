// 1.2
import java.io.*;

public class MatrixWriter implements AutoCloseable {
    private final BufferedWriter writer;

    public MatrixWriter(String fileName) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(fileName)); // конструктор
    }
    public void write(int[][] matrix) throws IOException {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                writer.write(matrix[i][j] + " ");
            }
            writer.newLine();
        }
    }
    @Override 
    public void close() throws IOException {
        writer.close();
    }
}