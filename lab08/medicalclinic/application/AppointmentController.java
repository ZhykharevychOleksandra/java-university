package lab08.medicalclinic.application;

public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(
            AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
    }

    public String scheduleAppointment(
            AppointmentRequest request) {

        return appointmentService.schedule(request);
    }
}
