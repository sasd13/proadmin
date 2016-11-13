package com.sasd13.proadmin.util.caller;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public interface IRunningTeamReadServiceCaller {

    void onLoad();

    void onReadProjectsSucceeded(IReadWrapper<Project> projectReadWrapper);

    void onReadAcademicLevelsSucceeded(IReadWrapper<AcademicLevel> academicLevelReadWrapper);

    void onReadTeamsSucceeded(IReadWrapper<Team> teamReadWrapper);

    void onError(@StringRes int error);
}
