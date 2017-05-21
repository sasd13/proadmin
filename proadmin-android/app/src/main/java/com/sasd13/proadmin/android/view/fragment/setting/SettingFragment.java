package com.sasd13.proadmin.android.view.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.bean.user.UserUpdate;
import com.sasd13.proadmin.android.scope.SettingScope;
import com.sasd13.proadmin.android.view.ISettingController;
import com.sasd13.proadmin.android.view.gui.form.UserForm;
import com.sasd13.proadmin.util.EnumPreference;

import java.util.Observable;
import java.util.Observer;

public class SettingFragment extends Fragment implements Observer {

    private ISettingController controller;
    private SettingScope scope;
    private UserForm userForm;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Menu menu;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ISettingController) ((MainActivity) getActivity()).lookup(ISettingController.class);
        scope = (SettingScope) controller.getScope();

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv_w_srl, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        buildFormUser(view);
        buildSwipeRefreshLayout(view);
        bindFormWithUser(scope.getUser());
    }

    private void buildFormUser(View view) {
        userForm = new UserForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, userForm.getHolder());
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                controller.actionLoadUser();
            }
        });
    }

    private void bindFormWithUser(User user) {
        userForm.bindUser(user);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

        inflater.inflate(R.menu.menu_setting, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings_action_save:
                updateUser();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateUser() {
        try {
            controller.actionUpdateUser(getUserUpdateFromForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private UserUpdate getUserUpdateFromForm() throws FormException {
        UserUpdate userUpdate = new UserUpdate();
        User user = scope.getUser();

        user.setEmail(userForm.getEmail());
        user.getUserPreferences().findPreference(EnumPreference.GENERAL_DATE).setValue(userForm.getPreferenceDate());
        userUpdate.setUser(user);

        return userUpdate;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_settings));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        swipeRefreshLayout.setRefreshing(scope.isLoading());
        bindFormWithUser(scope.getUser());
    }

    @Override
    public void onPause() {
        super.onPause();

        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.destroyDrawingCache();
        swipeRefreshLayout.clearAnimation();
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