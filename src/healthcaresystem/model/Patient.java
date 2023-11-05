package healthcaresystem.model;

public class Patient {
    private int patientID;
    private String name;

    /**
     * @param patientID
     * @param name
     */
    public Patient(int patientID, String name) {
        this.patientID = patientID;
        this.name = name;
    }

    /**
     * @return the patientID
     */
    public int getPatientID() {
        return patientID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
