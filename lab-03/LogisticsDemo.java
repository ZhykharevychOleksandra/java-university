// 4.6 main
import java.util.List;

public class LogisticsDemo {
    public static void main(String[] args) {
        LogisticsCompany company = new LogisticsCompany();

        Car volvo = new Car("Volvo", 8.0, 90, 2019, 7.5);
        Car skoda = new Car("Skoda", 0.5, 180, 2021, 1.4);
        Train intercity = new Train("Intercity", 40.0, 120, 2015, 10);
        
        company.registerTransport(volvo);
        company.registerTransport(skoda);
        company.registerTransport(intercity);

        System.out.println("All transport: ");
        company.printAll();

        System.out.println("Own methods: ");
        System.out.println("Volvo is truck: " + volvo.isTruck());
        System.out.println("Skoda is truck: " + skoda.isTruck());
        System.out.println(volvo.refuel());
        
        System.out.println("Train wagons before: " + intercity.getWagon());
        intercity.addWagon();
        System.out.println("Train wagons after: " + intercity.getWagon());
        System.out.println("Train total capacity: " + intercity.totalCapacity());

        System.out.println("Payload >= 5 : ");
        List<Transport> found = company.searchByPayLoadCapacity(5);
        for (Transport t : found) {
            System.out.println(t.info() + ", cost for 100 km: " + t.deliveryCost(100));
        }
    }
}
