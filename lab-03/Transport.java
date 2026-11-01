// завдання 4.1 (абстрактні класи та інтерфейси)
public abstract class Transport {
    protected String brand;
    protected double payLoadCapacity;
    protected double speed;
    protected int year;

    public Transport(String brand, double payLoadCapacity, double speed, int year) {
        this.brand = brand;
        this.payLoadCapacity = payLoadCapacity;
        this.speed = speed;
        this.year = year;
    }

    public String info() {
        return transportType() + " " + brand + ", payload: " + payLoadCapacity + " t, " + year;
    }

    public double getPayLoadCapacity() {
        return payLoadCapacity;
    }

    public abstract double deliveryCost(double distance);
    public abstract String transportType();

}