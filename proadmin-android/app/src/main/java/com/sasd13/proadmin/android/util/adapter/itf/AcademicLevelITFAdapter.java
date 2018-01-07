package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.AcademicLevel;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.AcademicLevelAdapterI2M;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class AcademicLevelITFAdapter extends ITFAdapter<AcademicLevel, AcademicLevelBean> {

    public AcademicLevelITFAdapter() {
        super(new AcademicLevelAdapterI2M());
    }
}
