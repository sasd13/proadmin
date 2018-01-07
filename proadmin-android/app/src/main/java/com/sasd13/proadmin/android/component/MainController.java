package com.sasd13.proadmin.android.component;

import android.support.v4.app.Fragment;

import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.component.Controller;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class MainController extends Controller {

    protected MainController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected MainActivity getActivity() {
        return (MainActivity) super.getActivity();
    }

    protected void startFragment(Fragment fragment) {
        getActivity().startFragment(fragment);
    }
}
