package lab08.medicalclinic.infrastructure;
import lab08.medicalclinic.domain.*;

public class ConsultationPricingFactory {

    public static ConsultationPricing create(int category) {

        switch (category) {

            case 1:
                return new GeneralPricing();

            case 2:
                return new SpecialistPricing();

            case 3:
                return new ProfessorPricing();

            default:
                throw new IllegalArgumentException(
                    "Невідома категорія лікаря");
        }
    }
}