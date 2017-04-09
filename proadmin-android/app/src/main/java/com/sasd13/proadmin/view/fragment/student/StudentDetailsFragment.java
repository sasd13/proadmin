package com.sasd13.proadmin.view.fragment.student;

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
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.scope.StudentScope;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.view.gui.form.StudentForm;

import java.util.Observable;
import java.util.Observer;

public class StudentDetailsFragment extends Fragment implements Observer {

    private IStudentController controller;
    private StudentScope scope;
    private StudentForm studentForm;
    private Menu menu;

    public static StudentDetailsFragment newInstance() {
        return new StudentDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IStudentController) ((MainActivity) getActivity()).lookup(IStudentController.class);
        scope = (StudentScope) controller.getScope();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormStudent(view);
        bindFormWithStudent(scope.getStudentTeam().getStudent());
    }

    private void buildFormStudent(View view) {
        studentForm = new StudentForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, studentForm.getHolder());
    }

    private void bindFormWithStudent(Student student) {
        studentForm.bindStudent(student);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                updateStudent();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateStudent() {
        try {
            controller.actionUpdateStudent(getStudentUpdateWrapperFromForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private StudentUpdateWrapper getStudentUpdateWrapperFromForm() throws FormException {
        StudentUpdateWrapper studentUpdateWrapper = new StudentUpdateWrapper();
        Student student = scope.getStudentTeam().getStudent();

        studentUpdateWrapper.setNumber(student.getNumber());
        studentUpdateWrapper.setWrapped(student);
        student.setNumber(studentForm.getNumber());
        student.setFirstName(studentForm.getFirstName());
        student.setLastName(studentForm.getLastName());
        student.setEmail(studentForm.getEmail());

        return studentUpdateWrapper;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_student));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        scope = (StudentScope) observable;

        bindFormWithStudent(scope.getStudentTeam().getStudent());
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