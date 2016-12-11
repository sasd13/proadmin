package com.sasd13.proadmin.view.project;

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
import com.sasd13.proadmin.gui.form.ProjectForm;
import com.sasd13.proadmin.util.wrapper.ProjectWrapper;

public class ProjectDetailsFragmentInfos extends Fragment {

    private Project project;
    private ProjectForm projectForm;

    public static ProjectDetailsFragmentInfos newInstance(ProjectWrapper projectWrapper) {
        ProjectDetailsFragmentInfos fragment = new ProjectDetailsFragmentInfos();
        fragment.project = projectWrapper.getProject();

        return fragment;
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
        bindFormWithProject();
    }

    private void buildFormProject(View view) {
        projectForm = new ProjectForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, projectForm.getHolder());
    }

    private void bindFormWithProject() {
        projectForm.bind(project);
    }
}