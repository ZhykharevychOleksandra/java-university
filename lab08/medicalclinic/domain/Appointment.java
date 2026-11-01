package lab08.medicalclinic.domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Appointment {

    private final int appointmentId;
    private final Patient patient;
    private final Doctor doctor;

    private final List<LabTest> labTests = new ArrayList<>();

    private double consultationCost;
    private double labTestsCost;
    private double insuranceCoverage;
    private double patientPayable;

    private String status;

    private Appointment(int id, Patient patient, Doctor doctor) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "Некоректний номер запису");
        }

        if (patient == null || doctor == null) {
            throw new IllegalArgumentException(
                "Пацієнт та лікар обов'язкові");
        }

        this.appointmentId = id;
        this.patient = patient;
        this.doctor = doctor;
        this.status = "Scheduled";
    }

    // Creator
    public static Appointment create(
            int id, Patient patient, Doctor doctor) {

        return new Appointment(id, patient, doctor);
    }

    // Information Expert
    public void addLabTest(LabTest test) {

        if (test == null) {
            throw new IllegalArgumentException(
                "Аналіз не може бути порожнім");
        }

        if ("Confirmed".equals(status)) {
            throw new IllegalStateException(
                "Не можна змінювати підтверджений запис");
        }

        labTests.add(test);
    }

    public double calculateLabTestsCost() {

        double total = 0;

        for (LabTest test : labTests) {
            total += test.getPrice();
        }

        return total;
    }

    // Information Expert
    public void confirm(InsurancePolicy policy) {

        if ("Confirmed".equals(status)) {
            throw new IllegalStateException(
                "Запис уже підтверджений");
        }

        if (policy == null) {
            throw new IllegalArgumentException(
                "Страховий поліс обов'язковий");
        }

        consultationCost =
                doctor.calculateConsultationCost();

        labTestsCost = calculateLabTestsCost();

        double totalCost =
                consultationCost + labTestsCost;

        insuranceCoverage = patient.hasInsurance()
                ? policy.calculateCoverage(totalCost)
                : 0;

        patientPayable = totalCost - insuranceCoverage;

        status = "Confirmed";
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public List<LabTest> getLabTests() {
        return Collections.unmodifiableList(labTests);
    }

    public double getConsultationCost() {
        return consultationCost;
    }

    public double getLabTestsCost() {
        return labTestsCost;
    }

    public double getTotalCost() {
        return consultationCost + labTestsCost;
    }

    public double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public double getPatientPayable() {
        return patientPayable;
    }

    public String getStatus() {
        return status;
    }
}