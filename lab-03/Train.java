//завдання 4.3
public class Train extends Transport {
    private int wagon;
    public Train(String brand, double payLoadCapacity, double speed, int year, int wagon) { 
        super(brand, payLoadCapacity, speed, year);
        this.wagon = wagon;
    }

    @Override 
    public double deliveryCost(double distance) {
        return distance * 5;
    }

    @Override 
    public String transportType() {
        return "Train";
    }
    
    public void addWagon() {
        wagon++;
    }

    public int getWagon() {
        return wagon;
    }

    public double totalCapacity() {
        return payLoadCapacity * wagon;
    }
}
