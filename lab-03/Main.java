// завдання 1.1 матриці
import java.util.Random;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[][] grades = new int[20][10]; //рядки, стовпці

        for(int i = 0; i < grades.length; i++) {
            for(int j = 0; j < grades[i].length; j++) {
                grades[i][j] = 2 + random.nextInt(4);
            }
        }

        try (MatrixWriter mw = new MatrixWriter("grades.txt")) {
            mw.write(grades);
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
        try (MatrixReader mr = new MatrixReader("grades.txt")) {
            int[][] loaded = mr.read();

            for (int i = 0; i < loaded.length; i++) {
                for (int j = 0; j < loaded[i].length; j++) {
                    System.out.print(loaded[i][j] + " ");
                }
                System.out.println();
            }

            int total = 0;
            for (int j = 0; j < loaded[0].length; j++) {
                int sum = 0;
                for (int i = 0; i < loaded.length; i++) { 
                    sum += loaded[i][j];
                    total += loaded[i][j];
                }
                double average = (double) sum / loaded.length;
                System.out.printf("Discipline %d: %.2f%n", j + 1, average);
            }
            double groupAverage = (double) total / (loaded.length * loaded[0].length);
            System.out.printf("Group average: %.2f%n", groupAverage);
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }
}
