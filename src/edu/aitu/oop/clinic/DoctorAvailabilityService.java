package edu.aitu.oop.clinic;

import edu.aitu.oop.clinic.domain.Doctor;
import edu.aitu.oop.clinic.repository.DoctorRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DoctorAvailabilityService {
    private final DoctorRepository doctorRepo;

    public DoctorAvailabilityService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public boolean isDoctorAvailable(int doctorId, LocalDateTime time) {
        Doctor doctor = doctorRepo.findById(doctorId);
        return doctor != null && doctor.isAvailableAt(time);
    }

    public List<LocalDateTime> getDoctorSchedule(int doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId);
        return doctor != null ? doctor.getAvailableSlots() : List.of();
    }
}
