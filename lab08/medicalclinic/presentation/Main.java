package lab08.medicalclinic.presentation;
import lab08.medicalclinic.application.*;
import lab08.medicalclinic.domain.InsurancePolicy;
import lab08.medicalclinic.infrastructure.*;

public class Main {
    public static void main(String[] args) {

        AppointmentRepository repository =
                new InMemoryAppointmentRepository();

        SmsSender smsSender = new ConsoleSmsSender();

        InvoiceGenerator invoiceGenerator =
                new StandardInvoiceGenerator();

        InsurancePolicy policy = new InsurancePolicy(0.80);

        AppointmentService service =
                new AppointmentService(
                    repository,
                    smsSender,
                    invoiceGenerator,
                    policy
                );

        AppointmentController controller =
                new AppointmentController(service);

        ConsoleUI ui = new ConsoleUI(controller);

        ui.start();
    }
}
