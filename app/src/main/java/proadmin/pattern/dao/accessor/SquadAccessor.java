package proadmin.pattern.dao.accessor;

import proadmin.content.Squad;
import proadmin.content.id.SquadId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SquadAccessor {

    void insertSquad(Squad squad);

    void updateSquad(Squad squad);

    void deleteSquad(SquadId squadId);

    Squad selectSquad(SquadId squadId);
}
