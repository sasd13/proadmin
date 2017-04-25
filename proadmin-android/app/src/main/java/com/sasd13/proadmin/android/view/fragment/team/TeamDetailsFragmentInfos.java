package com.sasd13.proadmin.android.view.fragment.team;

import android.content.DialogInterface;
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
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.scope.TeamScope;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdate;
import com.sasd13.proadmin.view.ITeamController;
import com.sasd13.proadmin.view.gui.form.TeamForm;

import java.util.Observable;
import java.util.Observer;

public class TeamDetailsFragmentInfos extends Fragment implements Observer {

    private ITeamController controller;
    private TeamScope scope;
    private TeamForm teamForm;
    private Menu menu;

    public static TeamDetailsFragmentInfos newInstance() {
        return new TeamDetailsFragmentInfos();
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
        bindFormWithTeam(scope.getTeam());
    }

    private void buildFormTeam(View view) {
        teamForm = new TeamForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, teamForm.getHolder());
    }

    private void bindFormWithTeam(Team team) {
        teamForm.bindTeam(team);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                updateTeam();
                break;
            case R.id.menu_edit_action_delete:
                deleteTeam();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateTeam() {
        try {
            controller.actionUpdateTeam(getTeamUpdateFromForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private TeamUpdate getTeamUpdateFromForm() throws FormException {
        TeamUpdate teamUpdate = new TeamUpdate();
        Team team = scope.getTeam();

        teamUpdate.setNumber(team.getNumber());
        teamUpdate.setWrapped(team);
        team.setNumber(teamForm.getNumber());

        return teamUpdate;
    }

    private void deleteTeam() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getString(R.string.message_delete),
                getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.actionRemoveTeam(scope.getTeam());
                    }
                });
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithTeam(scope.getTeam());
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