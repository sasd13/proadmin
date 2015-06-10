package proadmin.gui.widget;

import android.content.Context;
import android.widget.ArrayAdapter;

import proadmin.content.Viewable;

/**
 * Created by Samir on 10/06/2015.
 */
public class SpinnerAdapter {

    private ArrayAdapter adapter;

    public SpinnerAdapter(Context context, Viewable viewable) {
        this.adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, viewable.getListSrings());
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
    }

    public ArrayAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(ArrayAdapter adapter) {
        this.adapter = adapter;
    }
}
