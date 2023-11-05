package healthcaresystem.model;

public class VisitRecord {
    private int visitID;
    private int patientID;
    private String patientName;
    private int physicianID;
    private String physicianName;
    private String date;
    private String time;
    private String location;
    private String notes;

    /**
     * @param visitID
     * @param patientID
     * @param patientName
     * @param physicianID
     * @param physicianName
     * @param date
     * @param time
     * @param location
     * @param notes
     */
    public VisitRecord(int visitID, int patientID, String patientName, int physicianID, String physicianName,
                       String date, String time, String location, String notes) {
        this.visitID = visitID;
        this.patientID = patientID;
        this.patientName = patientName;
        this.physicianID = physicianID;
        this.physicianName = physicianName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.notes = notes;
    }

    /**
     * @return the visitID
     */
    public int getVisitID() {
        return visitID;
    }

    /**
     * @return the patientID
     */
    public int getPatientID() {
        return patientID;
    }

    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @return the physicianID
     */
    public int getPhysicianID() {
        return physicianID;
    }

    /**
     * @return the physicianName
     */
    public String getPhysicianName() {
        return physicianName;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }
}
