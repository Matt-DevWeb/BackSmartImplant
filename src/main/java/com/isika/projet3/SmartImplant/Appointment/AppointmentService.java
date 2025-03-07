// package com.isika.projet3.SmartImplant.Appointment;

// import org.springframework.stereotype.Service;

// import com.isika.projet3.SmartImplant.models.Appointment;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class AppointmentService {
// private final AppointmentRepository appointmentRepository;

// public AppointmentService(AppointmentRepository appointmentRepository) {
// this.appointmentRepository = appointmentRepository;
// }

// public List<Appointment> getAllAppointments() {
// return appointmentRepository.findAll();
// }

// public Optional<Appointment> getAppointmentById(Long id) {
// return appointmentRepository.findById(id);
// }

// public Appointment saveAppointment(Appointment appointment) {
// return appointmentRepository.save(appointment);
// }

// public void deleteAppointment(Long id) {
// appointmentRepository.deleteById(id);
// }
// }