package clinic_data;
import java.io.Serializable;
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    private int patientID;
    private String name;
    private int age;
    private String phoneNumber;
    private String issueFaced;

    public Patient(int patientID, String name, int age, String phoneNumber , String issueFaced) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.issueFaced = issueFaced;
    }
    public int getPatientID() { return patientID; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getAge() { return age; }
    public String getIssueFaced() { return issueFaced; }
   
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Phone: %s | Issue Faced: %s", 
                             patientID, name, age, phoneNumber, issueFaced);
    }
}