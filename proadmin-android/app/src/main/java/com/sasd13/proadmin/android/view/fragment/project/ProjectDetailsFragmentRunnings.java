package com.sasd13.proadmin.android.view.fragment.project;

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
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.scope.ProjectScope;
import com.sasd13.proadmin.android.util.sorter.RunningSorter;
import com.sasd13.proadmin.android.view.IProjectController;
import com.sasd13.proadmin.android.view.IRunningController;
import com.sasd13.proadmin.android.view.gui.tab.RunningItemModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProjectDetailsFragmentRunnings extends Fragment implements Observer {

    private ProjectScope scope;
    private Recycler recycler;

    public static ProjectDetailsFragmentRunnings newInstance() {
        return new ProjectDetailsFragmentRunnings();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scope = (ProjectScope) (((MainActivity) getActivity()).lookup(IProjectController.class)).getScope();
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
        buildTabRunnings(view);
        buildFloatingActionButton(view);
        bindTabWithRunnings(scope.getRunnings());
    }

    private void buildTabRunnings(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IRunningController) ((MainActivity) getActivity()).lookup(IRunningController.class)).actionNewRunning(scope.getProject(), scope.getTeacher());
            }
        });
    }

    private void bindTabWithRunnings(List<Running> runnings) {
        recycler.clear();
        RunningSorter.byYear(runnings);
        addRunningsToTab(runnings);
    }

    private void addRunningsToTab(List<Running> runnings) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Running running : runnings) {
            pair = new RecyclerHolderPair(new RunningItemModel(running));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    ((IRunningController) ((MainActivity) getActivity()).lookup(IRunningController.class)).actionShowRunning(running);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void update(Observable observable, Object o) {
        bindTabWithRunnings(scope.getRunnings());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}