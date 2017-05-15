package com.sasd13.proadmin.android.util;

import com.sasd13.proadmin.android.bean.User;
import com.sasd13.proadmin.android.bean.UserPreference;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class UserStorage {

    private User user;

    public UserStorage(User user) {
        this.user = user;
    }

    public UserPreference getUserPreference(String category, String name) {
        for (UserPreference userPreference : user.getUserPreferences()) {
            if (userPreference.getCategory().equalsIgnoreCase(category) && userPreference.getName().equalsIgnoreCase(name)) {
                return userPreference;
            }
        }

        return null;
    }
}
