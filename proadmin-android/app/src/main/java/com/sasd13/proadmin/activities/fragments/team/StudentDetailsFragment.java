package com.sasd13.proadmin.activities.fragments.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.TeamsActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.form.RunningForm;
import com.sasd13.proadmin.gui.form.StudentForm;
import com.sasd13.proadmin.service.member.StudentReadService;
import com.sasd13.proadmin.service.project.ProjectReadService;
import com.sasd13.proadmin.service.running.RunningManageService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningBuilder;

import java.util.List;

public class StudentDetailsFragment extends Fragment implements IManageServiceCaller<StudentTeam> {

    private TeamsActivity parentActivity;

    private StudentForm studentForm;

    private Student student;

    private StudentManage studentReadService;

    public static StudentDetailsFragment newInstance(Student student) {
        StudentDetailsFragment fragment = new StudentDetailsFragment();
        fragment.student = student;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (TeamsActivity) getActivity();
        studentReadService = new ProjectReadService(this);
        runningManageService = new RunningManageService(this);
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
        buildFormRunning(view);
    }

    private void buildFormRunning(View view) {
        studentForm = new RunningForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, studentForm.getHolder());
    }

    private void readProjectsFromWS() {
        studentReadService.readProjects();
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_running));
        readProjectsFromWS();
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
                createRunning();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createRunning() {
        runningManageService.createRunning(studentForm, SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
    }

    @Override
    public void onLoad() {
        waitDialog = new WaitDialog(getContext());
        waitDialog.show();
    }

    private void bindFormRunning(List<Project> projects) {
        if (project != null) {
            studentForm.bind(new DefaultRunningBuilder(project).build(), projects);
        } else {
            studentForm.bind(new DefaultRunningBuilder().build(), projects);
        }
    }

    @Override
    public void onCreateSucceeded(StudentTeam studentTeam) {
        if (waitDialog != null) {
            waitDialog.dismiss();
        }

        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunnings();
    }

    @Override
    public void onUpdateSucceeded(StudentTeam studentTeam) {
    }

    @Override
    public void onDeleteSucceeded() {
    }

    @Override
    public void onError(@StringRes int message) {
        if (waitDialog != null) {
            waitDialog.dismiss();
        }

        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}