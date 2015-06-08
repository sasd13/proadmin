package proadmin.gui.recycler.tab;

import android.view.View;
import android.view.ViewStub;

import com.example.proadmin.R;

import proadmin.gui.recycler.AbstractRecyclerItem;

/**
 * Created by Samir on 22/03/2015.
 */
public class TabItemTitle extends AbstractRecyclerItem {

    public TabItemTitle() {
        super(R.layout.tabitem_title);
    }

    @Override
    public View inflate(ViewStub viewStub) {
        return super.inflate(viewStub);
    }
}
