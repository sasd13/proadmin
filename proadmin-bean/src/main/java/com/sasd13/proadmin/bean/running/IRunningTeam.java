package com.sasd13.proadmin.bean.running;

import com.sasd13.proadmin.bean.level.IAcademicLevel;
import com.sasd13.proadmin.bean.member.ITeam;

public interface IRunningTeam {

	IRunning getRunning();

	ITeam getTeam();

	IAcademicLevel getAcademicLevel();
}
