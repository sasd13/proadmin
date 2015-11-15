package proadmin.bean.running;

import proadmin.bean.member.Student;

public class Team {

    private long id, runningYear;
    private String code;
    private Student[] students;

    public Team() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRunningYear() {
        return this.runningYear;
    }

    public void setRunningYear(long runningYear) {
        this.runningYear = runningYear;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Student[] getStudents() {
        return this.students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
