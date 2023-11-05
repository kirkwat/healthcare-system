package healthcaresystem.model;

import java.util.ArrayList;

public class Nurse extends User {
    private ArrayList<Integer> physicianIDs;

    public Nurse(int uid, String name, String type, ArrayList<Integer> physicianIDs) {
        super(uid, name, type);
        this.physicianIDs = physicianIDs;
    }

    public ArrayList<Integer> getPhysicianIDs(){
        return physicianIDs;
    }
}
