// мутабельний клас: стан можна змінювати через сеттери (поведінка reference type)
public class ProcessingItem {
    private String name;
    private int quantity;
    private double unitWeightKg;
    private StorageConditions condition;

    public ProcessingItem(String name, int quantity, double unitWeightKg, StorageConditions condition) {
        this.name = name;
        this.quantity = quantity;
        this.unitWeightKg = unitWeightKg;
        this.condition = condition;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitWeightKg() { return unitWeightKg; }
    public StorageConditions getCondition() { return condition; }

    @Override
    public String toString() {
        return name + ": " + quantity + " шт, " + unitWeightKg + " кг/шт, умова: " + condition;
    }
}