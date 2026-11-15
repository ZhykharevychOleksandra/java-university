package lab12.prutcredit.composite;

public enum UnitLevel {
    HEAD_OFFICE("Головний офіс"),
    REGION("Регіональне управління"),
    BRANCH("Відділення"),
    MINI_BRANCH("Міні-відділення");

    private final String label;

    UnitLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}