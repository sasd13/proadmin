package com.sasd13.proadmin.android.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ssaidali2 on 08/05/2017.
 */

public class UserPreference implements Parcelable {

    public static final Creator<UserPreference> CREATOR = new Creator<UserPreference>() {
        @Override
        public UserPreference createFromParcel(Parcel in) {
            return new UserPreference(in);
        }

        @Override
        public UserPreference[] newArray(int size) {
            return new UserPreference[size];
        }
    };

    private long id;
    private String value, category, name, defaultValue;

    protected UserPreference(Parcel in) {
        id = in.readLong();
        value = in.readString();
        category = in.readString();
        name = in.readString();
        defaultValue = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(value);
        parcel.writeString(category);
        parcel.writeString(name);
        parcel.writeString(defaultValue);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
