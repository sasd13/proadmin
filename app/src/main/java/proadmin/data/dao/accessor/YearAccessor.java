package proadmin.data.dao.accessor;

import proadmin.content.ListYears;
import proadmin.content.Year;

/**
 * Created by Samir on 11/06/2015.
 */
public interface YearAccessor {

    void deleteYear(Year year);

    ListYears selectYears();

    ListYears selectYearsOfProjectByDesc(String projectId);

    Year selectYearCreationOfProject(String projectId);
}
