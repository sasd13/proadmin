package com.sasd13.proadmin.activities.fragments.running;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.sasd13.proadmin.activities.RunningsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.gui.tab.RunningItemModel;
import com.sasd13.proadmin.service.RunningsService;

import java.util.ArrayList;
import java.util.List;

public class RunningsFragment extends Fragment {

    private Project project;
    private RunningsActivity parentActivity;
    private RunningsService runningsService;
    private Recycler runningsTab;
    private List<Running> runnings;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton floatingActionButton;

    public static RunningsFragment newInstance(Project project) {
        RunningsFragment runningsFragment = new RunningsFragment();
        runningsFragment.project = project;

        return runningsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (RunningsActivity) getActivity();
        runningsService = new RunningsService(this);
        runnings = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_runnings, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildSwipeRefreshLayout(view);
        buildTabRunnings(view);
        buildFloatingActionButton(view);
        readRunnings();
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.runnings_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                runningsService.readRunnings(project);
            }
        });
    }

    private void buildTabRunnings(View view) {
        runningsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.runnings_recyclerview));
        runningsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.runnings_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newRunning();
            }
        });
    }

    private void readRunnings() {
        if (runnings.isEmpty()) {
            readRunningsFromCache();
        }

        refreshView();
        readRunningsFromWS();
    }

    private void readRunningsFromCache() {
        runnings.addAll(runningsService.readRunningsFromCache(project));
    }

    private void refreshView() {
        runningsTab.clear();
        addRunningsToTab();
    }

    private void addRunningsToTab() {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Running running : runnings) {
            pair = new RecyclerHolderPair(new RunningItemModel(running));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.editRunning(running);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(runningsTab, holder);
    }

    private void readRunningsFromWS() {
        runningsService.readRunnings(project);
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_runnings));
        parentActivity.getSupportActionBar().setSubtitle(null);
    }

    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void onReadSucceeded(List<Running> runningsFromWS) {
        swipeRefreshLayout.setRefreshing(false);

        runnings.clear();
        runnings.addAll(runningsFromWS);

        refreshView();
    }

    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}