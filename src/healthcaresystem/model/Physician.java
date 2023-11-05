package healthcaresystem.model;

import java.util.ArrayList;

public class Physician extends User {
    private ArrayList<Integer> patientIDs;

    public Physician(int uid, String name, String type, ArrayList<Integer> patientIDs) {
        super(uid, name, type);
        this.patientIDs = patientIDs;
    }

    public ArrayList<Integer> getPatientIDs() {
        return patientIDs;
    }

}
