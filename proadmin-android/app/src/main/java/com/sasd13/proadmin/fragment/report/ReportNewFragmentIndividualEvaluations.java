package com.sasd13.proadmin.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.fragment.IReportController;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsForm;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsFormException;
import com.sasd13.proadmin.util.builder.running.IndividualEvaluationsBuilder;
import com.sasd13.proadmin.util.wrapper.ReportNewWrapper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ReportNewFragmentIndividualEvaluations extends Fragment implements Observer {

    private IReportController controller;
    private ReportNewFragment parentFragment;
    private IndividualEvaluationsForm individualEvaluationsForm;
    private Recycler recycler;

    public static ReportNewFragmentIndividualEvaluations newInstance(ReportNewFragment parentFragment) {
        ReportNewFragmentIndividualEvaluations fragment = new ReportNewFragmentIndividualEvaluations();
        fragment.parentFragment = parentFragment;
        parentFragment.getReportNewWrapper().addObserver(fragment);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormIndividualEvaluations(view);
        buildFloatingActionButton(view);

        bindFormWithStudents(parentFragment.getReportNewWrapper().getStudentTeams());
    }

    private void buildFormIndividualEvaluations(View view) {
        individualEvaluationsForm = new IndividualEvaluationsForm(getContext());

        recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_save_white_48dp));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    editIndividualEvaluationsWithForm();
                    createReport();
                } catch (FormException e) {
                    controller.displayMessage(e.getMessage());
                }
            }
        });
    }

    private void editIndividualEvaluationsWithForm() throws IndividualEvaluationsFormException {
        List<IndividualEvaluation> individualEvaluationsFromForm = individualEvaluationsForm.getIndividualEvaluations();
        Report reportToCreate = parentFragment.getReportToCreate();

        reportToCreate.getIndividualEvaluations().clear();

        for (IndividualEvaluation individualEvaluationFromForm : individualEvaluationsFromForm) {
            individualEvaluationFromForm.setReport(reportToCreate);
            reportToCreate.getIndividualEvaluations().add(individualEvaluationFromForm);
        }
    }

    private void createReport() {
        parentFragment.createReport();
    }

    private void bindFormWithStudents(List<StudentTeam> studentTeams) {
        individualEvaluationsForm.bindIndividualEvaluations(new IndividualEvaluationsBuilder(studentTeams).build());
        RecyclerHelper.addAll(recycler, individualEvaluationsForm.getHolder());
    }

    @Override
    public void update(Observable observable, Object o) {
        ReportNewWrapper reportNewWrapper = (ReportNewWrapper) observable;

        bindFormWithStudents(reportNewWrapper.getStudentTeams());
    }
}