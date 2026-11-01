// завдання 4.2
public class Car extends Transport{
    private double weight;
    public Car(String brand, double payLoadCapacity, double speed, int year, double weight) {
        super(brand, payLoadCapacity, speed, year);
        this.weight = weight;
    }

    @Override 
    public double deliveryCost(double distance) {
        return distance * 12;
    }

    @Override 
    public String transportType() {
        return "Car";
    }

    public boolean isTruck() {
        return weight > 3;
    }

    public String refuel() {
        return "Refueling was successful";
    }
}
