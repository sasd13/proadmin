package com.sasd13.proadmin.content.extra.member;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.builder.member.StudentTeamBaseBuilder;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class StudentTeamParcel implements Parcelable {

    public static final Parcelable.Creator<StudentTeamParcel> CREATOR
            = new Parcelable.Creator<StudentTeamParcel>() {
        public StudentTeamParcel createFromParcel(Parcel in) {
            return new StudentTeamParcel(in);
        }

        public StudentTeamParcel[] newArray(int size) {
            return new StudentTeamParcel[size];
        }
    };

    private StudentTeam studentTeam;

    public StudentTeamParcel(StudentTeam studentTeam) {
        this.studentTeam = studentTeam;
    }

    public StudentTeam getStudentTeam() {
        return studentTeam;
    }

    private StudentTeamParcel(Parcel in) {
        studentTeam = new StudentTeamBaseBuilder(in.readString(), in.readString()).build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(studentTeam.getStudent().getNumber());
        parcel.writeString(studentTeam.getTeam().getNumber());
    }
}
