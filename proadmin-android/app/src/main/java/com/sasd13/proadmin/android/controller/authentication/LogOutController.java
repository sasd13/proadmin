package com.sasd13.proadmin.android.controller.authentication;

import android.content.DialogInterface;
import android.content.Intent;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.IUserStorageService;
import com.sasd13.proadmin.android.view.ILogOutController;

/**
 * Created by ssaidali2 on 05/12/2016.
 */
public class LogOutController extends MainController implements ILogOutController {

    private IAuthenticationService authenticationService;
    private IUserStorageService userStorageService;

    public LogOutController(MainActivity mainActivity, IAuthenticationService authenticationService, IUserStorageService userStorageService) {
        super(mainActivity);

        this.authenticationService = authenticationService;
        this.userStorageService = userStorageService;
    }

    @Override
    public Scope getScope() {
        return null;
    }

    @Override
    public void browse() {
        actionLogOut();
    }

    @Override
    public void actionLogOut() {
        logOut();
    }

    private void logOut() {
        OptionDialog.showOkCancelDialog(
                getActivity(),
                getActivity().getString(R.string.button_logout),
                getActivity().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        exit();
                    }
                }
        );
    }

    private void exit() {
        authenticationService.logOut();
        userStorageService.clear(getActivity());
        goToIdentityActivity();
    }

    private void goToIdentityActivity() {
        startActivity(new Intent(getActivity(), IdentityActivity.class));
    }
}
