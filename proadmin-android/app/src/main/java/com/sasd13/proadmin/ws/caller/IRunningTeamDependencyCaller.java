package com.sasd13.proadmin.ws.caller;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;

import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public interface IRunningTeamDependencyCaller extends IWebServiceCaller {

    void onRetrieved(List<Project> projects, List<Team> teams, List<AcademicLevel> academicLevels);
}
