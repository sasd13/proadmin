package com.sasd13.proadmin.android.bean;

public class RunningTeam {

    private Running running;
    private Team team;
    private AcademicLevel academicLevel;

    public Running getRunning() {
        return running;
    }

    public void setRunning(Running running) {
        this.running = running;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public AcademicLevel getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        RunningTeam other = (RunningTeam) obj;

        if (running == null && other.running != null)
            return false;
        else if (!running.equals(other.running))
            return false;

        if (team == null && other.team != null)
            return false;
        else if (!team.equals(other.team))
            return false;

        if (academicLevel == null && other.academicLevel != null)
            return false;
        else if (!academicLevel.equals(other.academicLevel))
            return false;

        return true;
    }
}
