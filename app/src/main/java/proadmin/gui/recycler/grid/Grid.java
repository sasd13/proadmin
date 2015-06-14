package proadmin.gui.recycler.grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.proadmin.R;

import proadmin.gui.recycler.AbstractRecycler;
import proadmin.gui.recycler.RecyclerAdapter;

/**
 * <p>
 * Container class for grids :
 * </p>
 * Created by Samir on 13/03/2015.
 */
public class Grid extends AbstractRecycler {

    public Grid(Context context) {
        super(context);
    }

    @Override
    public void adapt(RecyclerView gridView) {
        view = gridView;

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        gridView.setHasFixedSize(true);

        // add spaces item decoration
        int space = context.getResources().getDimensionPixelSize(R.dimen.grid_items_space);
        gridView.addItemDecoration(new SpacesItemDecoration(space));

        int spanCount = context.getResources().getInteger(R.integer.grid_numcolumns);

        // use a grid layout manager
        //GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);

        // use a staggered grid layout manager
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);

        gridView.setLayoutManager(layoutManager);

        RecyclerAdapter gridAdapter = new RecyclerAdapter(listAbstractRecyclerItems, itemStubLayout);
        gridView.setAdapter(gridAdapter);
    }
}
