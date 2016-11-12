package com.sasd13.proadmin.content.extra.running;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.builder.running.LeadEvaluationBaseBuilder;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class LeadEvaluationParcel implements Parcelable {

    public static final Parcelable.Creator<LeadEvaluationParcel> CREATOR
            = new Parcelable.Creator<LeadEvaluationParcel>() {
        public LeadEvaluationParcel createFromParcel(Parcel in) {
            return new LeadEvaluationParcel(in);
        }

        public LeadEvaluationParcel[] newArray(int size) {
            return new LeadEvaluationParcel[size];
        }
    };

    private LeadEvaluation leadEvaluation;

    public LeadEvaluationParcel(LeadEvaluation leadEvaluation) {
        this.leadEvaluation = leadEvaluation;
    }

    public LeadEvaluation getLeadEvaluation() {
        return leadEvaluation;
    }

    private LeadEvaluationParcel(Parcel in) {
        leadEvaluation = new LeadEvaluationBaseBuilder(in.readString(), in.readString()).build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(leadEvaluation.getReport().getNumber());
        parcel.writeString(leadEvaluation.getStudent().getNumber());
    }
}
