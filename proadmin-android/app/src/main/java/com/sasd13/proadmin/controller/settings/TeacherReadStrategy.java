package com.sasd13.proadmin.controller.settings;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.service.ITeacherService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeacherReadStrategy extends ReadRequestorStrategy {

    private SettingsController controller;
    private ITeacherService service;

    public TeacherReadStrategy(SettingsController controller, ITeacherService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.onReadTeacher(((List<Teacher>) out).get(0));
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
