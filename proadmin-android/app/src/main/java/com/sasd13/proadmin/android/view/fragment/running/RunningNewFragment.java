package com.sasd13.proadmin.android.view.fragment.running;

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

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.scope.RunningScope;
import com.sasd13.proadmin.android.view.IRunningController;
import com.sasd13.proadmin.android.view.gui.form.RunningForm;

import java.util.Observable;
import java.util.Observer;

public class RunningNewFragment extends Fragment implements Observer {

    private IRunningController controller;
    private RunningScope scope;
    private RunningForm runningForm;
    private Menu menu;

    public static RunningNewFragment newInstance() {
        return new RunningNewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IRunningController) ((MainActivity) getActivity()).lookup(IRunningController.class);
        scope = (RunningScope) controller.getScope();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormRunning(view);
        bindFormWithRunning(scope.getRunning());
    }

    private void buildFormRunning(View view) {
        runningForm = new RunningForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, runningForm.getHolder());
    }

    private void bindFormWithRunning(Running running) {
        runningForm.bindRunning(running);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.menu_edit_action_delete).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                createRunning();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createRunning() {
        try {
            editRunningWithForm();
            controller.actionCreateRunning(scope.getRunning());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private void editRunningWithForm() throws FormException {
        Running running = scope.getRunning();

        running.setYear(runningForm.getYear());
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_running));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.title_new));
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithRunning(scope.getRunning());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);

        if (menu != null) {
            menu.setGroupVisible(R.id.menu_edit_group, false);
        }
    }
}