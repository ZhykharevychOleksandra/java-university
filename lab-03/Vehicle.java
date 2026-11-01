// завдання 3.1 ієрархія (наслідування та поліморфізм)
public class Vehicle implements AutoCloseable{
    protected String type;
    protected double speed;
    protected int year;

    public Vehicle() { }

    public Vehicle (String type, double speed, int year) {
        this.type = type;
        this.speed = speed;
        this.year = year;
    }

    public Vehicle(String type, double speed) {
        this(type, speed, 2000);
    }

    public double cost() {
        return (speed * year) / 100;
    }

    public void update() {
        year -= 5;
    }

    public String info() {
        return "Type: " + type + ", Year of manufacture: " + year + ", Cost: " + cost();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Override 
    public void close() {
        System.out.println("The laboratory work was completed by a second-year student Zhykharevych Oleksandra");
    }
}