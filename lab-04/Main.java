import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4(args);
    }

    // завдання 1: records, неруйнівна мутація 
    private static void task1() {
        System.out.println("=== Завдання 1: неруйнівна мутація (новий об'єкт через конструктор) ===");

        var processing = new OrderDelivery.Processing(7, 12.5);
        var inTransit = new OrderDelivery.InTransit("Олег", 40);
        var delivered = new OrderDelivery.Delivered("І. Петренко", false);

        // У Java немає оператора with — створюємо новий record, передаючи поля старого
        var processing2 = new OrderDelivery.Processing(processing.warehouseId(), 80.0);
        var inTransit2 = new OrderDelivery.InTransit(inTransit.courierName(), 90);
        var delivered2 = new OrderDelivery.Delivered(delivered.receiverSignature(), true);

        System.out.println("Оригінал: " + processing);
        System.out.println("Копія:    " + processing2);
        System.out.println("Оригінал: " + inTransit);
        System.out.println("Копія:    " + inTransit2);
        System.out.println("Оригінал: " + delivered);
        System.out.println("Копія:    " + delivered2);
        System.out.println("Оригінал не змінився? "
                + (processing.expectedWeight() == 12.5 && inTransit.estimatedMinutes() == 40 && !delivered.isDamaged()));
        System.out.println();
    }

    // завдання 2: колекція + switch expression 
    private static void task2() {
        System.out.println("=== Завдання 2: маршрутизація ===");

        OrderDelivery[] orders = {
                new OrderDelivery.Processing(0, 5.0),              // некоректний склад
                new OrderDelivery.Processing(3, 75.0),             // важке
                new OrderDelivery.Processing(3, 10.0),             // стандартне
                new OrderDelivery.InTransit("Марія", 75),          // затримка (>60)
                new OrderDelivery.InTransit("Іван", 10),           // майже на місці
                new OrderDelivery.InTransit("Андрій", 30),         // звичайний рух
                new OrderDelivery.Delivered("", true),             // пошкоджено, без підпису
                new OrderDelivery.Delivered("С. Коваль", true),    // пошкоджено
                new OrderDelivery.Delivered("", false),            // без підпису
                new OrderDelivery.Delivered("Т. Мельник", false)   // все добре
        };

        for (OrderDelivery order : orders) {
            System.out.println(order + "\n   -> " + DeliveryRouter.route(order));
        }
        System.out.println();
    }

    // завдання 3
    private static void task3() {
        System.out.println("=== Завдання 3: enum, мутабельний клас та незмінний record ===");

        // enum як об'єкт: код і опис
        for (StorageConditions c : StorageConditions.values()) {
            System.out.println(c + " -> код " + c.getCode() + ", " + c.getDescription());
        }
        int mask = StorageConditions.toMask(EnumSet.of(StorageConditions.FRAGILE, StorageConditions.PERISHABLE));
        System.out.println("Маска FRAGILE|PERISHABLE = " + mask + ", розшифровка: " + StorageConditions.fromMask(mask));
        System.out.println();

        // мутабельний клас
        var item = new ProcessingItem("Молоко", 100, 1.0, StorageConditions.PERISHABLE);
        System.out.println("Початково: " + item);

        System.out.println("1) Зміна стану через посилання — writeOffSpoiled(item, 20):");
        WarehouseOps.writeOffSpoiled(item, 20);
        System.out.println("   Оригінал після виклику: " + item.getQuantity() + " шт  (змінився)");

        System.out.println("2) Перепризначення посилання в методі — replaceItem(item, 20):");
        WarehouseOps.replaceItem(item, 20);
        System.out.println("   Оригінал після виклику: " + item.getQuantity() + " шт  (НЕ змінився, мутація «втрачена»)");
        System.out.println();

        // Незмінний record
        var inventoried = new InventoriedItem("Кефір", 50, 0.9, StorageConditions.PERISHABLE);
        System.out.println("Незмінний record: " + inventoried);
      
        System.out.println("Після writeOffSpoiled(5) без збереження результату: " + inventoried);

        InventoriedItem updated = inventoried.writeOffSpoiled(5); // правильно: беремо нову копію
        System.out.println("Нова копія з результату:                   " + updated);
        System.out.println("Оригінал:                                  " + inventoried);
        System.out.println();
    }

    // завдання 4: дата і час
    private static void task4(String[] args) {
        System.out.println("=== Завдання 4: тарифікація дзвінка ===");

        if (List.of(args).contains("demo")) {
            CallBilling.run(LocalDateTime.of(2026, 10, 18, 9, 15, 30),
                            LocalDateTime.of(2026, 10, 18, 9, 22, 45),
                            new BigDecimal("1.50"));
        } else {
            CallBilling.runInteractive();
        }
    }
}
