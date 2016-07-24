package com.sasd13.proadmin.util;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.EnumAcademicLevel;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public enum EnumAcademicLevelRes {
    L1(EnumAcademicLevel.L1, R.string.academiclevel_l1),
    L2(EnumAcademicLevel.L2, R.string.academiclevel_l2),
    L3(EnumAcademicLevel.L3, R.string.academiclevel_l3),
    L4(EnumAcademicLevel.L4, R.string.academiclevel_l4),
    L5(EnumAcademicLevel.L5, R.string.academiclevel_l5),
    ;

    private EnumAcademicLevel academicLevel;
    private int stringRes;

    EnumAcademicLevelRes(EnumAcademicLevel academicLevel, @StringRes int stringRes) {
        this.academicLevel = academicLevel;
        this.stringRes = stringRes;
    }

    public EnumAcademicLevel getAcademicLevel() {
        return academicLevel;
    }

    public int getStringRes() {
        return stringRes;
    }

    public static EnumAcademicLevelRes find(EnumAcademicLevel academicLevel) {
        for (EnumAcademicLevelRes academicLevelRes : values()) {
            if (academicLevelRes.academicLevel.equals(academicLevel)) {
                return academicLevelRes;
            }
        }

        return null;
    }
}
