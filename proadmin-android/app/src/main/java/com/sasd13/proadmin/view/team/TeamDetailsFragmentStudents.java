package com.sasd13.proadmin.view.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.gui.tab.StudentTeamItemModel;
import com.sasd13.proadmin.util.sorter.member.StudentTeamsSorter;
import com.sasd13.proadmin.util.wrapper.TeamDependencyWrapper;
import com.sasd13.proadmin.view.ITeamController;

import java.util.List;

public class TeamDetailsFragmentStudents extends Fragment {

    private ITeamController controller;
    private Team team;
    private TeamDependencyWrapper dependencyWrapper;
    private Recycler studentTeamsTab;

    public static TeamDetailsFragmentStudents newInstance(ITeamController controller, Team team, TeamDependencyWrapper dependencyWrapper) {
        TeamDetailsFragmentStudents fragment = new TeamDetailsFragmentStudents();
        fragment.controller = controller;
        fragment.team = team;
        fragment.dependencyWrapper = dependencyWrapper;

        return fragment;
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
        buildTabStudents(view);
        buildFloatingActionButton(view);
        bindTabWithStudentTeams(dependencyWrapper.getStudentTeams());
    }

    private void buildTabStudents(View view) {
        studentTeamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        studentTeamsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.newStudent(team);
            }
        });
    }

    private void bindTabWithStudentTeams(List<StudentTeam> studentTeams) {
        StudentTeamsSorter.byStudentNumber(studentTeams);
        addTeamsToTab(studentTeams);
    }

    private void addTeamsToTab(List<StudentTeam> studentTeams) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final StudentTeam studentTeam : studentTeams) {
            pair = new RecyclerHolderPair(new StudentTeamItemModel(studentTeam));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    controller.showStudent(studentTeam.getStudent());
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(studentTeamsTab, holder);
    }
}