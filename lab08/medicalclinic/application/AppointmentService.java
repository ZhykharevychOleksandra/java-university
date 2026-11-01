package lab08.medicalclinic.application;
import lab08.medicalclinic.domain.Appointment;
import lab08.medicalclinic.domain.InsurancePolicy;
import lab08.medicalclinic.domain.LabTest;
import lab08.medicalclinic.infrastructure.*;

public class AppointmentService {
    private final AppointmentRepository repository;
    private final SmsSender smsSender;
    private final InvoiceGenerator invoiceGenerator;
    private final InsurancePolicy insurancePolicy;

    public AppointmentService(
            AppointmentRepository repository,
            SmsSender smsSender,
            InvoiceGenerator invoiceGenerator,
            InsurancePolicy insurancePolicy) {

        this.repository = repository;
        this.smsSender = smsSender;
        this.invoiceGenerator = invoiceGenerator;
        this.insurancePolicy = insurancePolicy;
    }

    public String schedule(AppointmentRequest request) {

        Appointment appointment = Appointment.create(
            request.getAppointmentId(),
            request.getPatient(),
            request.getDoctor()
        );

        for (LabTest test : request.getTests()) {
            appointment.addLabTest(test);
        }

        appointment.confirm(insurancePolicy);

        repository.save(appointment);

        String invoice = invoiceGenerator.generate(appointment);

        smsSender.send(
            request.getPatient().getPhone(),
            "Ваш запис підтверджено. Номер: "
                + request.getAppointmentId()
        );

        return invoice;
    }
}
