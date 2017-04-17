package com.sasd13.proadmin.controller.setting;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.SettingScope;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.view.ISettingController;
import com.sasd13.proadmin.view.fragment.setting.SettingFragment;

public class SettingController extends MainController implements ISettingController {

    private SettingScope scope;
    private ITeacherService teacherService;
    private TeacherReadTask teacherReadTask;
    private TeacherUpdateTask teacherUpdateTask;

    public SettingController(MainActivity mainActivity, ITeacherService teacherService) {
        super(mainActivity);

        scope = new SettingScope();
        this.teacherService = teacherService;
    }

    @Override
    public Object getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        startFragment(SettingFragment.newInstance());
        readTeacher();
    }

    private void readTeacher() {
        if (teacherReadTask == null) {
            teacherReadTask = new TeacherReadTask(this, teacherService);
        }

        teacherReadTask.clearParameters();
        teacherReadTask.putParameter(EnumParameter.INTERMEDIARY.getName(), new String[]{SessionHelper.getExtraIntermediary(mainActivity)});
        new Requestor(teacherReadTask).execute();
    }

    void onReadTeacher(Teacher teacher) {
        scope.setTeacher(teacher);
    }

    @Override
    public void actionUpdateTeacher(TeacherUpdateWrapper teacherUpdateWrapper) {
        if (teacherUpdateTask == null) {
            teacherUpdateTask = new TeacherUpdateTask(this, teacherService);
        }

        new Requestor(teacherUpdateTask).execute(teacherUpdateWrapper);
    }

    void onUpdateTeacher() {
        display(R.string.message_updated);
    }
}