package com.sasd13.proadmin.controller.student;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.IStudentController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.StudentScope;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.util.builder.member.DefaultStudentBuilder;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.view.fragment.student.StudentDetailsFragment;
import com.sasd13.proadmin.view.fragment.student.StudentNewFragment;

public class StudentController extends MainController implements IStudentController {

    private StudentScope scope;
    private IStudentService studentService;
    private StudentCreateStrategy studentCreateStrategy;
    private StudentUpdateStrategy studentUpdateStrategy;

    public StudentController(MainActivity mainActivity, IStudentService studentService) {
        super(mainActivity);

        scope = new StudentScope();
        this.studentService = studentService;
    }

    @Override
    public Object getScope() {
        return scope;
    }

    @Override
    public void actionNewStudent(Team team) {
        StudentTeam studentTeam = new StudentTeam();

        studentTeam.setStudent(new DefaultStudentBuilder().build());
        studentTeam.setTeam(team);

        startFragment(StudentNewFragment.newInstance());
    }

    @Override
    public void actionCreateStudent(StudentTeam studentTeam) {
        if (studentCreateStrategy == null) {
            studentCreateStrategy = new StudentCreateStrategy(this, studentService);
        }

        new Requestor(studentCreateStrategy).execute(studentTeam);
    }

    void onCreateStudent() {
        display(R.string.message_saved);
        //TODO entry();
    }

    @Override
    public void actionShowStudent(StudentTeam studentTeam) {
        scope.setStudentTeam(studentTeam);

        startFragment(StudentDetailsFragment.newInstance());
    }

    @Override
    public void actionUpdateStudent(Student student, Student studentToUpdate) {
        if (studentUpdateStrategy == null) {
            studentUpdateStrategy = new StudentUpdateStrategy(this, studentService);
        }

        new Requestor(studentUpdateStrategy).execute(getStudentUpdateWrapper(student, studentToUpdate));
    }

    private StudentUpdateWrapper getStudentUpdateWrapper(Student student, Student studentToUpdate) {
        StudentUpdateWrapper updateWrapper = new StudentUpdateWrapper();

        updateWrapper.setWrapped(student);
        updateWrapper.setNumber(studentToUpdate.getNumber());

        return updateWrapper;
    }

    void onUpdateStudent() {
        display(R.string.message_updated);
    }
}