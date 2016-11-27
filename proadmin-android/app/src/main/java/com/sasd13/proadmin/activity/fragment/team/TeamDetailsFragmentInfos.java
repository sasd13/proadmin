package com.sasd13.proadmin.activity.fragment.team;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.TeamsActivity;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.gui.form.TeamForm;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.builder.member.TeamFromFormBuilder;
import com.sasd13.proadmin.ws.service.TeamsService;

import java.util.List;

public class TeamDetailsFragmentInfos extends Fragment implements TeamsService.ManageCaller {

    private TeamsActivity parentActivity;

    private TeamForm teamForm;

    private Team team;

    private TeamsService service;

    public static TeamDetailsFragmentInfos newInstance(Team team) {
        TeamDetailsFragmentInfos fragment = new TeamDetailsFragmentInfos();
        fragment.team = team;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (TeamsActivity) getActivity();
        service = new TeamsService(this);
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
        buildFormTeam(view);
        bindFormWithTeam();
    }

    private void buildFormTeam(View view) {
        teamForm = new TeamForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, teamForm.getHolder());
    }

    private void bindFormWithTeam() {
        teamForm.bindTeam(team);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

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
            service.update(getTeamFromForm(), team);
        } catch (FormException e) {
            displayError(e.getMessage());
        }
    }

    private Team getTeamFromForm() throws FormException {
        return new TeamFromFormBuilder(teamForm).build();
    }

    private void deleteTeam() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        service.delete(team);
                    }
                });
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onCreated() {
    }

    @Override
    public void onUpdated() {
        Snackbar.make(getView(), R.string.message_updated, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleted() {
        Snackbar.make(getView(), R.string.message_deleted, Snackbar.LENGTH_SHORT).show();
        parentActivity.listTeams();
    }

    @Override
    public void onError(List<String> errors) {
        displayError(WebServiceUtils.handleErrors(getContext(), errors));
    }

    public void displayError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}