package com.sasd13.proadmin.controller.settings;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeacherUpdateStrategy extends RequestorStrategy<TeacherUpdateWrapper, Void> {

    private SettingsController controller;
    private ITeacherService service;

    public TeacherUpdateStrategy(SettingsController controller, ITeacherService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Void doInBackgroung(TeacherUpdateWrapper[] in) {
        service.update(in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Void out) {
        super.onPostExecute(out);

        //controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onCancelled(Void out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
