package com.healthcaresystem.model;

import java.util.ArrayList;

public class Nurse extends User {
    private ArrayList<Integer> physicianIds;

    public Nurse(int uid, String name, String type, ArrayList<Integer> physicianIds) {
        super(uid, name, type);
        this.physicianIds = physicianIds;
    }

    public ArrayList<Integer> getPhysicianIds(){
        return physicianIds;
    }
}
