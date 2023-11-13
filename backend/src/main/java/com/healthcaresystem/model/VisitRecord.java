package com.healthcaresystem.model;

public class VisitRecord {
    private int visitId;
    private int patientId;
    private String patientName;
    private int physicianId;
    private String physicianName;
    private String date;
    private String time;
    private String location;
    private String notes;

    /**
     * @param visitId
     * @param patientId
     * @param patientName
     * @param physicianId
     * @param physicianName
     * @param date
     * @param time
     * @param location
     * @param notes
     */
    public VisitRecord(int visitId, int patientId, String patientName, int physicianId, String physicianName,
                       String date, String time, String location, String notes) {
        this.visitId = visitId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.physicianId = physicianId;
        this.physicianName = physicianName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.notes = notes;
    }

    /**
     * @return the visitId
     */
    public int getVisitId() {
        return visitId;
    }

    /**
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @return the physicianId
     */
    public int getPhysicianId() {
        return physicianId;
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
