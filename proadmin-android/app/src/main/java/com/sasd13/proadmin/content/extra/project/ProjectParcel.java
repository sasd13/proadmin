package com.sasd13.proadmin.content.extra.project;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.project.Project;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class ProjectParcel implements Parcelable {

    public static final Parcelable.Creator<ProjectParcel> CREATOR
            = new Parcelable.Creator<ProjectParcel>() {
        public ProjectParcel createFromParcel(Parcel in) {
            return new ProjectParcel(in);
        }

        public ProjectParcel[] newArray(int size) {
            return new ProjectParcel[size];
        }
    };

    private Project project;

    public ProjectParcel(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    private ProjectParcel(Parcel in) {
        project = new Project(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(project.getCode());
    }
}