package com.healthcaresystem.model;

import java.util.ArrayList;

public class Nurse extends User {
    private ArrayList<Integer> physicianIds;

    public Nurse(int uid, String username, String name, String type, ArrayList<Integer> physicianIds) {
        super(uid, username, name, type);
        this.physicianIds = physicianIds;
    }

    public ArrayList<Integer> getPhysicianIds(){
        return physicianIds;
    }
}
