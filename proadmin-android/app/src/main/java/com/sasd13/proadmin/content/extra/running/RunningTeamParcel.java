package com.sasd13.proadmin.content.extra.running;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.builder.running.RunningTeamBaseBuilder;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class RunningTeamParcel implements Parcelable {

    public static final Parcelable.Creator<RunningTeamParcel> CREATOR
            = new Parcelable.Creator<RunningTeamParcel>() {
        public RunningTeamParcel createFromParcel(Parcel in) {
            return new RunningTeamParcel(in);
        }

        public RunningTeamParcel[] newArray(int size) {
            return new RunningTeamParcel[size];
        }
    };

    private RunningTeam runningTeam;

    public RunningTeamParcel(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;
    }

    public RunningTeam getRunningTeam() {
        return runningTeam;
    }

    private RunningTeamParcel(Parcel in) {
        runningTeam = new RunningTeamBaseBuilder(
                in.readInt(),
                in.readString(),
                in.readString(),
                in.readString(),
                in.readString()).build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(runningTeam.getRunning().getYear());
        parcel.writeString(runningTeam.getRunning().getProject().getCode());
        parcel.writeString(runningTeam.getRunning().getTeacher().getNumber());
        parcel.writeString(runningTeam.getTeam().getNumber());
        parcel.writeString(runningTeam.getAcademicLevel().getCode());
    }
}