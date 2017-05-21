package com.sasd13.proadmin.android.bean.user;

import android.os.Parcel;
import android.os.Parcelable;

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
    private UserPreferences userPreferences;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readLong();
        userID = in.readString();
        intermediary = in.readString();
        email = in.readString();
        status = in.readInt();
        roles = in.createStringArray();
        userPreferences = in.readParcelable(UserPreferences.class.getClassLoader());
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

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
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
        parcel.writeParcelable(userPreferences, i);
    }
}
