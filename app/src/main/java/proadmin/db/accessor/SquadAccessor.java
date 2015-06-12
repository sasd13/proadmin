package proadmin.db.accessor;

import proadmin.content.Id;
import proadmin.content.Squad;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SquadAccessor {

    boolean insertSquad(Squad squad);

    void updateSquad(Squad squad);

    void deleteSquad(Id squadId);

    Squad selectSquad(Id squadId);
}
