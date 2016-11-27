package com.sasd13.proadmin.util.caller;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public interface IRunningTeamReadServiceCaller {

    void onLoad();

    void onReadRunningsSucceeded(IReadWrapper<Running> runningReadWrapper);

    void onReadAcademicLevelsSucceeded(IReadWrapper<AcademicLevel> academicLevelReadWrapper);

    void onReadTeamsSucceeded(IReadWrapper<Team> teamReadWrapper);

    void onError(@StringRes int error);
}
