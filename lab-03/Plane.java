// 3.2
public class Plane extends Vehicle {
    private double height;
    public Plane() { super(); }

    public Plane(String type, double speed, int year, double height) {
        super(type, speed, year);
        this.height = height;
    }

    public Plane(String type, double speed) {
        this(type, speed, 2000, 0);
    }

    @Override 
    public double cost() {
        return speed * year;
    }

    @Override 
    public void update() {
        year -= 3;
    }

    @Override 
    public String info() {
        return super.info() + ", Height: " + height; 
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
}