package lab08.medicalclinic.domain;
// 1.3
public class SpecialistPricing implements ConsultationPricing {
    @Override
    public double calculate(double baseFee) {
        return baseFee * 1.4;
    }
}