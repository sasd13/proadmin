package com.sasd13.proadmin.view.team;

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
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.gui.form.TeamForm;
import com.sasd13.proadmin.util.builder.member.TeamFromFormBuilder;
import com.sasd13.proadmin.view.ITeamController;

public class TeamDetailsFragmentInfos extends Fragment {

    private ITeamController controller;
    private Team team;
    private TeamForm teamForm;

    public static TeamDetailsFragmentInfos newInstance(ITeamController controller, Team team) {
        TeamDetailsFragmentInfos fragment = new TeamDetailsFragmentInfos();
        fragment.controller = controller;
        fragment.team = team;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
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
            Team teamFromForm = new TeamFromFormBuilder(teamForm).build();

            controller.updateTeam(teamFromForm, team);
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    private void deleteTeam() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.deleteTeam(team);
                    }
                });
    }
}