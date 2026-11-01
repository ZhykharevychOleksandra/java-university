package lab08.medicalclinic.infrastructure;
import java.util.HashMap;
import java.util.Map;
import lab08.medicalclinic.domain.Appointment;

public class InMemoryAppointmentRepository
        implements AppointmentRepository {

    private final Map<Integer, Appointment> appointments =
            new HashMap<>();

    @Override
    public void save(Appointment appointment) {

        if (appointment == null) {
            throw new IllegalArgumentException(
                "Запис не може бути порожнім");
        }

        appointments.put(
            appointment.getAppointmentId(), appointment);
    }

    @Override
    public Appointment findById(int id) {
        return appointments.get(id);
    }
}