// 3.4
import java.util.Scanner;

public class VehicleDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vehicle type: ");
        String vType = scanner.nextLine();
        System.out.print("Vehicle speed: ");
        double vSpeed = Double.parseDouble(scanner.nextLine());
        System.out.print("Vehicle year: ");
        int vYear = Integer.parseInt(scanner.nextLine());


        System.out.print("Plane type: ");
        String pType = scanner.nextLine();
        System.out.print("Plane speed: ");
        double pSpeed = Double.parseDouble(scanner.nextLine());
        System.out.print("Plane year: ");
        int pYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Plane height: ");
        double pHeight = Double.parseDouble(scanner.nextLine()); 


        System.out.print("Ship type: ");
        String sType = scanner.nextLine();
        System.out.print("Ship speed: ");
        double sSpeed = Double.parseDouble(scanner.nextLine());
        System.out.print("Ship year: ");
        int sYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Ship port: ");
        String sPort = scanner.nextLine();

        try (Vehicle v = new Vehicle(vType, vSpeed, vYear); Plane p = new Plane(pType, pSpeed, pYear, pHeight); Ship s = new Ship(sType, sSpeed, sYear, sPort)) {
            System.out.println("Before update: ");
            System.out.println(v.info());
            System.out.println(p.info());
            System.out.println(s.info());
            v.update();
            p.update();
            s.update();
            
            System.out.println("After update: ");
            // поліморфізм
            Vehicle[] list = {v, p, s};
            for (Vehicle x : list) {
                System.out.println(x.info());
            }
        }
    }
}