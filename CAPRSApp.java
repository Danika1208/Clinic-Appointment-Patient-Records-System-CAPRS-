package App;

import clinic_data.Doctor;
import clinic_data.Patient;
import clinic_logic.ClinicManager;
import clinic_logic.CustomExceptions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CAPRSApp {
    private static ClinicManager manager = new ClinicManager();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextPatientId = 1001;
    private static int nextDoctorId = 5001;

    public static void main(String[] args) {
        System.out.println(" Clinic Appointment & Patient Records System (CAPRS) ");
           
        manager.addDoctor(new Doctor(nextDoctorId++, "Dr. Vyas", "Cardiology", "8310245698"));
        manager.addDoctor(new Doctor(nextDoctorId++, "Dr. Sharma", "Pediatrics", "5550100248"));
        manager.addDoctor(new Doctor(nextDoctorId++, "Dr. Tiwari", "Radiology", "8325497412"));
        manager.addDoctor(new Doctor(nextDoctorId++, "Dr. Shah", "Oncology", "9230145686"));
        manager.addDoctor(new Doctor(nextDoctorId++, "Dr. Puri", "Dental Care", "8368028395"));
        manager.addDoctor(new Doctor(nextDoctorId++, "Dr. Pandey", "Neurology", "8310324077"));

        manager.addPatient(new Patient(nextPatientId++, "Aryan Tiwari", 40, "9236541083", "Heart burn, chest pains , trouble breathing"));
        manager.addPatient(new Patient(nextPatientId++, "Yash Sharma", 20, "9119104832", "X-ray of leg , hairline fracture"));
        manager.addPatient(new Patient(nextPatientId++, "Sanskriti ", 36, "8365421087", "Back pain , Balance issues , partially deaf from one ear"));
        manager.addPatient(new Patient(nextPatientId++, "Arya Phutane", 12, "8632140549 (parent)", "Fever, vomitting"));
        manager.addPatient(new Patient(nextPatientId++, "Himanshu Vaishe", 03, "6321405687 (parent)",  "Vaccination"));


        int choice = -1;
        while (choice != 0) {
            displayMainMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                processChoice(choice);
            } catch (NumberFormatException e) {
                System.err.println("Please enter a number");
            }
        }
        
        manager.saveData(); 
        System.out.println("System shutting down. Goodbye!");
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Appointment Scheduling");
        System.out.println("0. Exit and Save");
        System.out.print("Enter choice: ");
    }
    
    private static void processChoice(int choice) {
        switch (choice) {
            case 1: patientMenu(); break;
            case 2: doctorMenu(); break;
            case 3: appointmentMenu(); break;
            case 0: break;
            default: System.out.println("Invalid choice. Try again.");
        }
    }

    private static void patientMenu() {
        System.out.println("\n--- Patient Management ---");
        System.out.println("1. Add New Patient");
        System.out.println("2. View All Patients");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                System.out.print("Enter Name: "); String name = scanner.nextLine();
                System.out.print("Enter Age: "); int age = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Phone: "); String phone = scanner.nextLine();
                System.out.print("Enter Issues Faced: "); String issuesFaced = scanner.nextLine(); 
                
                manager.addPatient(new Patient(nextPatientId++, name, age, phone, issuesFaced)); 
                
                System.out.println(" Patient added with ID: " + (nextPatientId - 1));
            } else if (choice == 2) {
                System.out.println("\n--- Patient List ---");
                manager.getAllPatients().forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input.");
        }
    }

    private static void doctorMenu() {
        System.out.println("\n--- Doctor Management ---");
        System.out.println("1. Add New Doctor");
        System.out.println("2. View All Doctors");
        System.out.println("3. View Doctors by Specialization");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                System.out.print("Enter Name: "); String name = scanner.nextLine();
                System.out.print("Enter Specialization: "); String spec = scanner.nextLine();
                System.out.print("Enter Contact Number: "); String contactNumber = scanner.nextLine(); // <-- NEW INPUT
                
                manager.addDoctor(new Doctor(nextDoctorId++, name, spec, contactNumber)); 
                
                System.out.println(" Doctor added with ID: " + (nextDoctorId - 1));
            } else if (choice == 2) {
                System.out.println("\n--- Doctor List ---");
                manager.getAllDoctors().forEach(System.out::println);
            } else if (choice == 3) {
                 System.out.print("Enter Specialization to filter: "); String spec = scanner.nextLine();
                 manager.getDoctorsBySpecialization(spec).forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.err.println(" Invalid input.");
        }
    }

    private static void appointmentMenu() {
        System.out.println("\n--- Appointment Scheduling ---");
        System.out.println("1. Schedule New Appointment");
        System.out.println("2. View Appointments by Doctor");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                scheduleAppointment();
            } else if (choice == 2) {
                System.out.print("Enter Doctor ID: ");
                int docId = Integer.parseInt(scanner.nextLine());
                manager.viewAppointmentsByDoctor(docId).forEach(System.out::println);
            }
        } catch (NumberFormatException | NotFoundException e) {
            System.err.println(" Error: " + e.getMessage());
        }
    }

    private static void scheduleAppointment() {
        try {
            System.out.print("Enter Patient ID: "); int pId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Doctor ID: "); int dId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Date (dd-MM-yyyy): "); String dateStr = scanner.nextLine();
            System.out.print("Enter Time (HH:mm - 24hr format): "); String timeStr = scanner.nextLine();
            
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
            LocalDate date = LocalDate.parse(dateStr, dateFormat);
            LocalTime time = LocalTime.parse(timeStr, timeFormat);
            
            manager.scheduleAppointment(pId, dId, date, time);
            
        } catch (NumberFormatException e) {
            System.err.println(" Invalid ID/Input format. Please use numbers.");
        } catch (DateTimeParseException e) {
            System.err.println(" Invalid Date or Time format. Use dd-MM-yyyy and HH:mm.");
        } catch (NotFoundException | ConflictException e) {
            System.err.println("Scheduling Failed: " + e.getMessage()); // Error Handling Strategy
        }
    }
}