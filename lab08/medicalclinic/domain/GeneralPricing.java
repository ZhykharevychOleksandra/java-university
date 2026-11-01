package lab08.medicalclinic.domain;
// 1.2
public class GeneralPricing implements ConsultationPricing {
    @Override
    public double calculate(double baseFee) {
        return baseFee;
    }
}