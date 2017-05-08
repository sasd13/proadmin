package com.sasd13.proadmin.android.bean;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.androidex.util.DateTimeHelper;

import java.util.Locale;

/**
 * Created by ssaidali2 on 08/05/2017.
 */

public class UserPreferences implements Parcelable {

    private static final String DATETIME_SEPARATOR = " ";
    public static final String PATTERN_DATE_DEFAULT = "yyyy-MM-dd";
    public static final String PATTERN_TIME_DEFAULT = "HH-mm";
    public static final String PATTERN_DATETIME_DEFAULT = PATTERN_DATE_DEFAULT + DATETIME_SEPARATOR + PATTERN_TIME_DEFAULT;
    public static final Creator<UserPreferences> CREATOR = new Creator<UserPreferences>() {
        @Override
        public UserPreferences createFromParcel(Parcel in) {
            return new UserPreferences(in);
        }

        @Override
        public UserPreferences[] newArray(int size) {
            return new UserPreferences[size];
        }
    };

    private String language;
    private String patternDate, patternTime;

    public UserPreferences() {
        language = Locale.ENGLISH.getLanguage();
        patternDate = PATTERN_DATE_DEFAULT;
        patternTime = PATTERN_TIME_DEFAULT;
    }

    public UserPreferences(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        } else {
            language = context.getResources().getConfiguration().locale.getLanguage();
        }

        patternDate = DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.EnumFormat.SHORT);
        patternTime = DateTimeHelper.getLocaleTimeFormatPattern(context, DateTimeHelper.EnumFormat.SHORT);
    }

    protected UserPreferences(Parcel in) {
        language = in.readString();
        patternDate = in.readString();
        patternTime = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(language);
        parcel.writeString(patternDate);
        parcel.writeString(patternTime);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPatternDate() {
        return patternDate;
    }

    public void setPatternDate(String patternDate) {
        this.patternDate = patternDate;
    }

    public String getPatternTime() {
        return patternTime;
    }

    public void setPatternTime(String patternTime) {
        this.patternTime = patternTime;
    }

    public String getPatternDateTime() {
        return patternDate + DATETIME_SEPARATOR + patternTime;
    }
}
