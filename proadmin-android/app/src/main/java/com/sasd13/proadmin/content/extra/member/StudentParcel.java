package com.sasd13.proadmin.content.extra.member;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.member.Student;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class StudentParcel implements Parcelable {

    public static final Parcelable.Creator<StudentParcel> CREATOR
            = new Parcelable.Creator<StudentParcel>() {
        public StudentParcel createFromParcel(Parcel in) {
            return new StudentParcel(in);
        }

        public StudentParcel[] newArray(int size) {
            return new StudentParcel[size];
        }
    };

    private Student student;

    public StudentParcel(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    private StudentParcel(Parcel in) {
        student = new Student(in.readString());

        student.setFirstName(in.readString());
        student.setLastName(in.readString());
        student.setEmail(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(student.getNumber());
        parcel.writeString(student.getFirstName());
        parcel.writeString(student.getLastName());
        parcel.writeString(student.getEmail());
    }
}
