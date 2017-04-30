package com.sasd13.proadmin.android.bean;

public class StudentTeam {

    private long id;
    private Student student;
    private Team team;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        StudentTeam other = (StudentTeam) obj;

        if (student == null && other.student != null)
            return false;
        else if (!student.equals(other.student))
            return false;

        if (team == null && other.team != null)
            return false;
        else if (!team.equals(other.team))
            return false;

        return true;
    }
}
