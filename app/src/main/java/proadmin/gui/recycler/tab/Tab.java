package proadmin.gui.recycler.tab;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import proadmin.gui.recycler.AbstractRecycler;
import proadmin.gui.recycler.RecyclerAdapter;

/**
 * <p>
 * Container class for Table views :
 * </p>
 * Created by Samir on 22/05/2015.
 */
public class Tab extends AbstractRecycler {

    public Tab(Context context) {
        super(context);
    }

    @Override
    public void adapt(RecyclerView recyclerView) {
        view = recyclerView;

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerAdapter drawerAdapter = new RecyclerAdapter(listAbstractRecyclerItems, itemStubLayout);
        recyclerView.setAdapter(drawerAdapter);
    }
}
