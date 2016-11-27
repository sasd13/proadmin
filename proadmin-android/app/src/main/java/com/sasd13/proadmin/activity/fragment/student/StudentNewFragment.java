package com.sasd13.proadmin.activity.fragment.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.sasd13.proadmin.activity.TeamsActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.gui.form.StudentForm;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.builder.member.StudentFromFormBuilder;
import com.sasd13.proadmin.ws.service.StudentsService;

import java.util.List;

public class StudentNewFragment extends Fragment implements StudentsService.ManageCaller {

    private TeamsActivity parentActivity;

    private StudentForm studentForm;

    private Team team;

    private StudentsService service;

    public static StudentNewFragment newInstance(Team team) {
        StudentNewFragment fragment = new StudentNewFragment();
        fragment.team = team;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (TeamsActivity) getActivity();
        service = new StudentsService(this);
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
    }

    private void buildFormStudent(View view) {
        studentForm = new StudentForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, studentForm.getHolder());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.menu_edit_action_delete).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                createStudent();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createStudent() {
        try {
            service.create(getStudentFromForm(), team);
        } catch (FormException e) {
            displayError(e.getMessage());
        }
    }

    private Student getStudentFromForm() throws FormException {
        return new StudentFromFormBuilder(studentForm).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setSubtitle(getResources().getString(R.string.title_student));
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onCreated() {
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.showTeam(team);
    }

    @Override
    public void onUpdated() {
    }

    @Override
    public void onDeleted() {
    }

    @Override
    public void onError(List<String> errors) {
        displayError(WebServiceUtils.handleErrors(getContext(), errors));
    }

    public void displayError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}