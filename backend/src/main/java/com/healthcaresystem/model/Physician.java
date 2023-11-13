package com.healthcaresystem.model;

import java.util.ArrayList;

public class Physician extends User {
    private ArrayList<Integer> patientIds;

    public Physician(int uid, String username, String name, String type, ArrayList<Integer> patientIds) {
        super(uid, username, name, type);
        this.patientIds = patientIds;
    }

    public ArrayList<Integer> getPatientIds() {
        return patientIds;
    }

}
