package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.ParameterizedReadTask;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.fragment.running.RunningsFragment;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.ws.WSInformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningsHandler implements IWSPromise {

    private RunningsFragment runningsFragment;
    private Map<String, String[]> parameters;
    private ParameterizedReadTask<Running> parameterizedReadTask;

    public RunningsHandler(RunningsFragment runningsFragment) {
        this.runningsFragment = runningsFragment;
        parameters = new HashMap<>();
    }

    public void readRunnings(Project project) {
        setParameters(project);

        parameterizedReadTask = new ParameterizedReadTask<>(Running.class, WSInformation.URL_RUNNINGS, parameters, this);
        parameterizedReadTask.setDeepReadEnabled(true);
        parameterizedReadTask.execute();
    }

    private void setParameters(Project project) {
        parameters.clear();
        parameters.put(
                EnumParameter.TEACHER.getName(),
                new String[]{ String.valueOf(SessionHelper.getExtraId(runningsFragment.getContext(), Extra.TEACHER_ID)) });
        parameters.put(EnumParameter.PROJECT.getName(), new String[]{ String.valueOf(project.getId()) });
    }

    public List<Running> readRunningsFromCache(Project project) {
        setParameters(project);

        return Cache.load(runningsFragment.getContext(), parameters, Running.class);
    }

    @Override
    public void onLoad() {
        runningsFragment.onLoad();
    }

    @Override
    public void onSuccess() {
        try {
            List<Running> runnings = parameterizedReadTask.getResults();

            Cache.keepAll(runningsFragment.getContext(), runnings);
            runningsFragment.onReadSucceeded(runnings);
        } catch (IndexOutOfBoundsException e) {
            runningsFragment.onError("Erreur de chargement des données");
        }
    }

    @Override
    public void onFail(int i) {
        runningsFragment.onError("Echec de la connexion au serveur");
    }
}