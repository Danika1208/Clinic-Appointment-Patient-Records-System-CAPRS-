package clinic_data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    private int appointmentID;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;

    public Appointment(int appointmentID, Patient patient, Doctor doctor, LocalDate date, LocalTime time) {
        this.appointmentID = appointmentID;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    // THIS METHOD IS CRUCIAL FOR THE STREAM CODE TO WORK!
    public int getAppointmentID() { 
        return appointmentID; 
    } 
    
    // Other Getters
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    // ... other standard setters/getters

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("APPT ID: %d | Date: %s @ %s | Doctor: %s | Patient: %s",
                             appointmentID, date.format(dateFormat), time, 
                             doctor.getName(), patient.getName());
    }
}
