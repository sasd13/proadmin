package com.sasd13.proadmin.controller.setting;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.controller.ISettingController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.SettingScope;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.view.fragment.setting.SettingFragment;

public class SettingController extends MainController implements ISettingController {

    private SettingScope scope;
    private ITeacherService teacherService;
    private TeacherReadStrategy teacherReadStrategy;
    private TeacherUpdateStrategy teacherUpdateStrategy;

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
    public void entry() {
        mainActivity.clearHistory();
        showTeacher();
    }

    private void showTeacher() {
        SettingFragment fragment = SettingFragment.newInstance();

        scope.addObserver(fragment);
        startFragment(fragment);
        readTeacher();
    }

    private void readTeacher() {
        if (teacherReadStrategy == null) {
            teacherReadStrategy = new TeacherReadStrategy(this, teacherService);
        }

        teacherReadStrategy.clearParameters();
        teacherReadStrategy.putParameter(EnumParameter.NUMBER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(teacherReadStrategy).execute();
    }

    void onReadTeacher(Teacher teacher) {
        scope.setTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher, Teacher teacherToUpdate) {
        if (teacherUpdateStrategy == null) {
            teacherUpdateStrategy = new TeacherUpdateStrategy(this, teacherService);
        }

        new Requestor(teacherUpdateStrategy).execute(getTeacherUpdateWrapper(teacher, teacherToUpdate));
    }

    private TeacherUpdateWrapper getTeacherUpdateWrapper(Teacher teacher, Teacher teacherToUpdate) {
        TeacherUpdateWrapper updateWrapper = new TeacherUpdateWrapper();

        updateWrapper.setWrapped(teacher);
        updateWrapper.setNumber(teacherToUpdate.getNumber());

        return updateWrapper;
    }
}