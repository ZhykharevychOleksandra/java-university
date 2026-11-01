// завдання 2.2 завод
import  java.util.Scanner;

public class FactoryDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("Workers: ");
        int workers = Integer.parseInt(sc.nextLine());

        System.out.print("Daily output: ");
        int outputPerDay = Integer.parseInt(sc.nextLine());

        System.out.print("Price: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Salary per worker: ");
        double salaryWorkers = Double.parseDouble(sc.nextLine());

        System.out.print("Founded year: ");
        int foundedYear = Integer.parseInt(sc.nextLine());

        Factory factory = new Factory(name, city, workers, outputPerDay, price, salaryWorkers, foundedYear);

        // виклик трьох методів через геттери
        System.out.println("Daily revenue: " + factory.dailyRevenue(factory.getOutputPerDay(), factory.getPrice()));
        System.out.println("Monthly wage costs: " + factory.monthlySalaryCost(workers, salaryWorkers));
        System.out.println("Age: " + factory.age(2026, foundedYear));

        factory.setWorkers(-5);
        System.out.println("Workers after setWorkers(-5): " + factory.getWorkers());
    }
}
