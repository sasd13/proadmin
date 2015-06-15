package proadmin.gui.widget.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.android.proadmin.R;

/**
 * <b>AbstractRecycler is a container class for a squad of elements (AbstractRecyclerItem)</b>
 * <p>
 * A container has :
 * <ul>
 * <li>A context for the adaptor inflater (RecyclerAdapter)</li>
 * <li>A list of elements (AbstractRecycler!item) (</li>
 * <li>A layout resource (XML) for items</li>
 * </ul>
 * </p>
 *
 * Created by Samir on 13/03/2015.
 */
public abstract class AbstractRecycler {

    protected Context context;
    protected ListAbstractRecyclerItems listAbstractRecyclerItems;
    protected int itemStubLayout;

    protected AbstractRecycler(Context context) {
        this.context = context;
        this.listAbstractRecyclerItems = new ListAbstractRecyclerItems();
        this.itemStubLayout = R.layout.recyclerviewitem;
    }

    public boolean addItem(AbstractRecyclerItem abstractRecyclerItem) {
        return this.listAbstractRecyclerItems.add(abstractRecyclerItem);
    }

    public AbstractRecyclerItem removeItem(int index) {
        return this.listAbstractRecyclerItems.remove(index);
    }

    public AbstractRecyclerItem getItem(int index) {
        return this.listAbstractRecyclerItems.get(index);
    }

    public void clearItems() {
        this.listAbstractRecyclerItems.clear();
    }

    public abstract void adapt(RecyclerView recyclerView);
}