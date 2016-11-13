package com.sasd13.proadmin.gui.form;

import android.content.Context;
import android.support.annotation.StringRes;

import com.sasd13.androidex.gui.form.FormException;

/**
 * Created by ssaidali2 on 13/11/2016.
 */

public class IndividualEvaluationsFormException extends FormException {

    private String additionalInfo;

    public IndividualEvaluationsFormException(Context context, @StringRes int resMessage) {
        super(context, resMessage);
    }

    public IndividualEvaluationsFormException(Context context, @StringRes int resMessage, String additionalInfo) {
        super(context, resMessage);

        this.additionalInfo = additionalInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
