package com.sasd13.proadmin.android.controller.student;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.scope.StudentScope;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.util.builder.NewStudentTeamBuilder;
import com.sasd13.proadmin.android.util.builder.NewStudentBuilder;
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

    void onCreateStudent() {
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

    void onUpdateStudent() {
        display(R.string.message_updated);
    }
}