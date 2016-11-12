package com.sasd13.proadmin.content.extra.running;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.running.Report;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class ReportParcel implements Parcelable {

    public static final Parcelable.Creator<ReportParcel> CREATOR
            = new Parcelable.Creator<ReportParcel>() {
        public ReportParcel createFromParcel(Parcel in) {
            return new ReportParcel(in);
        }

        public ReportParcel[] newArray(int size) {
            return new ReportParcel[size];
        }
    };

    private Report report;

    public ReportParcel(Report report) {
        this.report = report;
    }

    public Report getReport() {
        return report;
    }

    private ReportParcel(Parcel in) {
        report = new Report();

        report.setNumber(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(report.getNumber());
    }
}