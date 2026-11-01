package lab08.medicalclinic.domain;

public class Doctor {
    private final int id;
    private final String name;
    private final String specialty;
    private final double baseConsultationFee;
    private final ConsultationPricing pricing;

    public Doctor(
            int id,
            String name,
            String specialty,
            double baseConsultationFee,
            ConsultationPricing pricing) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Ім'я лікаря обов'язкове");
        }

        if (specialty == null || specialty.isBlank()) {
            throw new IllegalArgumentException(
                "Спеціальність обов'язкова");
        }

        if (baseConsultationFee < 0) {
            throw new IllegalArgumentException(
                "Некоректна вартість консультації");
        }

        if (pricing == null) {
            throw new IllegalArgumentException(
                "Стратегія розрахунку обов'язкова");
        }

        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.baseConsultationFee = baseConsultationFee;
        this.pricing = pricing;
    }

    public double calculateConsultationCost() {
        return pricing.calculate(baseConsultationFee);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }
}