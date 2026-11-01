package lab08.medicalclinic.infrastructure;
import lab08.medicalclinic.domain.Appointment;

public interface InvoiceGenerator {
    String generate(Appointment appointment);
}