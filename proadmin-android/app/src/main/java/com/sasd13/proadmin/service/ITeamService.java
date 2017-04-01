package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface ITeamService {

    List<Team> read(Map<String, String[]> parameters);

    void create(Team team);

    void update(TeamUpdateWrapper teamUpdateWrapper);

    void delete(Team team);
}
