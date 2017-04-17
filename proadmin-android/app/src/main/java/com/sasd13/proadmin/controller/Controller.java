package com.sasd13.proadmin.controller;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.sasd13.javaex.net.EnumHttpStatus;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.EnumError;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.view.IController;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class Controller implements IController {

    private View contentView;

    protected Controller(Activity activity) {
        contentView = activity.findViewById(android.R.id.content);
    }

    @Override
    public void display(@StringRes int resID) {
        Snackbar.make(contentView, resID, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void display(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    public void onCancelled() {
        display(R.string.message_cancelled);
    }

    public void onFail(int httpStatus, List<String> responseErrors) {
        if (responseErrors != null && !responseErrors.isEmpty()) {
            EnumError error = EnumError.find(Integer.valueOf(responseErrors.get(0)));

            display(EnumErrorRes.find(error).getResID());
        } else {
            EnumHttpStatus status = EnumHttpStatus.find(httpStatus);

            if (status != null) {
                display(status.getDesc());
            } else {
                display(EnumErrorRes.UNKNOWN.getResID());
            }
        }
    }
}
