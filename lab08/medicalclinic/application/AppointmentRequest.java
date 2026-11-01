package lab08.medicalclinic.application;
import lab08.medicalclinic.domain.Patient;
import lab08.medicalclinic.domain.Doctor;
import lab08.medicalclinic.domain.LabTest;

public class AppointmentRequest {

    private final Patient patient;
    private final Doctor doctor;
    private final int appointmentId;
    private final LabTest[] tests;

    public AppointmentRequest(
            Patient patient,
            Doctor doctor,
            int appointmentId,
            LabTest... tests) {

        this.patient = patient;
        this.doctor = doctor;
        this.appointmentId = appointmentId;
        this.tests = tests.clone();
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public LabTest[] getTests() {
        return tests.clone();
    }
}
