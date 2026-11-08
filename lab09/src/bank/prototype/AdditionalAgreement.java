package lab09.src.bank.prototype;

public class AdditionalAgreement {
    private String description;

    public AdditionalAgreement(String description) {
        this.description = description;
    }

    public AdditionalAgreement copy() {
        return new AdditionalAgreement(description);
    }

    @Override
    public String toString() {
        return description;
    }
}