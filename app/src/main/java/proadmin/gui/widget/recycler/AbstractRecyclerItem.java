package proadmin.gui.widget.recycler;

import android.view.View;
import android.view.ViewStub;

/**
 * Created by Samir on 22/03/2015.
 */
public abstract class AbstractRecyclerItem {

    protected int layoutResource;
    protected View view;

    protected AbstractRecyclerItem(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(this.layoutResource);

        this.view = viewStub.inflate();
    }
}
