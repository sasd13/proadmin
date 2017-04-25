package com.sasd13.proadmin.android.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.project.CoreInfo;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;

import java.text.SimpleDateFormat;

public class ProjectAdapterB2I implements IAdapter<Project, ProjectBean> {

    @Override
    public ProjectBean adapt(Project s) {
        ProjectBean t = new ProjectBean();

        Id id = new Id();
        id.setId(s.getCode());
        t.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setCode(s.getCode());
        coreInfo.setDateCreation(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).format(s.getDateCreation()));
        coreInfo.setTitle(s.getTitle());
        coreInfo.setDescription(s.getDescription());
        t.setCoreInfo(coreInfo);

        return t;
    }
}
