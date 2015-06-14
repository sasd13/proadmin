package proadmin.gui.recycler.tab;

import android.view.View;
import android.view.ViewStub;

import com.android.proadmin.R;

import proadmin.gui.recycler.AbstractRecyclerItem;

/**
 * Created by Samir on 22/03/2015.
 */
public class TabItemProjectTitle extends AbstractRecyclerItem {

    public TabItemProjectTitle() {
        super(R.layout.tabitem_project_title);
    }

    @Override
    public View inflate(ViewStub viewStub) {
        return super.inflate(viewStub);
    }
}
