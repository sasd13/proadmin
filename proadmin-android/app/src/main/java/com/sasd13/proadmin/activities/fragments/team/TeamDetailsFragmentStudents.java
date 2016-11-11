package com.sasd13.proadmin.activities.fragments.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.RunningsActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.StudentTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.tab.StudentTeamItemModel;
import com.sasd13.proadmin.service.running.StudentTeamReadService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.sorter.running.StudentTeamsSorter;
import com.sasd13.proadmin.wrapper.read.member.IStudentTeamReadWrapper;
import com.sasd13.proadmin.wrapper.read.member.IStudentReadWrapper;

import java.util.ArrayList;
import java.util.List;

public class TeamDetailsFragmentStudents extends Fragment implements IReadServiceCaller<IStudentReadWrapper> {

    private RunningsActivity parentActivity;

    private Recycler studentTeamsTab;

    private Team team;
    private List<StudentTeam> studentTeams;

    private StudentTeamReadService studentTeamReadService;

    public static TeamDetailsFragmentStudents newInstance(Team team) {
        TeamDetailsFragmentStudents fragment = new TeamDetailsFragmentStudents();
        fragment.team = team;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (RunningsActivity) getActivity();
        studentTeams = new ArrayList<>();
        studentTeamReadService = new StudentTeamReadService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabRunnings(view);
        buildFloatingActionButton(view);
    }

    private void buildTabRunnings(View view) {
        studentTeamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        studentTeamsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newStudentTeam(running);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        readStudentTeamsFromWS();
    }

    private void readStudentTeamsFromWS() {
        studentTeamReadService.readStudentTeams(
                running.getYear(),
                running.getProject().getCode(),
                SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onReadSucceeded(IStudentTeamReadWrapper studentTeamReadWrapper) {
        studentTeams.clear();
        studentTeams.addAll(studentTeamReadWrapper.getStudentTeams());
        fillTabStudentTeamsByTeam();
    }

    private void fillTabStudentTeamsByTeam() {
        studentTeamsTab.clear();
        StudentTeamsSorter.byTeamNumber(studentTeams);
        addRunningsToTab();
    }

    private void addRunningsToTab() {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final StudentTeam studentTeam : studentTeams) {
            pair = new RecyclerHolderPair(new StudentTeamItemModel(studentTeam));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.showTeam(studentTeam.getTeam());
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(studentTeamsTab, holder);
    }

    @Override
    public void onError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}