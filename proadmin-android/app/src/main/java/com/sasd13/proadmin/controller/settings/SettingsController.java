package com.sasd13.proadmin.controller.settings;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.fragment.ISettingsController;
import com.sasd13.proadmin.fragment.settings.SettingsFragment;
import com.sasd13.proadmin.service.TeacherService;
import com.sasd13.proadmin.util.wrapper.TeacherWrapper;

public class SettingsController extends Controller implements ISettingsController {

    private TeacherService teacherService;

    public SettingsController(MainActivity mainActivity) {
        super(mainActivity);

        teacherService = new TeacherService(new TeacherServiceCallback(this, mainActivity));
    }

    @Override
    public void entry() {
        mainActivity.clearHistory();
        showTeacher();
    }

    public void showTeacher() {
        startProxyFragment();
        teacherService.readByNumber(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadTeacher(Teacher teacher) {
        if (isProxyFragmentNotDetached()) {
            startFragment(SettingsFragment.newInstance(new TeacherWrapper(teacher)));
        }
    }

    @Override
    public void updateTeacher(Teacher teacher, Teacher teacherToUpdate) {
        teacherService.updateTeacher(teacher, teacherToUpdate);
    }
}