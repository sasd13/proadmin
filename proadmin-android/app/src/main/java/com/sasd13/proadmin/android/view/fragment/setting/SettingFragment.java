package com.sasd13.proadmin.android.view.fragment.setting;

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
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.bean.update.TeacherUpdate;
import com.sasd13.proadmin.android.scope.SettingScope;
import com.sasd13.proadmin.android.view.ISettingController;
import com.sasd13.proadmin.android.view.gui.form.TeacherForm;

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

        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
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

        inflater.inflate(R.menu.menu_setting, menu);
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
            controller.actionUpdateTeacher(getTeacherUpdateFromForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private TeacherUpdate getTeacherUpdateFromForm() throws FormException {
        TeacherUpdate teacherUpdate = new TeacherUpdate();
        Teacher teacher = scope.getTeacher();

        teacherUpdate.setIntermediary(teacher.getIntermediary());
        teacherUpdate.setWrapped(teacher);
        teacher.setIntermediary(teacherForm.getNumber());
        teacher.setFirstName(teacherForm.getFirstName());
        teacher.setLastName(teacherForm.getLastName());
        teacher.setEmail(teacherForm.getEmail());

        return teacherUpdate;
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