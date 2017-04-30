package com.sasd13.proadmin.android.controller.student;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.StudentUpdate;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.scope.StudentScope;
import com.sasd13.proadmin.android.service.v1.IStudentService;
import com.sasd13.proadmin.android.service.v1.IStudentTeamService;
import com.sasd13.proadmin.android.util.builder.member.NewStudentBuilder;
import com.sasd13.proadmin.android.view.IBrowsable;
import com.sasd13.proadmin.android.view.IStudentController;
import com.sasd13.proadmin.android.view.ITeamController;
import com.sasd13.proadmin.android.view.fragment.student.StudentDetailsFragment;
import com.sasd13.proadmin.android.view.fragment.student.StudentNewFragment;

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
        scope.setStudentTeam(getStudentTeam(team));
        startFragment(StudentNewFragment.newInstance());
    }

    private StudentTeam getStudentTeam(Team team) {
        StudentTeam studentTeam = new StudentTeam();

        studentTeam.setStudent(new NewStudentBuilder().build());
        studentTeam.setTeam(team);

        return studentTeam;
    }

    @Override
    public void actionCreateStudent(StudentTeam studentTeam) {
        if (studentCreateTask == null) {
            studentCreateTask = new StudentCreateTask(this, studentService, studentTeamService);
        }

        new Requestor(studentCreateTask).execute(studentTeam);
    }

    void onCreateStudent() {
        display(R.string.message_saved);
        ((IBrowsable) mainActivity.lookup(ITeamController.class)).browse();
    }

    @Override
    public void actionShowStudent(StudentTeam studentTeam) {
        scope.setStudentTeam(studentTeam);
        startFragment(StudentDetailsFragment.newInstance());
    }

    @Override
    public void actionUpdateStudent(StudentUpdate studentUpdate) {
        if (studentUpdateTask == null) {
            studentUpdateTask = new StudentUpdateTask(this, studentService);
        }

        new Requestor(studentUpdateTask).execute(studentUpdate);
    }

    void onUpdateStudent() {
        display(R.string.message_updated);
    }
}