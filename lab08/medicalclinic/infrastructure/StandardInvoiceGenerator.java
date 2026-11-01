package lab08.medicalclinic.infrastructure;
import lab08.medicalclinic.domain.Appointment;

public class StandardInvoiceGenerator
        implements InvoiceGenerator {
    @Override
    public String generate(Appointment appointment) {

        if (appointment == null) {
            throw new IllegalArgumentException(
                "Запис не може бути порожнім");
        }

        return "Квитанція прийому #" +
            appointment.getAppointmentId()
            + "\n Пацієнт: "
            + appointment.getPatient().getFullName()
            + "\n Лікар: "
            + appointment.getDoctor().getName()
            + "\n Вартість консультації: "
            + appointment.getConsultationCost()
            + " грн"
            + "\n Вартість аналізів: "
            + appointment.getLabTestsCost()
            + " грн"
            + "\n Загальна вартість: "
            + appointment.getTotalCost()
            + " грн"
            + "\n Страхове покриття: "
            + appointment.getInsuranceCoverage()
            + " грн"
            + "\n До сплати: "
            + appointment.getPatientPayable()
            + " грн";
    }
}