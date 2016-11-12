package com.sasd13.proadmin.content.extra.running;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.builder.running.RunningBaseBuilder;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class RunningParcel implements Parcelable {

    public static final Parcelable.Creator<RunningParcel> CREATOR
            = new Parcelable.Creator<RunningParcel>() {
        public RunningParcel createFromParcel(Parcel in) {
            return new RunningParcel(in);
        }

        public RunningParcel[] newArray(int size) {
            return new RunningParcel[size];
        }
    };

    private Running running;

    public RunningParcel(Running running) {
        this.running = running;
    }

    public Running getRunning() {
        return running;
    }

    private RunningParcel(Parcel in) {
        running = new RunningBaseBuilder(in.readInt(), in.readString(), in.readString()).build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(running.getYear());
        parcel.writeString(running.getProject().getCode());
        parcel.writeString(running.getTeacher().getNumber());
    }
}