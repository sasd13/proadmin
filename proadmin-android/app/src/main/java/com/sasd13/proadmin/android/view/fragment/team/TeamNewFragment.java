package com.sasd13.proadmin.android.view.fragment.team;

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
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.scope.TeamScope;
import com.sasd13.proadmin.android.view.ITeamController;
import com.sasd13.proadmin.android.view.gui.form.TeamForm;

import java.util.Observable;
import java.util.Observer;

public class TeamNewFragment extends Fragment implements Observer {

    private ITeamController controller;
    private TeamScope scope;
    private TeamForm teamForm;
    private Menu menu;

    public static TeamNewFragment newInstance() {
        return new TeamNewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ITeamController) ((MainActivity) getActivity()).lookup(ITeamController.class);
        scope = (TeamScope) controller.getScope();

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
        buildFormTeam(view);
    }

    private void buildFormTeam(View view) {
        teamForm = new TeamForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, teamForm.getHolder());
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
                createTeam();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createTeam() {
        try {
            editTeamWithForm();
            controller.actionCreateTeam(scope.getTeam());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private void editTeamWithForm() throws FormException {
        Team team = scope.getTeam();

        team.setNumber(teamForm.getNumber());
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_team));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.title_new));
    }

    @Override
    public void update(Observable observable, Object o) {
        //Do nothing
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