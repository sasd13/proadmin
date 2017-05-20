package com.sasd13.proadmin.android.bean.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.util.EnumPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 20/05/2017.
 */

public class UserPreferences implements Parcelable {

    public static final Creator<UserPreferences> CREATOR = new Creator<UserPreferences>() {
        @Override
        public UserPreferences createFromParcel(Parcel in) {
            return new UserPreferences(in);
        }

        @Override
        public UserPreferences[] newArray(int size) {
            return new UserPreferences[size];
        }
    };

    private List<UserPreference> preferences;

    public UserPreferences() {
        preferences = new ArrayList<>();
    }

    public UserPreferences(List<UserPreference> preferences) {
        this.preferences = preferences;
    }

    protected UserPreferences(Parcel in) {
        preferences = in.createTypedArrayList(UserPreference.CREATOR);
    }

    public List<UserPreference> getPreferences() {
        return preferences;
    }

    public String find(EnumPreference criteria) {
        for (UserPreference preference : preferences) {
            if (preference.getCategory().equalsIgnoreCase(criteria.getCategory()) && preference.getName().equalsIgnoreCase(criteria.getName())) {
                return preference.getValue();
            }
        }

        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(preferences);
    }
}
