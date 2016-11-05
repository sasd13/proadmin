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
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.tab.RunningItemModel;
import com.sasd13.proadmin.service.IReadServiceCaller;
import com.sasd13.proadmin.service.running.RunningReadService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;

import java.util.ArrayList;
import java.util.List;

public class RunningsFragment extends Fragment implements IReadServiceCaller<Running> {

    private RunningsActivity parentActivity;
    private RunningReadService runningReadService;
    private Recycler runningsTab;
    private List<Running> runnings;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static RunningsFragment newInstance() {
        return new RunningsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (RunningsActivity) getActivity();
        runningReadService = new RunningReadService(this);
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
        readRunningsFromWS();
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.runnings_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readRunningsFromWS();
            }
        });
    }

    private void readRunningsFromWS() {
        runningReadService.readRunnings(
                SessionHelper.getExtraId(getContext(), Extra.PROJECT_CODE),
                SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER)
        );
    }

    private void buildTabRunnings(View view) {
        runningsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.runnings_recyclerview));
        runningsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.runnings_floatingactionbutton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newRunning();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_runnings));
        parentActivity.getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReadSucceeded(List<Running> runningsFromWS) {
        swipeRefreshLayout.setRefreshing(false);

        bindRunningsToTab(runningsFromWS);
    }

    private void bindRunningsToTab(List<Running> runningsFromWS) {
        RunningsSorter.byYear(runningsFromWS);

        runnings.clear();
        runnings.addAll(runningsFromWS);

        fillTabRunningsByProject();
    }

    private void fillTabRunningsByProject() {
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
                    parentActivity.showRunning(running);
                }
            });

            holder.add(String.valueOf(running.getYear()), pair);
        }

        RecyclerHelper.addAll(runningsTab, holder);
    }

    @Override
    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}