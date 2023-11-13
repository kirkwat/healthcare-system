package com.healthcaresystem.model;

public class Patient {
    private int patientId;
    private String name;

    /**
     * @param patientId
     * @param name
     */
    public Patient(int patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }

    /**
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
