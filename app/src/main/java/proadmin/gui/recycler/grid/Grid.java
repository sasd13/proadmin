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
    public void adapt(RecyclerView recyclerView) {
        view = recyclerView;

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // add spaces item decoration
        int space = context.getResources().getDimensionPixelSize(R.dimen.grid_items_space);
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));

        int spanCount = context.getResources().getInteger(R.integer.grid_numcolumns);

        // use a grid layout manager
        //GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);

        // use a staggered grid layout manager
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerAdapter gridAdapter = new RecyclerAdapter(listAbstractRecyclerItems, itemStubLayout);
        recyclerView.setAdapter(gridAdapter);
    }
}
