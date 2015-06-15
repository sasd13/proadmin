package proadmin.data.dao.accessor;

import proadmin.content.ListYears;
import proadmin.content.Year;
import proadmin.content.id.ProjectId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface YearAccessor {

    void deleteYear(Year year);

    ListYears selectYears();

    Year selectYearCreationOfProject(ProjectId projectId);
}
