package com.sasd13.proadmin.content.extra.member;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.member.Team;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class TeamParcel implements Parcelable {

    public static final Parcelable.Creator<TeamParcel> CREATOR
            = new Parcelable.Creator<TeamParcel>() {
        public TeamParcel createFromParcel(Parcel in) {
            return new TeamParcel(in);
        }

        public TeamParcel[] newArray(int size) {
            return new TeamParcel[size];
        }
    };

    private Team team;

    public TeamParcel(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    private TeamParcel(Parcel in) {
        team = new Team(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(team.getNumber());
    }
}