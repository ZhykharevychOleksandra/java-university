package lab12.prutcredit.flyweight;

public final class Merchant {
    private static int instanceCount = 0;

    private final String id;
    private final String name;
    private final String logo;
    private final String category;
    private final int mcc;
    private final String address;

    Merchant(String id, String name, String logo, String category, int mcc, String address) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.category = category;
        this.mcc = mcc;
        this.address = address;
        instanceCount++;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public String getCategory() {
        return category;
    }

    public int getMcc() {
        return mcc;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name + " (MCC " + mcc + ", " + category + ", " + address + ")";
    }
}
