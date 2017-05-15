package com.sasd13.proadmin.android.util;

import com.sasd13.proadmin.android.bean.UserPreference;

import java.util.List;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class UserStorage {

    private List<UserPreference> userPreferences;

    public List<UserPreference> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<UserPreference> userPreferences) {
        this.userPreferences = userPreferences;
    }
}
