package com.sasd13.proadmin.activity.fragment.team;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.TeamsActivity;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.content.extra.Extra;
import com.sasd13.proadmin.content.extra.member.TeamParcel;
import com.sasd13.proadmin.gui.form.TeamForm;
import com.sasd13.proadmin.service.member.TeamManageService;

public class TeamDetailsFragmentInfos extends Fragment implements IManageServiceCaller<Team> {

    private TeamsActivity parentActivity;

    private TeamForm teamForm;

    private Team team;

    private TeamManageService teamManageService;

    public static TeamDetailsFragmentInfos newInstance(Team team) {
        TeamDetailsFragmentInfos fragment = new TeamDetailsFragmentInfos();
        fragment.team = team;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readFromBundle(savedInstanceState);
        setHasOptionsMenu(true);

        parentActivity = (TeamsActivity) getActivity();
        teamManageService = new TeamManageService(this);
    }

    private void readFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

        if (team == null) {
            team = savedInstanceState.getParcelable(Extra.PARCEL_TEAM);
        }
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
    }

    private void buildFormTeam(View view) {
        teamForm = new TeamForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, teamForm.getHolder());
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
        teamManageService.updateTeam(teamForm, team);
    }

    private void deleteTeam() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        teamManageService.deleteTeam(team);
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

        bindFormWithTeam();
    }

    private void bindFormWithTeam() {
        teamForm.bindTeam(team);
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onCreateSucceeded(Team team) {
    }

    @Override
    public void onUpdateSucceeded() {
        Snackbar.make(getView(), R.string.message_updated, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSucceeded() {
        Snackbar.make(getView(), R.string.message_deleted, Snackbar.LENGTH_SHORT).show();
        parentActivity.listTeams();
    }

    @Override
    public void onError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Extra.PARCEL_TEAM, new TeamParcel(team));
    }
}