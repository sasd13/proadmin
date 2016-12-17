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

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.fragment.IRunningController;
import com.sasd13.proadmin.gui.form.RunningForm;
import com.sasd13.proadmin.util.wrapper.RunningWrapper;

public class RunningNewFragment extends Fragment {

    private IRunningController controller;
    private Running running;
    private RunningForm runningForm;

    public static RunningNewFragment newInstance(RunningWrapper runningWrapper) {
        RunningNewFragment fragment = new RunningNewFragment();
        fragment.running = runningWrapper.getRunning();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        controller = (IRunningController) ((MainActivity) getActivity()).lookup(IRunningController.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormRunning(view);
        bindFormWithRunning();
    }

    private void buildFormRunning(View view) {
        runningForm = new RunningForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningForm.getHolder());
    }

    private void bindFormWithRunning() {
        runningForm.bindRunning(running);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

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
            controller.createRunning(running);
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    private void editRunningWithForm() throws FormException {
        running.setYear(runningForm.getYear());
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_project));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getResources().getString(R.string.title_running));
    }
}