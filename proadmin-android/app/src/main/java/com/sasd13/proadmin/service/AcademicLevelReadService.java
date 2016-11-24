package com.sasd13.proadmin.service;

import android.content.Context;

import com.sasd13.javaex.service.IReadServiceCaller;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.ws.rest.ReadRESTWebService;

import java.util.List;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class AcademicLevelReadService implements IReadServiceCaller<List<AcademicLevel>> {

    public class AcademicLevelReadWrapper {

        private List<AcademicLevel> academicLevels;

        public AcademicLevelReadWrapper(List<AcademicLevel> academicLevels) {
            this.academicLevels = academicLevels;
        }

        public List<AcademicLevel> getAcademicLevels() {
            return academicLevels;
        }
    }

    public interface AcademicLevelReadServiceCaller {

        void retrieve(AcademicLevelReadWrapper wrapper);
    }

    private AcademicLevelReadServiceCaller retriever;
    private ReadRESTWebService<AcademicLevel> readRESTWebService;

    public AcademicLevelReadService(Context context, Retriever retriever) {
        this.retriever = retriever;
        readRESTWebService = new ReadRESTWebService<>(context, WSResources.URL_WS_ACADEMICLEVELS, this, AcademicLevel.class);
    }

    public void readAcademicLevels() {
        readRESTWebService.read();
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onRead(List<AcademicLevel> academicLevels) {

    }

    @Override
    public void onError(String s) {

    }
}
