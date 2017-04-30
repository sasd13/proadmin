package com.sasd13.proadmin.android.bean;

public class User {

    private long id;
    private String userID, intermediary, email;
    private int status;
    private String[] roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getIntermediary() {
        return intermediary;
    }

    public void setIntermediary(String intermediary) {
        this.intermediary = intermediary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
