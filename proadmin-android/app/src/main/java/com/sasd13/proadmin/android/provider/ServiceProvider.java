package com.sasd13.proadmin.android.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceProvider {

    private static Map<Class<?>, Object> container = new HashMap<>();

    public static Object provide(Class<?> mClass) {
        Object service = container.get(mClass);

        if (service == null) {
            service = ServiceFactory.make(mClass);

            container.put(mClass, service);
        }

        return service;
    }
}
