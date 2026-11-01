// завдання 2: маршрутизація через switch expression
// Жодного default: sealed-ієрархія дозволяє компілятору Java перевірити вичерпність.
// Якщо додати новий підтип у permits і не обробити його тут — код не скомпілюється.
public final class DeliveryRouter {

    private DeliveryRouter() { }

    public static String route(OrderDelivery order) {
        return switch (order) {

            // ── Processing ──
            // Record pattern (деконструкція) + guard: некоректний склад
            case OrderDelivery.Processing(int id, double weight) when id <= 0
                    -> "ПОМИЛКА: некоректний номер складу, перевірити дані замовлення";
            // Record pattern + guard: важка вага
            case OrderDelivery.Processing(int id, double weight) when weight > 50.0
                    -> "Важке відправлення зі складу №" + id + ": потрібен вантажний транспорт";
            // Record pattern: стандартне відправлення
            case OrderDelivery.Processing(int id, double weight)
                    -> "Склад №" + id + " комплектує замовлення (" + weight + " кг), стандартне відправлення";

            // InTransit
            // estimatedMinutes > 60 → попередження про затримку
            case OrderDelivery.InTransit(String name, int minutes) when minutes > 60
                    -> "УВАГА: затримка кур'єра " + name + ", очікуваний час " + minutes + " хв";
            // діапазон: кур'єр майже на місці
            case OrderDelivery.InTransit(String name, int minutes) when minutes >= 0 && minutes <= 15
                    -> "Кур'єр " + name + " прибуде за кілька хвилин: сповістити отримувача";
            // звичайний рух
            case OrderDelivery.InTransit(String name, int minutes)
                    -> "Кур'єр " + name + " у дорозі, залишилось " + minutes + " хв";

            // ── Delivered ──
            // пошкоджено і підпису немає (логічне &&)
            case OrderDelivery.Delivered(String sig, boolean damaged)
                    when damaged && (sig == null || sig.isEmpty())
                    -> "Пошкоджене замовлення без підпису: відкрити претензію та викликати менеджера";
            // пошкоджено
            case OrderDelivery.Delivered(String sig, boolean damaged) when damaged
                    -> "Замовлення пошкоджене: відкрити рекламацію та запропонувати компенсацію";
            // ціле, але без підпису
            case OrderDelivery.Delivered(String sig, boolean damaged) when sig == null || sig.isEmpty()
                    -> "Підпис отримувача відсутній: запросити підтвердження доставки";
            // усе гаразд
            case OrderDelivery.Delivered(String sig, boolean damaged)
                    -> "Доставлено успішно, підпис: " + sig;
        };
    }
}
