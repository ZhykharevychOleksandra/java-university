// незмінний тип: record гарантує final-поля та відсутність сеттерів
public record InventoriedItem(String name, int quantity, double unitWeightKg, StorageConditions condition) {

    // «Зміна» можлива лише як створення нового об'єкта
    public InventoriedItem withQuantity(int newQuantity) {
        return new InventoriedItem(name, newQuantity, unitWeightKg, condition);
    }

    // Списання повертає НОВИЙ об'єкт; якщо проігнорувати результат — зміна «втрачена»
    public InventoriedItem writeOffSpoiled(int spoiled) {
        return withQuantity(quantity - spoiled);
    }
}