package lab08.medicalclinic.presentation;
import java.util.Scanner;
import lab08.medicalclinic.application.AppointmentController;
import lab08.medicalclinic.application.AppointmentRequest;
import lab08.medicalclinic.domain.*;
import lab08.medicalclinic.infrastructure.ConsultationPricingFactory;

public class ConsoleUI {

    private final AppointmentController controller;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(AppointmentController controller) {
        this.controller = controller;
    }

    public void start() {

        System.out.println("=== Медична клініка ===");

        System.out.print("Ім'я пацієнта: ");
        String name = scanner.nextLine();

        System.out.println(
            "Категорія лікаря: 1-General, 2-Specialist, 3-Professor");

        int category = Integer.parseInt(scanner.nextLine());

        ConsultationPricing pricing =
                ConsultationPricingFactory.create(category);

        Patient patient = new Patient(
            22, name, true, "INS-99201", "+380684445566");

        Doctor doctor = new Doctor(
            101, "Др. Ковальчук", "Кардіолог", 800, pricing);

        LabTest bloodTest = new LabTest(
            1, "Загальний аналіз крові", 250);

        LabTest urineTest = new LabTest(
            2, "Загальний аналіз сечі", 180);

        AppointmentRequest request = new AppointmentRequest(
            patient,
            doctor,
            3001,
            bloodTest,
            urineTest
        );

String invoice = controller.scheduleAppointment(request);

        System.out.println(invoice);
    }
}