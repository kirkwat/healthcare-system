package com.healthcaresystem.model;

public class User {
    private int uid;
    private String username;
    private String name;
    private String type; //'physician', 'nurse', 'receptionist'

    /**
     * @param uid
     * @param username
     * @param name
     * @param type
     */
    public User(int uid, String username, String name, String type) {
        this.uid = uid;
        this.username = username;
        this.name = name;
        this.type = type;
    }

    /**
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @return the name
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
}
