package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.Teacher;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.TeacherAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.TeacherAdapterM2I;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class TeacherITFAdapter extends ITFAdapter<Teacher, TeacherBean> {

    public TeacherITFAdapter() {
        super(new TeacherAdapterI2M(), new TeacherAdapterM2I());
    }
}
