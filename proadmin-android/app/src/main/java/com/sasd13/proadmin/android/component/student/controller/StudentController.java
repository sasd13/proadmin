package com.sasd13.proadmin.android.component.student.controller;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.component.MainController;
import com.sasd13.proadmin.android.component.student.task.StudentCreateTask;
import com.sasd13.proadmin.android.component.student.task.StudentUpdateTask;
import com.sasd13.proadmin.android.component.student.view.IStudentController;
import com.sasd13.proadmin.android.component.student.view.StudentDetailsFragment;
import com.sasd13.proadmin.android.component.student.view.StudentNewFragment;
import com.sasd13.proadmin.android.component.team.view.ITeamController;
import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.scope.StudentScope;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.util.browser.IBrowsable;
import com.sasd13.proadmin.android.util.builder.NewStudentBuilder;
import com.sasd13.proadmin.android.util.builder.NewStudentTeamBuilder;

public class StudentController extends MainController implements IStudentController {

    private StudentScope scope;
    private IStudentService studentService;
    private IStudentTeamService studentTeamService;
    private StudentCreateTask studentCreateTask;
    private StudentUpdateTask studentUpdateTask;

    public StudentController(MainActivity mainActivity, IStudentService studentService, IStudentTeamService studentTeamService) {
        super(mainActivity);

        scope = new StudentScope();
        this.studentService = studentService;
        this.studentTeamService = studentTeamService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void actionNewStudent(Team team) {
        scope.setStudentTeam(new NewStudentTeamBuilder(new NewStudentBuilder().build(), team).build());
        startFragment(StudentNewFragment.newInstance());
    }

    @Override
    public void actionCreateStudent(StudentTeam studentTeam) {
        if (studentCreateTask == null) {
            studentCreateTask = new StudentCreateTask(this, studentService, studentTeamService);
        }

        new Requestor(studentCreateTask).execute(studentTeam);
    }

    public void onCreateStudent() {
        display(R.string.message_saved);
        ((IBrowsable) getActivity().lookup(ITeamController.class)).browse();
    }

    @Override
    public void actionShowStudent(StudentTeam studentTeam) {
        scope.setStudentTeam(studentTeam);
        startFragment(StudentDetailsFragment.newInstance());
    }

    @Override
    public void actionUpdateStudent(Student student) {
        if (studentUpdateTask == null) {
            studentUpdateTask = new StudentUpdateTask(this, studentService);
        }

        new Requestor(studentUpdateTask).execute(student);
    }

    public void onUpdateStudent() {
        display(R.string.message_saved);
    }
}