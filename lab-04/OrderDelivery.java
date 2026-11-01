// завдання 1 (варіант 3): закрита ієрархія OrderDelivery
public sealed interface OrderDelivery
        permits OrderDelivery.Processing, OrderDelivery.InTransit, OrderDelivery.Delivered {

    // склад обробляє замовлення
    record Processing(int warehouseId, double expectedWeight) implements OrderDelivery { }

    // замовлення в дорозі
    record InTransit(String courierName, int estimatedMinutes) implements OrderDelivery { }

    // замовлення доставлено
    record Delivered(String receiverSignature, boolean isDamaged) implements OrderDelivery { }
}
