package com.sasd13.proadmin.android.component.project.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.component.project.scope.ProjectScope;
import com.sasd13.proadmin.android.gui.form.ProjectForm;
import com.sasd13.proadmin.android.model.Project;

import java.util.Observable;
import java.util.Observer;

public class ProjectDetailsFragmentInfos extends Fragment implements Observer {

    private ProjectScope scope;
    private ProjectForm projectForm;

    public static ProjectDetailsFragmentInfos newInstance() {
        return new ProjectDetailsFragmentInfos();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scope = (ProjectScope) ((MainActivity) getActivity()).lookup(IProjectController.class).getScope();
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
        buildFormProject(view);
        bindFormWithProject(scope.getProject());
    }

    private void buildFormProject(View view) {
        projectForm = new ProjectForm(getContext(), scope.getUserPreferences());
        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));

        recycler.addDividerItemDecoration();
        RecyclerHelper.addAll(recycler, projectForm.getHolder());
    }

    private void bindFormWithProject(Project project) {
        projectForm.bind(project);
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithProject(scope.getProject());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}