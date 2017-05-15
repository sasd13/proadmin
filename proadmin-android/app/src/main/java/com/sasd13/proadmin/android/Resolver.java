package com.sasd13.proadmin.android;

import android.app.Activity;

import com.sasd13.proadmin.android.factory.ControllerFactory;
import com.sasd13.proadmin.android.factory.ServiceFactory;
import com.sasd13.proadmin.android.view.IController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class Resolver {

    private Map<Class, Object> container;

    public Resolver() {
        container = new HashMap<>();
    }

    public void register(Class mClass, Object object) {
        container.put(mClass, object);
    }

    public Object resolve(Class mClass) {
        return container.get(mClass);
    }

    public IController resolveController(Class mClass, Activity activity) {
        IController controller = (IController) resolve(mClass);

        if (controller == null) {
            controller = ((ControllerFactory) resolve(ControllerFactory.class)).make(mClass, activity);

            register(mClass, controller);
        }

        return controller;
    }

    public Object resolveService(Class mClass) {
        Object service = resolve(mClass);

        if (service == null) {
            service = ServiceFactory.make(mClass);

            register(mClass, service);
        }

        return service;
    }
}
