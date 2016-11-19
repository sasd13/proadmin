package com.sasd13.proadmin.content.extra.member;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.member.Teacher;

/**
 * Created by ssaidali2 on 31/10/2016.
 */

public class TeacherParcel implements Parcelable {

    public static final Parcelable.Creator<TeacherParcel> CREATOR
            = new Parcelable.Creator<TeacherParcel>() {
        public TeacherParcel createFromParcel(Parcel in) {
            return new TeacherParcel(in);
        }

        public TeacherParcel[] newArray(int size) {
            return new TeacherParcel[size];
        }
    };

    private Teacher teacher;

    public TeacherParcel(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    private TeacherParcel(Parcel in) {
        teacher = new Teacher(in.readString());

        teacher.setFirstName(in.readString());
        teacher.setLastName(in.readString());
        teacher.setEmail(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(teacher.getNumber());
        parcel.writeString(teacher.getFirstName());
        parcel.writeString(teacher.getLastName());
        parcel.writeString(teacher.getEmail());
    }
}
