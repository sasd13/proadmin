package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.service.IStudentService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentReadStrategy extends ReadRequestorStrategy {

    private TeamController controller;
    private IStudentService service;

    public StudentReadStrategy(TeamController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.onReadStudenTeams((List<StudentTeam>) out);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
