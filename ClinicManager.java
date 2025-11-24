package clinic_logic; // FIX: Corrected package name convention (dot instead of underscore)

import clinic_data.*;
import clinic_logic.CustomExceptions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ClinicManager { // <-- FIX 1: Missing opening brace for the class body

    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private int nextAppointmentId = 1;

    public ClinicManager() {

        this.patients = FileHandler.loadPatients();
        this.doctors = FileHandler.loadDoctors();
        this.appointments = FileHandler.loadAppointments();

        System.out.println("✅ ClinicManager initialized. Data loaded.");
        
        // This stream logic is correct for finding the next available ID
        if (!appointments.isEmpty()) {
             nextAppointmentId = appointments.stream()
                                             .mapToInt(Appointment::getAppointmentID) // Requires getAppointmentID() method in Appointment.java
                                             .max()
                                             .orElse(0) + 1;
        }
    } 
    // End of ClinicManager constructor

    // --- Data Persistence ---
    public void saveData() {
        FileHandler.saveAllData(patients, doctors, appointments);
    }

    // --- Patient Management (CRUD) ---
    public void addPatient(Patient p) { patients.add(p); }
    public List<Patient> getAllPatients() { return patients; }
    public Patient getPatientById(int id) throws NotFoundException {
        return patients.stream()
                        .filter(p -> p.getPatientID() == id)
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("Patient with ID " + id + " not found."));
    }

    public void addDoctor(Doctor d) { doctors.add(d); }
    public List<Doctor> getAllDoctors() { return doctors; }
    public Doctor getDoctorById(int id) throws NotFoundException {
        return doctors.stream()
                      .filter(d -> d.getDoctorID() == id)
                      .findFirst()
                      .orElseThrow(() -> new NotFoundException("Doctor with ID " + id + " not found."));
    }
    
    public List<Doctor> getDoctorsBySpecialization(String spec) {
        return doctors.stream()
                      .filter(d -> d.getSpecialization().equalsIgnoreCase(spec))
                      .collect(Collectors.toList());
    }


    private boolean isDoctorAvailable(int doctorId, LocalDate date, LocalTime time) {
        return appointments.stream()
                           .noneMatch(a -> a.getDoctor().getDoctorID() == doctorId &&
                                           a.getDate().equals(date) &&
                                           a.getTime().equals(time));
    }

    public void scheduleAppointment(int patientId, int doctorId, LocalDate date, LocalTime time) 
           throws NotFoundException, ConflictException {
        
        Patient patient = getPatientById(patientId);
        Doctor doctor = getDoctorById(doctorId);

        if (!isDoctorAvailable(doctorId, date, time)) {
            throw new ConflictException("Doctor " + doctor.getName() + " is already booked on " + date + " at " + time + ".");
        }

        Appointment newAppt = new Appointment(nextAppointmentId++, patient, doctor, date, time);
        appointments.add(newAppt);
        System.out.println("\n✅ Appointment Scheduled: " + newAppt);
    }
    
    public List<Appointment> viewAppointmentsByDoctor(int doctorId) throws NotFoundException { // <-- FIX 2: Missing opening brace
        getDoctorById(doctorId); // Check if doctor exists
        return appointments.stream()
                           .filter(a -> a.getDoctor().getDoctorID() == doctorId)
                           .sorted((a1, a2) -> a1.getDate().compareTo(a2.getDate()))
                           .collect(Collectors.toList()); // <-- FIX 3: Incomplete stream chain
    } // <-- FIX 2: Missing closing brace

} // <-- FIX 1: Missing closing brace for the class body