package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.StudentAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.StudentAdapterM2I;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class StudentITFAdapter extends ITFAdapter<Student, StudentBean> {

    public StudentITFAdapter() {
        super(new StudentAdapterI2M(), new StudentAdapterM2I());
    }
}
