package com.sasd13.proadmin.controller.settings;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.view.ISettingsController;
import com.sasd13.proadmin.view.settings.SettingsFragment;
import com.sasd13.proadmin.ws.service.TeacherService;

public class SettingsController extends Controller implements ISettingsController {

    private SettingsFragment settingsFragment;

    private TeacherService teacherService;

    public SettingsController(MainActivity mainActivity) {
        super(mainActivity);

        teacherService = new TeacherService(new TeacherServiceCaller(this, mainActivity));
    }

    @Override
    public void entry() {
        settingsFragment = SettingsFragment.newInstance(this);

        startFragment(settingsFragment);
        teacherService.readTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadTeacher(Teacher teacher) {
        if (!settingsFragment.isDetached()) {
            settingsFragment.setTeacher(teacher);
        }
    }

    @Override
    public void updateTeacher(Teacher teacher, Teacher teacherToUpdate) {
        teacherService.updateTeacher(teacher, teacherToUpdate);
    }
}