package com.sasd13.proadmin.gui.widget.dialog.progress;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Samir on 15/12/2015.
 */
public class LoadDialog extends ProgressDialog {

    public LoadDialog(Context context) {
        super(context);

        setProgressStyle(ProgressDialog.STYLE_SPINNER);
        setCancelable(true);
        setIndeterminate(true);
    }

    public LoadDialog(Context context, CharSequence title, CharSequence message) {
        this(context);

        setTitle(title);
        setMessage(message);
    }
}
