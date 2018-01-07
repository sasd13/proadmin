package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.StudentTeam;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.StudentTeamAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.StudentTeamAdapterM2I;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public class StudentTeamITFAdapter extends ITFAdapter<StudentTeam, StudentTeamBean> {

	public StudentTeamITFAdapter() {
		super(new StudentTeamAdapterI2M(), new StudentTeamAdapterM2I());
	}
}
