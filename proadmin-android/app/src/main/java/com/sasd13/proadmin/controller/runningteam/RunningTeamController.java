package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.IRunningTeamController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.Extra;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningTeamBuilder;
import com.sasd13.proadmin.util.wrapper.RunningTeamWrapper;
import com.sasd13.proadmin.util.wrapper.RunningTeamsWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

import java.util.List;
import java.util.Map;

public class RunningTeamController extends MainController implements IRunningTeamController {

    private Requestor requestor;
    private IRunningTeamService runningTeamService;
    private IReportService reportService;
    private RunningTeamReadStrategy runningTeamReadStrategy;
    private RunningTeamDependenciesStrategy runningTeamDependenciesStrategy;
    private ReportReadStrategy reportReadStrategy;
    private RunningTeamCreateStrategy runningTeamCreateStrategy;
    private RunningTeamUpdateStrategy runningTeamUpdateStrategy;
    private RunningTeamDeleteStrategy runningTeamDeleteStrategy;
    private RunningTeamsWrapper runningTeamsWrapper;
    private RunningTeamWrapper runningTeamWrapper;
    private int mode;

    public RunningTeamController(MainActivity mainActivity, Requestor requestor, IRunningTeamService runningTeamService, IReportService reportService) {
        super(mainActivity);

        this.requestor = requestor;
        this.runningTeamService = runningTeamService;
        this.reportService = reportService;
    }

    @Override
    public void entry() {
        mainActivity.clearHistory();
        listRunningTeams();
    }

    @Override
    public void listRunningTeams() {
        runningTeamsWrapper = new RunningTeamsWrapper();

        startProxyFragment();
        readRunningTeams();
    }

    private void readRunningTeams() {
        if (runningTeamReadStrategy == null) {
            runningTeamReadStrategy = new RunningTeamReadStrategy(this, runningTeamService);
        }

        runningTeamReadStrategy.clearParameters();
        runningTeamReadStrategy.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        requestor.setStrategy(runningTeamReadStrategy);
        requestor.execute();
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (isProxyFragmentNotDetached()) {
            runningTeamsWrapper.setRunningTeams(runningTeams);
            startFragment(RunningTeamsFragment.newInstance(runningTeamsWrapper));
        }
    }

    @Override
    public void newRunningTeam() {
        mode = Extra.MODE_NEW;
        runningTeamWrapper = new RunningTeamWrapper(new DefaultRunningTeamBuilder().build());

        startProxyFragment();
        readDependencies();
    }

    private void readDependencies() {
        if (runningTeamDependenciesStrategy == null) {
            runningTeamDependenciesStrategy = new RunningTeamDependenciesStrategy(this, runningTeamService);
        }

        runningTeamDependenciesStrategy.resetParameters();
        runningTeamDependenciesStrategy.putParameter(IRunningTeamService.PARAMATERS_RUNNING, EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        requestor.setStrategy(runningTeamDependenciesStrategy);
        requestor.execute();
    }

    void onRetrieved(Map<String, List> results) {
        if (isProxyFragmentNotDetached()) {
            runningTeamWrapper.setRunnings((List<Running>) results.get(IRunningTeamService.PARAMATERS_RUNNING));
            runningTeamWrapper.setTeams((List<Team>) results.get(IRunningTeamService.PARAMETERS_TEAM));
            runningTeamWrapper.setAcademicLevels((List<AcademicLevel>) results.get(IRunningTeamService.PARAMETERS_ACADEMICLEVEL));

            if (mode == Extra.MODE_NEW) {
                startFragment(RunningTeamNewFragment.newInstance(runningTeamWrapper));
            } else if (mode == Extra.MODE_EDIT) {
                startFragment(RunningTeamDetailsFragment.newInstance(runningTeamWrapper));
                readReports(runningTeamWrapper.getRunningTeam());
            }
        }
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
        requestor.setStrategy(reportReadStrategy);
        requestor.execute();
    }

    void onReadReports(List<Report> reports) {
        runningTeamWrapper.setReports(reports);
    }

    @Override
    public void createRunningTeam(RunningTeam runningTeam) {
        if (runningTeamCreateStrategy == null) {
            runningTeamCreateStrategy = new RunningTeamCreateStrategy(this, runningTeamService);
        }

        requestor.setStrategy(runningTeamCreateStrategy);
        requestor.execute(runningTeam);
    }

    @Override
    public void showRunningTeam(RunningTeam runningTeam) {
        mode = Extra.MODE_EDIT;
        runningTeamWrapper = new RunningTeamWrapper(runningTeam);

        startProxyFragment();
        readDependencies();
    }

    @Override
    public void updateRunningTeam(RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        if (runningTeamUpdateStrategy == null) {
            runningTeamUpdateStrategy = new RunningTeamUpdateStrategy(this, runningTeamService);
        }

        requestor.setStrategy(runningTeamUpdateStrategy);
        requestor.execute(getRunningTeamUpdateWrapper(runningTeam, runningTeamToUpdate));
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

    @Override
    public void deleteRunningTeams(RunningTeam[] runningTeams) {
        if (runningTeamDeleteStrategy == null) {
            runningTeamDeleteStrategy = new RunningTeamDeleteStrategy(this, runningTeamService);
        }

        requestor.setStrategy(runningTeamDeleteStrategy);
        requestor.execute(runningTeams);
    }
}