package com.sasd13.proadmin.content.extra;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.builder.AcademicLevelBaseBuilder;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class AcademicLevelParcel implements Parcelable {

    public static final Parcelable.Creator<AcademicLevelParcel> CREATOR
            = new Parcelable.Creator<AcademicLevelParcel>() {
        public AcademicLevelParcel createFromParcel(Parcel in) {
            return new AcademicLevelParcel(in);
        }

        public AcademicLevelParcel[] newArray(int size) {
            return new AcademicLevelParcel[size];
        }
    };

    private AcademicLevel academicLevel;

    public AcademicLevelParcel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }

    public AcademicLevel getAcademicLevel() {
        return academicLevel;
    }

    private AcademicLevelParcel(Parcel in) {
        academicLevel = new AcademicLevelBaseBuilder(in.readString()).build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(academicLevel.getCode());
    }
}