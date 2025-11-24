# Clinic-Appointment-Patient-Records-System-CAPRS-
## Description
The Clinic Appointment & Patient Records System (CAPRS) is a Java console application designed to manage the core administrative functions of a small clinic or hospital. The system leverages Object-Oriented Programming (OOP) principles for data modeling and uses Java Serialization for persistent data storage.

This project focuses on centralizing and automating the management of:
- Patient Records: Storing demographic and preliminary medical issue data.
- Doctor Records: Tracking doctor specialization and contact information.
- Appointment Scheduling: Booking appointments and checking doctor availability.
- Data Persistence: Automatically saving and loading all records using files (.ser).

## Key Features 
- Data Modeling: Dedicated classes (Patient, Doctor, Appointment) for clear data structure.
- Persistent Storage: Data is saved to files (patients.ser, doctors.ser, etc.) using Java Serialization, allowing data to persist between application runs.
- Appointment Conflict Checking: Prevents scheduling a doctor for two different appointments at the exact same date and time.
- Java Streams API: Efficiently retrieves and filters data (e.g., getting a patient by ID, finding doctors by specialization, and viewing sorted appointments).
- Custom Exception Handling: Uses NotFoundException and ConflictException to manage user input errors and logical constraints gracefully.
- Console UI: Simple, menu-driven interface for system interaction.

## Technologies Used 
- Primary Language: Java
- Core Libraries: java.io (for Serialization), java.time (for Date and Time handling), java.util.List and java.util.stream (for data management and processing).
- Data Persistence: Java Serialization (no external database required).

## Installation and Setup ️
This project is a standard Java application and requires a Java Development Kit (JDK) to compile and run.

## Project Structure
Ensure your files are arranged in the following package structure:

src/
├── App/
│   └── CAPRSApp.java
├── clinic_data/
│   ├── Patient.java
│   ├── Doctor.java
│   └── Appointment.java
└── clinic_logic/
    ├── ClinicManager.java
    ├── FileHandler.java
    └── CustomExceptions.java
- Step 1: Compile the Java Files : Navigate to the source directory (src/) in your terminal and compile all the Java file 
Example compilation command (adjust based on your IDE/build tool)
javac App/*.java clinic_data/*.java clinic_logic/*.java -d classes

- Step 2: Run the Application : Run the main class from the compiled output directory (classes/)
java -cp classes App.CAPRSApp

## Usage ️
Once the application is running, you will be presented with the main menu.

- Start-up: The system automatically loads existing data from *.ser files and initializes with a few sample patients and doctors (hardcoded in CAPRSApp.java).
- Navigation: Enter the number corresponding to your desired menu option (1, 2, or 3) and press Enter.
- Scheduling Appointments:
   - Select 3. Appointment Scheduling.
   - Choose 1. Schedule New Appointment : You will be prompted to enter the Patient ID, Doctor ID, Date (dd-MM-yyyy), and Time (HH:mm).
   - The system will check for doctor availability and confirm the booking.
   - Exit: Select 0. Exit and Save to close the program. This will trigger the ClinicManager.saveData() method, writing all current records (patients, doctors, appointments) back to the .ser files for persistence.

## Code Architecture Overview 
The system is split into two logical package layers:

- Package	Purpose	Key Classes
   - clinic_data	Data Model: Simple Java objects representing core entities.	Patient, Doctor, Appointment
   - clinic_logic	Business Logic: Handles data loading, saving, and all core operations.	ClinicManager, FileHandler, CustomExceptions
  
