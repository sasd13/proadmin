package com.sasd13.proadmin.ws.task;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.R;

/**
 * Created by Samir on 24/12/2015.
 */
public class WaitDialogMaker {

    private static WaitDialog waitDialog;
    private static Context mContext;
    private static AsyncTask mAsyncTask;
    private static TaskPlanner mTaskPlanner;

    public static WaitDialog make(Context context, AsyncTask asyncTask, TaskPlanner taskPlanner) {
        mContext = context;
        mAsyncTask = asyncTask;
        mTaskPlanner = taskPlanner;
        waitDialog = new WaitDialog(context, context.getResources().getString(R.string.dialog_title_request));

        waitDialog.setCancelable(true);
        waitDialog.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                context.getResources().getString(R.string.button_cancel),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mAsyncTask.cancel(true);
                        mTaskPlanner.stop();
                        waitDialog.dismiss();
                        Toast.makeText(mContext, R.string.dialog_message_error_request_canceled, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return waitDialog;
    }
}
