// package com.isika.projet3.SmartImplant.Appointment;

// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import java.util.List;
// import java.util.Optional;

// import com.isika.projet3.SmartImplant.models.Appointment;

// @RestController
// @RequestMapping("/appointments")
// public class AppointmentController {
// private final AppointmentService appointmentService;

// public AppointmentController(AppointmentService appointmentService) {
// this.appointmentService = appointmentService;
// }

// @GetMapping
// public List<Appointment> getAllAppointments() {
// return appointmentService.getAllAppointments();
// }

// @GetMapping("/{id}")
// public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
// return appointmentService.getAppointmentById(id);
// }

// @PostMapping
// public Appointment createAppointment(@RequestBody Appointment appointment) {
// return appointmentService.saveAppointment(appointment);
// }

// @DeleteMapping("/{id}")
// public void deleteAppointment(@PathVariable Long id) {
// appointmentService.deleteAppointment(id);
// }
// }
