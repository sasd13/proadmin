package com.sasd13.proadmin.controller.settings;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.service.TeacherService;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class TeacherServiceCallback implements TeacherService.Callback {

    private SettingsController controller;
    private Context context;

    public TeacherServiceCallback(SettingsController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onPreExecute() {
    }

    @Override
    public void onReaded(List<Teacher> teachers) {
        controller.onReadTeacher(teachers.get(0));
    }

    @Override
    public void onCreated() {
        //Do nothing
    }

    @Override
    public void onUpdated() {
        controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onDeleted() {
        //Do nothing
    }

    @Override
    public void onErrors(List<String> errors) {
        controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
