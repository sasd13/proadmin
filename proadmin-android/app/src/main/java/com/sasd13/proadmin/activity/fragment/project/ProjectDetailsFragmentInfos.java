package com.sasd13.proadmin.activity.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.content.extra.Extra;
import com.sasd13.proadmin.content.extra.project.ProjectParcel;
import com.sasd13.proadmin.gui.form.ProjectForm;

public class ProjectDetailsFragmentInfos extends Fragment {

    private ProjectForm projectForm;

    private Project project;

    public static ProjectDetailsFragmentInfos newInstance(Project project) {
        ProjectDetailsFragmentInfos fragment = new ProjectDetailsFragmentInfos();
        fragment.project = project;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readFromBundle(savedInstanceState);
    }

    private void readFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

        if (project == null) {
            project = savedInstanceState.getParcelable(Extra.PARCEL_PROJECT);
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
        buildFormProject(view);
    }

    private void buildFormProject(View view) {
        projectForm = new ProjectForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, projectForm.getHolder());
    }

    @Override
    public void onStart() {
        super.onStart();

        bindFormWithProject();
    }

    private void bindFormWithProject() {
        projectForm.bind(project);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Extra.PARCEL_PROJECT, new ProjectParcel(project));
    }
}