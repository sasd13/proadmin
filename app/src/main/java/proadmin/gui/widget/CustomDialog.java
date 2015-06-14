package proadmin.gui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Samir on 09/06/2015.
 */
public class CustomDialog {

    public static void showOkDialog(Context context, String title, String message) {
        showDialog(context, title, message, CustomDialogBuilder.TYPE_ONEBUTTON_OK, null);
    }

    public static void showOkDialog(Context context, int titleId, int messageId) {
        showDialog(context, titleId, messageId, CustomDialogBuilder.TYPE_ONEBUTTON_OK, null);
    }

    public static void showDialog(Context context, String title, String message, int dialogType, DialogInterface.OnClickListener positiveListener) {
        CustomDialogBuilder builder;
        switch (dialogType) {
            case CustomDialogBuilder.TYPE_ONEBUTTON_OK: default:
                builder = new CustomDialogBuilder(context, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
                builder.setTitle(title)
                        .setMessage(message)
                        .setNeutralButton(null);
                break;
            case CustomDialogBuilder.TYPE_TWOBUTTON_OKCANCEL: case CustomDialogBuilder.TYPE_TWOBUTTON_YESNO:
                builder = new CustomDialogBuilder(context, dialogType);
                builder.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(positiveListener);
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showDialog(Context context, int titleId, int messageId, int dialogType, DialogInterface.OnClickListener positiveListener) {
        CustomDialogBuilder builder;
        switch (dialogType) {
            case CustomDialogBuilder.TYPE_ONEBUTTON_OK: default:
                builder = new CustomDialogBuilder(context, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
                builder.setTitle(titleId)
                        .setMessage(messageId)
                        .setNeutralButton(null);
                break;
            case CustomDialogBuilder.TYPE_TWOBUTTON_OKCANCEL: case CustomDialogBuilder.TYPE_TWOBUTTON_YESNO:
                builder = new CustomDialogBuilder(context, dialogType);
                builder.setTitle(titleId)
                        .setMessage(messageId)
                        .setPositiveButton(positiveListener);
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
