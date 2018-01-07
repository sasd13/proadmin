package com.sasd13.proadmin.android.model;

public class Running {

    private long id;
    private Project project;
    private Teacher teacher;
    private int year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Running other = (Running) obj;

        if (year != other.year)
            return false;

        if (project == null && other.project != null)
            return false;
        else if (!project.equals(other.project))
            return false;

        if (teacher == null && other.teacher != null)
            return false;
        else if (!teacher.equals(other.teacher))
            return false;

        return true;
    }
}
