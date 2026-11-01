public final class WarehouseOps {

    private WarehouseOps() { }

    public static void writeOffSpoiled(ProcessingItem item, int spoiled) {
        item.setQuantity(item.getQuantity() - spoiled);
        System.out.println("   [всередині writeOffSpoiled] стан змінено: " + item.getQuantity() + " шт");
    }

    public static void replaceItem(ProcessingItem item, int spoiled) {
        item = new ProcessingItem(item.getName(), item.getQuantity() - spoiled,
                                  item.getUnitWeightKg(), item.getCondition());
        System.out.println("   [всередині replaceItem] локальна копія: " + item.getQuantity() + " шт");
    }
}