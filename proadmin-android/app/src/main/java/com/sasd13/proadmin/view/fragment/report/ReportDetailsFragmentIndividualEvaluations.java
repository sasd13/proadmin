package com.sasd13.proadmin.view.fragment.report;

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

import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.scope.ReportScope;
import com.sasd13.proadmin.util.builder.member.StudentsFromStudentTeamsBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.view.IReportController;
import com.sasd13.proadmin.view.gui.form.IndividualEvaluationsForm;
import com.sasd13.proadmin.view.gui.form.IndividualEvaluationsFormException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ReportDetailsFragmentIndividualEvaluations extends Fragment implements Observer {

    private IReportController controller;
    private ReportScope scope;
    private IndividualEvaluationsForm individualEvaluationsForm;
    private Recycler recycler;
    private Menu menu;

    public static ReportDetailsFragmentIndividualEvaluations newInstance() {
        return new ReportDetailsFragmentIndividualEvaluations();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
        scope = (ReportScope) controller.getScope();

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
        buildFormIndividualEvaluations(view);
        bindFormWithIndividualEvaluations(scope.getIndividualEvaluations(), scope.getStudentTeams());
    }

    private void buildFormIndividualEvaluations(View view) {
        individualEvaluationsForm = new IndividualEvaluationsForm(getContext());

        recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void bindFormWithIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<StudentTeam> studentTeams) {
        individualEvaluationsForm.bindIndividualEvaluations(individualEvaluations, new StudentsFromStudentTeamsBuilder(studentTeams).build());
        RecyclerHelper.addAll(recycler, individualEvaluationsForm.getHolder());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.menu_edit_action_delete).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                updateIndividualEvaluations();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateIndividualEvaluations() {
        try {
            controller.actionUpdateIndividualEvaluations(getAllIndividualEvaluations());
        } catch (IndividualEvaluationsFormException e) {
            controller.display(e.getMessage());
        }
    }

    public Map<Class, List> getAllIndividualEvaluations() throws IndividualEvaluationsFormException {
        Map<Class, List> map = new HashMap<>();
        List<IndividualEvaluationUpdateWrapper> individualEvaluationsToUpdate = new ArrayList<>();
        List<IndividualEvaluation> individualEvaluationsToCreate = new ArrayList<>();

        Map<Student, Float> marks = individualEvaluationsForm.getMarks();
        boolean toCreate;

        for (Map.Entry<Student, Float> entry : marks.entrySet()) {
            toCreate = true;

            for (IndividualEvaluation individualEvaluation : scope.getIndividualEvaluations()) {
                if (individualEvaluation.getStudent().equals(entry.getKey())) {
                    toCreate = false;

                    individualEvaluationsToUpdate.add(getIndividualEvaluationUpdateWrapper(individualEvaluation, entry.getValue()));

                    break;
                }
            }

            if (toCreate) {
                individualEvaluationsToCreate.add(getIndividualEvaluation(entry.getKey(), entry.getValue()));
            }
        }

        map.put(IndividualEvaluationUpdateWrapper.class, individualEvaluationsToUpdate);
        map.put(IndividualEvaluation.class, individualEvaluationsToCreate);

        return map;
    }

    private IndividualEvaluationUpdateWrapper getIndividualEvaluationUpdateWrapper(IndividualEvaluation individualEvaluation, Float mark) {
        IndividualEvaluationUpdateWrapper individualEvaluationUpdateWrapper = new IndividualEvaluationUpdateWrapper();

        individualEvaluationUpdateWrapper.setReportNumber(individualEvaluation.getReport().getNumber());
        individualEvaluationUpdateWrapper.setStudentNumber(individualEvaluation.getStudent().getNumber());
        individualEvaluationUpdateWrapper.setWrapped(individualEvaluation);
        individualEvaluation.setMark(mark);

        return individualEvaluationUpdateWrapper;
    }

    private IndividualEvaluation getIndividualEvaluation(Student student, Float mark) {
        IndividualEvaluation individualEvaluation = new IndividualEvaluation();

        individualEvaluation.setReport(scope.getReport());
        individualEvaluation.setStudent(student);
        individualEvaluation.setMark(mark);

        return individualEvaluation;
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithIndividualEvaluations(scope.getIndividualEvaluations(), scope.getStudentTeams());
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