package proadmin.gui.widget.recycler.drawer;

import com.android.proadmin.R;

public class DrawerItemTitle extends DrawerItem {

    public DrawerItemTitle() {
        super(R.layout.draweritem_title);
    }

    @Override
    public void setText(CharSequence text) {
        super.setText(text.toString().toUpperCase());
    }
}
