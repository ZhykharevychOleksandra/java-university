package lab08.medicalclinic.domain;

public class LabTest {

    private final int id;
    private final String name;
    private final double price;

    public LabTest(int id, String name, double price) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Назва аналізу обов'язкова");
        }

        if (price < 0) {
            throw new IllegalArgumentException(
                "Ціна не може бути від'ємною");
        }

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}