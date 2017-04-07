package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.view.IBrowsable;
import com.sasd13.proadmin.view.fragment.runningteam.IRunningTeamController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.RunningTeamScope;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.view.fragment.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.view.fragment.runningteam.RunningTeamsFragment;

import java.util.List;
import java.util.Map;

public class RunningTeamController extends MainController implements IRunningTeamController, IBrowsable {

    private RunningTeamScope scope;
    private IRunningTeamService runningTeamService;
    private IReportService reportService;
    private RunningTeamReadStrategy runningTeamReadStrategy;
    private RunningTeamDependenciesStrategy runningTeamDependenciesStrategy;
    private ReportReadStrategy reportReadStrategy;
    private RunningTeamCreateStrategy runningTeamCreateStrategy;
    private RunningTeamUpdateStrategy runningTeamUpdateStrategy;
    private RunningTeamDeleteStrategy runningTeamDeleteStrategy;

    public RunningTeamController(MainActivity mainActivity, IRunningTeamService runningTeamService, IReportService reportService) {
        super(mainActivity);

        scope = new RunningTeamScope();
        this.runningTeamService = runningTeamService;
        this.reportService = reportService;
    }

    @Override
    public Object getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        listRunningTeams();
    }

    private void listRunningTeams() {
        readRunningTeams();
        startFragment(RunningTeamsFragment.newInstance());
    }

    private void readRunningTeams() {
        if (runningTeamReadStrategy == null) {
            runningTeamReadStrategy = new RunningTeamReadStrategy(this, runningTeamService);
        }

        runningTeamReadStrategy.clearParameters();
        runningTeamReadStrategy.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(runningTeamReadStrategy).execute();
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        scope.setRunningTeams(runningTeams);
    }

    @Override
    public void actionNewRunningTeam() {
        readDependencies();
        startFragment(RunningTeamNewFragment.newInstance());
    }

    private void readDependencies() {
        if (runningTeamDependenciesStrategy == null) {
            runningTeamDependenciesStrategy = new RunningTeamDependenciesStrategy(this, runningTeamService);
        }

        runningTeamDependenciesStrategy.resetParameters();
        runningTeamDependenciesStrategy.putParameter(IRunningTeamService.PARAMATERS_RUNNING, EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(runningTeamDependenciesStrategy).execute();
    }

    void onRetrieved(Map<String, List> results) {
        scope.setRunnings((List<Running>) results.get(IRunningTeamService.PARAMATERS_RUNNING));
        scope.setTeams((List<Team>) results.get(IRunningTeamService.PARAMETERS_TEAM));
        scope.setAcademicLevels((List<AcademicLevel>) results.get(IRunningTeamService.PARAMETERS_ACADEMICLEVEL));
    }

    @Override
    public void actionCreateRunningTeam(RunningTeam runningTeam) {
        if (runningTeamCreateStrategy == null) {
            runningTeamCreateStrategy = new RunningTeamCreateStrategy(this, runningTeamService);
        }

        new Requestor(runningTeamCreateStrategy).execute(runningTeam);
    }

    void onCreateRunningTeam() {
        display(R.string.message_saved);
        browse();
    }

    @Override
    public void actionShowRunningTeam(RunningTeam runningTeam) {
        scope.setRunningTeam(runningTeam);
        readDependencies();
        readReports(runningTeam);
    }

    private void readReports(RunningTeam runningTeam) {
        if (reportReadStrategy == null) {
            reportReadStrategy = new ReportReadStrategy(this, reportService);
        }

        reportReadStrategy.clearParameters();
        reportReadStrategy.putParameter(EnumParameter.YEAR.getName(), new String[]{String.valueOf(runningTeam.getRunning().getYear())});
        reportReadStrategy.putParameter(EnumParameter.PROJECT.getName(), new String[]{runningTeam.getRunning().getProject().getCode()});
        reportReadStrategy.putParameter(EnumParameter.TEACHER.getName(), new String[]{runningTeam.getRunning().getTeacher().getNumber()});
        reportReadStrategy.putParameter(EnumParameter.TEAM.getName(), new String[]{runningTeam.getTeam().getNumber()});
        reportReadStrategy.putParameter(EnumParameter.ACADEMICLEVEL.getName(), new String[]{runningTeam.getAcademicLevel().getCode()});
        new Requestor(reportReadStrategy).execute();
    }

    void onReadReports(List<Report> reports) {
        scope.setReports(reports);
    }

    @Override
    public void actionUpdateRunningTeam(RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        if (runningTeamUpdateStrategy == null) {
            runningTeamUpdateStrategy = new RunningTeamUpdateStrategy(this, runningTeamService);
        }

        new Requestor(runningTeamUpdateStrategy).execute(getRunningTeamUpdateWrapper(runningTeam, runningTeamToUpdate));
    }

    private RunningTeamUpdateWrapper getRunningTeamUpdateWrapper(RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        RunningTeamUpdateWrapper updateWrapper = new RunningTeamUpdateWrapper();

        updateWrapper.setWrapped(runningTeam);
        updateWrapper.setRunningYear(runningTeamToUpdate.getRunning().getYear());
        updateWrapper.setProjectCode(runningTeamToUpdate.getRunning().getProject().getCode());
        updateWrapper.setTeamNumber(runningTeamToUpdate.getRunning().getTeacher().getNumber());
        updateWrapper.setTeamNumber(runningTeamToUpdate.getTeam().getNumber());
        updateWrapper.setAcademicLevelCode(runningTeamToUpdate.getAcademicLevel().getCode());

        return updateWrapper;
    }

    void onUpdateRunningTeam() {
        display(R.string.message_updated);
    }

    @Override
    public void actionRemoveRunningTeam(RunningTeam runningTeam) {
        if (runningTeamDeleteStrategy == null) {
            runningTeamDeleteStrategy = new RunningTeamDeleteStrategy(this, runningTeamService);
        }

        new Requestor(runningTeamDeleteStrategy).execute(new RunningTeam[]{runningTeam});
    }

    void onDeleteRunningTeam() {
        display(R.string.message_deleted);
        browse();
    }
}