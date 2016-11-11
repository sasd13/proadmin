package com.sasd13.proadmin.activities.fragments.running;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.RunningsActivity;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.tab.RunningItemModel;
import com.sasd13.proadmin.service.running.RunningReadService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.adapter.IntegersToStringsAdapter;
import com.sasd13.proadmin.util.builder.running.RunningsYearsBuilder;
import com.sasd13.proadmin.util.filter.running.RunningYearCriteria;
import com.sasd13.proadmin.util.sorter.IntegersSorter;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;
import com.sasd13.proadmin.wrapper.read.running.IRunningReadWrapper;

import java.util.ArrayList;
import java.util.List;

public class RunningsFragment extends Fragment implements IReadServiceCaller<IRunningReadWrapper> {

    private RunningsActivity parentActivity;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Spin spinYears;
    private Recycler runningsTab;

    private List<Integer> years;
    private List<Running> runnings;

    private RunningReadService runningReadService;

    public static RunningsFragment newInstance() {
        return new RunningsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (RunningsActivity) getActivity();
        years = new ArrayList<>();
        runnings = new ArrayList<>();
        runningReadService = new RunningReadService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_srl_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildSwipeRefreshLayout(view);
        buildTabRunnings(view);
        buildFloatingActionButton(view);
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_fab_swiperefreshlayout);
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
        runningsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_fab_recyclerview));
        runningsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_srl_fab_floatingactionbutton);
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
        readRunningsFromWS();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_runnings, menu);

        buildSpinYears(menu.findItem(R.id.menu_runnings_spinner));
    }

    private void buildSpinYears(MenuItem menuItem) {
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(menuItem);

        spinYears = new Spin(spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                fillTabRunningsByYear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReadSucceeded(IRunningReadWrapper runningReadWrapper) {
        swipeRefreshLayout.setRefreshing(false);
        bindYears(runningReadWrapper.getRunnings());
        bindRunningsToTab(runningReadWrapper.getRunnings());
    }

    private void bindYears(List<Running> runningsFromWS) {
        List<Integer> yearsToSpin = new RunningsYearsBuilder(runningsFromWS).build();

        IntegersSorter.byDesc(yearsToSpin);
        years.clear();
        years.addAll(yearsToSpin);
        fillSpinYears();
    }

    private void fillSpinYears() {
        spinYears.clear();
        spinYears.addItems(new IntegersToStringsAdapter().adapt(years));
        spinYears.resetPosition();
    }

    private void bindRunningsToTab(List<Running> runningsFromWS) {
        RunningsSorter.byYear(runningsFromWS);
        runnings.clear();
        runnings.addAll(runningsFromWS);
        fillTabRunningsByYear();
    }

    private void fillTabRunningsByYear() {
        runningsTab.clear();

        int year = years.get(spinYears.getSelectedPosition());
        List<Running> runningsToTab = new RunningYearCriteria(year).meetCriteria(runnings);

        RunningsSorter.byProjectCode(runningsToTab);
        addRunningsToTab(runningsToTab);
    }

    private void addRunningsToTab(List<Running> runnings) {
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