package com.sasd13.proadmin.android.bean;

import java.util.Date;

public class Report {

    private RunningTeam runningTeam;
    private String number, comment;
    private Date dateMeeting;
    private int session;

    public RunningTeam getRunningTeam() {
        return runningTeam;
    }

    public void setRunningTeam(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateMeeting() {
        return dateMeeting;
    }

    public void setDateMeeting(Date dateMeeting) {
        this.dateMeeting = dateMeeting;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Report other = (Report) obj;

        if (number == null && other.number != null)
            return false;
        else if (!number.equals(other.number))
            return false;

        return true;
    }
}
