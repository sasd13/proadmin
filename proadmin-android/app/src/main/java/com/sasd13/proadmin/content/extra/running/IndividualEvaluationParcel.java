package com.sasd13.proadmin.content.extra.running;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;

/**
 * Created by ssaidali2 on 12/11/2016.
 */

public class IndividualEvaluationParcel implements Parcelable {

    public static final Parcelable.Creator<IndividualEvaluationParcel> CREATOR
            = new Parcelable.Creator<IndividualEvaluationParcel>() {
        public IndividualEvaluationParcel createFromParcel(Parcel in) {
            return new IndividualEvaluationParcel(in);
        }

        public IndividualEvaluationParcel[] newArray(int size) {
            return new IndividualEvaluationParcel[size];
        }
    };

    private IndividualEvaluation individualEvaluation;

    public IndividualEvaluationParcel(IndividualEvaluation individualEvaluation) {
        this.individualEvaluation = individualEvaluation;
    }

    public IndividualEvaluation getIndividualEvaluation() {
        return individualEvaluation;
    }

    private IndividualEvaluationParcel(Parcel in) {
        individualEvaluation = new IndividualEvaluation(in.readString(), in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(individualEvaluation.getReport().getNumber());
        parcel.writeString(individualEvaluation.getStudent().getNumber());
    }
}
