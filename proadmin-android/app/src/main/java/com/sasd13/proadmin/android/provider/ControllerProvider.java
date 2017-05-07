package com.sasd13.proadmin.android.provider;

import android.app.Activity;

import com.sasd13.proadmin.android.view.IController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ControllerProvider {

    private Map<Class<? extends IController>, IController> container;

    public ControllerProvider() {
        container = new HashMap<>();
    }

    public IController provide(Class<? extends IController> mClass, Activity activity) {
        IController controller = container.get(mClass);

        if (controller == null) {
            controller = ControllerFactory.make(mClass, activity);

            container.put(mClass, controller);
        }

        return controller;
    }
}
