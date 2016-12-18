package com.sasd13.proadmin.fragment.student;

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
import com.sasd13.proadmin.fragment.IStudentController;
import com.sasd13.proadmin.gui.form.StudentForm;
import com.sasd13.proadmin.util.wrapper.StudentWrapper;

public class StudentDetailsFragment extends Fragment {

    private IStudentController controller;
    private Student student;
    private StudentForm studentForm;

    public static StudentDetailsFragment newInstance(StudentWrapper studentWrapper) {
        StudentDetailsFragment fragment = new StudentDetailsFragment();
        fragment.student = studentWrapper.getStudent();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        controller = (IStudentController) ((MainActivity) getActivity()).lookup(IStudentController.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormStudent(view);
        bindFormWithStudent();
    }

    private void buildFormStudent(View view) {
        studentForm = new StudentForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, studentForm.getHolder());
    }

    private void bindFormWithStudent() {
        studentForm.bindStudent(student);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

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
            controller.updateStudent(getStudentFromForm(), student);
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    private Student getStudentFromForm() throws FormException {
        Student studentFromForm = new Student();

        studentFromForm.setNumber(studentForm.getNumber());
        studentFromForm.setFirstName(studentForm.getFirstName());
        studentFromForm.setLastName(studentForm.getLastName());
        studentFromForm.setEmail(studentForm.getEmail());

        return studentFromForm;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_student));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }
}