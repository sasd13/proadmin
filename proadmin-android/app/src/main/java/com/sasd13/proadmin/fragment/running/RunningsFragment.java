package com.sasd13.proadmin.fragment.running;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.RunningsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.gui.tab.RunningItemModel;
import com.sasd13.proadmin.handler.RunningsHandler;

import java.util.ArrayList;
import java.util.List;

public class RunningsFragment extends Fragment {

    private Project project;
    private RunningsActivity parentActivity;
    private RunningsHandler runningsHandler;
    private Recycler runningsTab;
    private List<Running> runnings;
    private WaitDialog waitDialog;

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
        runningsHandler = new RunningsHandler(this);
        runnings = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_runnings, container, false);

        GUIHelper.colorTitles(view);
        buildView(view);

        return view;
    }

    private void buildView(View view) {
        buildProjectView(view);
        buildTabRunnings(view);
        readTeacher();
    }

    private void buildProjectView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.runnings_textview_project);
        textView.setText(project.getCode() + " - " + project.getTitle());
    }

    private void buildTabRunnings(View view) {
        runningsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.runnings_recyclerview));
        runningsTab.addDividerItemDecoration();
    }

    private void fillTabRunnings() {
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

    private void readTeacher() {
        runningsHandler.readProjects(project.getId());
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_runnings));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_runnings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_runnings_action_refresh:
                readTeacher();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void onLoad() {
        waitDialog = new WaitDialog(getContext());
        waitDialog.show();
    }

    public void onReadSucceeded(List<Running> runnings) {
        waitDialog.dismiss();

        this.runnings.clear();
        this.runnings.addAll(runnings);

        fillTabRunnings();
    }

    public void onError(String message) {
        waitDialog.dismiss();

        if (runnings.isEmpty()) {
            runnings.addAll(runningsHandler.readRunningsFromCache(project.getId()));

            fillTabRunnings();
        }

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}