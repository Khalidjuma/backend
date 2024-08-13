package com.example.demo.Services;

import com.example.demo.dto.AppointmentRequest;
import com.example.demo.model.Appointment;
import com.example.demo.model.Enum.AppointmentStatus;
import com.example.demo.model.Staff;
import com.example.demo.model.Student;
import com.example.demo.repo.Appointmentrepo;
import com.example.demo.repo.Staffrepo;
import com.example.demo.repo.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AppointmentServices {

    @Autowired
    private Appointmentrepo appointmentRepository;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private Staffrepo staffrepo;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveAppointment(AppointmentRequest appointment) {
        Appointment appointment1 =new Appointment();
        Student student = studentRepo.findById(appointment.getStudent())
                .orElseThrow(() -> new EntityNotFoundException("student with id "+ appointment.getStudent() + "not found"));
        Staff staff =staffrepo.findById(appointment.getStaff())
                .orElseThrow(() -> new EntityNotFoundException("staff with id "+ appointment.getStaff() + "not found"));
        appointment1.setStaff(staff);
        appointment1.setStudent(student);
        appointment1.setStatus(AppointmentStatus.Pending);
        Appointment savedAppointment= appointmentRepository.save(appointment1);
        return savedAppointment;
    }

    public  List<Appointment> changeStatus(Long Id){
        Appointment appointment = appointmentRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("appointment with that id  not found"));
        if(appointment.getStatus().equals(AppointmentStatus.Confirmed)){
            appointment.setStatus(AppointmentStatus.Pending);

        }else {
            appointment.setStatus(AppointmentStatus.Confirmed);
        }

        appointmentRepository.save(appointment);
        return List.of(appointment);
    }

    public ResponseEntity  updateAppointment(Long id, AppointmentRequest updatedAppointment) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("appointment with that id  not found"));

        appointment.setStudent(new Student(updatedAppointment.getStudent()));

        appointment.setStaff(new Staff(updatedAppointment.getStaff()));

        appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        appointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
        appointment.setStatus(updatedAppointment.getStatus());
        appointmentRepository.save(appointment);
        return  ResponseEntity.ok(Boolean.TRUE);
    }
}
