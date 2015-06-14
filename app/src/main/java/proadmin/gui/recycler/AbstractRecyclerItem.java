package proadmin.gui.recycler;

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

    public View inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(this.layoutResource);

        this.view = viewStub.inflate();

        return this.view;
    }

    public View getView() {
        return this.view;
    }
}
