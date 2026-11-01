package lab08.medicalclinic.infrastructure;
import lab08.medicalclinic.domain.Appointment;

public interface AppointmentRepository {
    void save(Appointment appointment);
    Appointment findById(int id);
}