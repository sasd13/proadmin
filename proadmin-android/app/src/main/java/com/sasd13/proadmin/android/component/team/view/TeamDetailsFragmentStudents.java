package com.sasd13.proadmin.android.component.team.view;

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
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.component.student.view.IStudentController;
import com.sasd13.proadmin.android.component.team.scope.TeamScope;
import com.sasd13.proadmin.android.gui.tab.StudentTeamItemModel;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.util.sorter.StudentTeamSorter;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TeamDetailsFragmentStudents extends Fragment implements Observer {

    private TeamScope scope;
    private Recycler recycler;

    public static TeamDetailsFragmentStudents newInstance() {
        return new TeamDetailsFragmentStudents();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scope = (TeamScope) (((MainActivity) getActivity()).lookup(ITeamController.class)).getScope();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv_w_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabStudents(view);
        buildFloatingActionButton(view);
        bindTabWithStudents(scope.getStudentTeams());
    }

    private void buildTabStudents(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IStudentController) ((MainActivity) getActivity()).lookup(IStudentController.class)).actionNewStudent(scope.getTeam());
            }
        });
    }

    private void bindTabWithStudents(List<StudentTeam> studentTeams) {
        recycler.clear();
        StudentTeamSorter.byStudentIntermediary(studentTeams);
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
                    ((IStudentController) ((MainActivity) getActivity()).lookup(IStudentController.class)).actionShowStudent(studentTeam);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void update(Observable observable, Object o) {
        bindTabWithStudents(scope.getStudentTeams());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}