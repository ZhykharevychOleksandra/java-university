// 3.3
public class Ship extends Vehicle {
    private String port; 
    public Ship() { super(); }

    public Ship(String type, double speed, int year, String port) {
        super(type, speed, year);
        this.port = port;
    } 

    public Ship(String type, double speed) {
        this(type, speed, 2000, "Unknown");
    }

    @Override 
    public double cost() {
        return (speed * year) / 10;
    }

    @Override 
    public void update() {
        year -= 5;
    }

    @Override 
    public String info() {
        return super.info() + ", Port: " + port;
    }

    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
}
