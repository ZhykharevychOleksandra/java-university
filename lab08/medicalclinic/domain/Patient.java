package lab08.medicalclinic.domain;

public class Patient {
    private final int id;
    private final String fullName;
    private final boolean hasInsurance;
    private final String insurancePolicyNumber;
    private final String phone;

    public Patient(int id, String fullName,
                   boolean hasInsurance,
                   String insurancePolicyNumber,
                   String phone) {

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException(
                "Ім'я пацієнта обов'язкове");
        }

        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException(
                "Номер телефону обов'язковий");
        }

        if (hasInsurance &&
                (insurancePolicyNumber == null ||
                 insurancePolicyNumber.isBlank())) {
            throw new IllegalArgumentException(
                "Номер страхового поліса обов'язковий");
        }

        this.id = id;
        this.fullName = fullName;
        this.hasInsurance = hasInsurance;
        this.insurancePolicyNumber = insurancePolicyNumber;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }
}