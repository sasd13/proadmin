package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.IWSPromise;
import com.sasd13.androidex.ws.rest.task.ReadTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.running.RunningsFragment;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningsService implements IWSPromise {

    private RunningsFragment runningsFragment;
    private Map<String, String[]> parameters;
    private ReadTask<Running> readTask;

    public RunningsService(RunningsFragment runningsFragment) {
        this.runningsFragment = runningsFragment;
        parameters = new HashMap<>();
    }

    public void readRunnings(Project project) {
        setParameters(project);

        readTask = new ReadTask<>(WSResources.URL_WS_RUNNINGS, this, Running.class);
        readTask.execute();
    }

    private void setParameters(Project project) {
        parameters.clear();
        parameters.put(
                EnumParameter.TEACHER.getName(),
                new String[]{ String.valueOf(SessionHelper.getExtraId(runningsFragment.getContext(), Extra.TEACHER_NUMBER)) });
        parameters.put(EnumParameter.PROJECT.getName(), new String[]{ String.valueOf(project.getCode()) });
    }

    public List<Running> readRunningsFromCache(Project project) {
        setParameters(project);

        return null;
    }

    @Override
    public void onLoad() {
        runningsFragment.onLoad();
    }

    @Override
    public void onSuccess() {
        try {
            List<Running> runnings = readTask.getResults();

            runningsFragment.onReadSucceeded(runnings);
        } catch (IndexOutOfBoundsException e) {
            runningsFragment.onError(R.string.ws_error_data_retrieval_error);
        }
    }

    @Override
    public void onFail(int i) {
        runningsFragment.onError(R.string.ws_error_server_connection_failed);
    }
}