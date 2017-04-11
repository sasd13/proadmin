package com.sasd13.proadmin.view.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.scope.SettingScope;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.view.ISettingController;
import com.sasd13.proadmin.view.gui.form.TeacherForm;

import java.util.Observable;
import java.util.Observer;

public class SettingFragment extends Fragment implements Observer {

    private ISettingController controller;
    private SettingScope scope;
    private TeacherForm teacherForm;
    private Menu menu;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ISettingController) ((MainActivity) getActivity()).lookup(ISettingController.class);
        scope = (SettingScope) controller.getScope();

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        buildFormTeacher(view);
        bindFormWithTeacher(scope.getTeacher());
    }

    private void buildFormTeacher(View view) {
        teacherForm = new TeacherForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, teacherForm.getHolder());
    }

    private void bindFormWithTeacher(Teacher teacher) {
        teacherForm.bindTeacher(teacher);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

        inflater.inflate(R.menu.menu_settings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings_action_save:
                updateTeacher();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateTeacher() {
        try {
            controller.actionUpdateTeacher(getTeacherUpdateWrapperFromForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private TeacherUpdateWrapper getTeacherUpdateWrapperFromForm() throws FormException {
        TeacherUpdateWrapper teacherUpdateWrapper = new TeacherUpdateWrapper();
        Teacher teacher = scope.getTeacher();

        teacherUpdateWrapper.setNumber(teacher.getNumber());
        teacherUpdateWrapper.setWrapped(teacher);
        teacher.setNumber(teacherForm.getNumber());
        teacher.setFirstName(teacherForm.getFirstName());
        teacher.setLastName(teacherForm.getLastName());
        teacher.setEmail(teacherForm.getEmail());

        return teacherUpdateWrapper;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_settings));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithTeacher(scope.getTeacher());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);

        if (menu != null) {
            menu.setGroupVisible(R.id.menu_edit_group, false);
        }
    }
}