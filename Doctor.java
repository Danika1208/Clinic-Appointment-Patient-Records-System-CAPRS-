package clinic_data;
import java.io.Serializable;
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    private int doctorID;
    private String name;
    private String specialization;
    private String contactNumber;

    public Doctor(int doctorID, String name, String specialization , String contactNumber) {
        this.doctorID = doctorID;
        this.name = name;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
    }
    public int getDoctorID() { return doctorID; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getcontactNumber() { return contactNumber; }
    @Override
    public String toString() {
        return String.format("ID: %d | Dr. %s | Spec: %s , Contact: %s", 
                             doctorID, name, specialization, contactNumber);
    }
}
