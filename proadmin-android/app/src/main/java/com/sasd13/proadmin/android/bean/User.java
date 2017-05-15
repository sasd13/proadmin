package com.sasd13.proadmin.android.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private long id;
    private String userID, intermediary, email;
    private int status;
    private String[] roles;
    private List<UserPreference> userPreferences;

    public User() {
        userPreferences = new ArrayList<>();
    }

    protected User(Parcel in) {
        id = in.readLong();
        userID = in.readString();
        intermediary = in.readString();
        email = in.readString();
        status = in.readInt();
        roles = in.createStringArray();
        userPreferences = in.createTypedArrayList(UserPreference.CREATOR);
    }

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

    public List<UserPreference> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<UserPreference> userPreferences) {
        this.userPreferences = userPreferences;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(userID);
        parcel.writeString(intermediary);
        parcel.writeString(email);
        parcel.writeInt(status);
        parcel.writeStringArray(roles);
        parcel.writeTypedList(userPreferences);
    }
}
