package com.sasd13.proadmin.android;

import android.app.Activity;

import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.proadmin.android.factory.ControllerFactory;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class Configuration {

    public static Resolver init(Activity activity) {
        Resolver resolver = new Resolver();

        resolver.register(ControllerFactory.class, new ControllerFactory(resolver));
        resolver.register(SessionStorage.class, new SessionStorage(activity));

        return resolver;
    }
}
