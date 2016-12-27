package com.sasd13.proadmin.fragment.settings;

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
import com.sasd13.proadmin.fragment.ISettingsController;
import com.sasd13.proadmin.gui.form.TeacherForm;
import com.sasd13.proadmin.util.wrapper.TeacherWrapper;

public class SettingsFragment extends Fragment {

    private ISettingsController controller;
    private Teacher teacher;
    private TeacherForm teacherForm;

    public static SettingsFragment newInstance(TeacherWrapper teacherWrapper) {
        SettingsFragment fragment = new SettingsFragment();
        fragment.teacher = teacherWrapper.getTeacher();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        controller = (ISettingsController) ((MainActivity) getActivity()).lookup(ISettingsController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        buildFormSettings(view);
        bindFormWithTeacher();
    }

    private void buildFormSettings(View view) {
        teacherForm = new TeacherForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, teacherForm.getHolder());
    }

    private void bindFormWithTeacher() {
        teacherForm.bindTeacher(teacher);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

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
            controller.updateTeacher(getTeacherFromForm(), teacher);
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    private Teacher getTeacherFromForm() throws FormException {
        Teacher teacherFromForm = new Teacher();

        teacherFromForm.setNumber(teacherForm.getNumber());
        teacherFromForm.setFirstName(teacherForm.getFirstName());
        teacherFromForm.setLastName(teacherForm.getLastName());
        teacherFromForm.setEmail(teacherForm.getEmail());

        return teacherFromForm;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_settings));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }
}