package clinic_logic;

import clinic_data.Appointment;
import clinic_data.Doctor;
import clinic_data.Patient;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    
    // File names for persistence
    private static final String PATIENT_FILE = "patients.ser";
    private static final String DOCTOR_FILE = "doctors.ser";
    private static final String APPOINTMENT_FILE = "appointments.ser";

    // --- Save Methods ---
    public static void saveAllData(List<Patient> patients, List<Doctor> doctors, List<Appointment> appointments) {
        saveList(patients, PATIENT_FILE);
        saveList(doctors, DOCTOR_FILE);
        saveList(appointments, APPOINTMENT_FILE);
        System.out.println("✅ All data saved successfully.");
    }

    private static void saveList(List<?> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.err.println("❌ Error saving data to " + fileName + ": " + e.getMessage());
        }
    }
    
    // --- Load Methods (Specific and Type-Safe) ---
    
    // We suppress warnings here because ObjectInputStream requires unchecked casting
    @SuppressWarnings("unchecked")
    private static <T> List<T> loadList(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>(); // Return an empty list if file not found/empty
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            // The magic happens here: read the object and cast it to a List<T>
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error loading data from " + fileName + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Public getters to load data, now completely type-safe for the ClinicManager
    public static List<Patient> loadPatients() {
        return loadList(PATIENT_FILE);
    }
    
    public static List<Doctor> loadDoctors() {
        return loadList(DOCTOR_FILE);
    }
    
    public static List<Appointment> loadAppointments() {
        return loadList(APPOINTMENT_FILE);
    }
}