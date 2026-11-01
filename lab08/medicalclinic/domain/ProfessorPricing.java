package lab08.medicalclinic.domain;

public class ProfessorPricing implements ConsultationPricing {
    @Override
    public double calculate(double baseFee) {
        return baseFee * 2.0;
    }
}