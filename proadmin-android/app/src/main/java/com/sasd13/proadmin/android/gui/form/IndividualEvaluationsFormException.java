package com.sasd13.proadmin.android.gui.form;

import android.content.Context;
import android.support.annotation.StringRes;

import com.sasd13.androidex.gui.form.FormException;

/**
 * Created by ssaidali2 on 13/11/2016.
 */

public class IndividualEvaluationsFormException extends FormException {

    private String additionalInfo;

    public IndividualEvaluationsFormException(Context context, @StringRes int resID) {
        super(context, resID);
    }

    public IndividualEvaluationsFormException(Context context, @StringRes int resID, String additionalInfo) {
        super(context, resID);

        this.additionalInfo = additionalInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
