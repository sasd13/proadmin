package com.sasd13.proadmin.android.controller.setting;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.bean.update.TeacherUpdate;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.SettingScope;
import com.sasd13.proadmin.android.service.ITeacherService;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.view.ISettingController;
import com.sasd13.proadmin.android.view.fragment.setting.SettingFragment;
import com.sasd13.proadmin.util.EnumParameter;

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
    public void actionUpdateTeacher(TeacherUpdate teacherUpdate) {
        if (teacherUpdateTask == null) {
            teacherUpdateTask = new TeacherUpdateTask(this, teacherService);
        }

        new Requestor(teacherUpdateTask).execute(teacherUpdate);
    }

    void onUpdateTeacher() {
        display(R.string.message_updated);
    }
}