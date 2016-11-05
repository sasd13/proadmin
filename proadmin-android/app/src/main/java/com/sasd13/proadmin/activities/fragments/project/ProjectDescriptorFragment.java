package com.sasd13.proadmin.activities.fragments.project;

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
import com.sasd13.proadmin.form.ProjectForm;

public class ProjectDescriptorFragment extends Fragment {

    private Project project;
    private ProjectForm projectForm;

    public static ProjectDescriptorFragment newInstance(Project project) {
        ProjectDescriptorFragment projectFragment = new ProjectDescriptorFragment();
        projectFragment.project = project;

        return projectFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectForm = new ProjectForm(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.view_descriptor, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormProject(view);
        bindFormWithProject();
    }

    private void buildFormProject(View view) {
        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.descriptor_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, projectForm.getHolder());
    }

    private void bindFormWithProject() {
        projectForm.bind(project);
    }
}