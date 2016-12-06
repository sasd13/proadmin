package com.sasd13.proadmin.controller.report;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.service.StudentService;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class StudentServiceCaller implements StudentService.Caller {

    private ReportController controller;
    private Context context;

    public StudentServiceCaller(ReportController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<StudentTeam> studentTeams) {
        controller.onReadStudentTeams(studentTeams);
    }

    @Override
    public void onCreated() {
        controller.displayMessage(context.getString(R.string.message_saved));
        controller.backPress();
    }

    @Override
    public void onUpdated() {
        controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onDeleted() {
        controller.displayMessage(context.getString(R.string.message_deleted));
        controller.backPress();
    }

    @Override
    public void onErrors(List<String> errors) {
        controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}