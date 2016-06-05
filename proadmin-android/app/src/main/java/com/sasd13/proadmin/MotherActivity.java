package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.drawer.Drawer;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItemNav;
import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.gui.nav.Nav;
import com.sasd13.proadmin.gui.nav.NavItem;

public abstract class MotherActivity extends AppCompatActivity {

    private static final int TIMEOUT = 2000;

    private Drawer drawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mother);

        createDrawer();
        fillDrawer();
    }

    @Override
    public void setContentView(int layoutResource) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activity_viewstub);
        viewStub.setLayoutResource(layoutResource);
        viewStub.inflate();
    }

    private void createDrawer() {
        drawer = new Drawer((RecyclerView) findViewById(R.id.drawer_recyclerview),
                (DrawerLayout) findViewById(R.id.drawer_drawerlayout));
    }

    private void fillDrawer() {
        Nav nav = Nav.getInstance(this);
        DrawerItemNav drawerItemNav;

        for (final NavItem navItem : nav.getItems()) {
            drawerItemNav = new DrawerItemNav();

            drawerItemNav.setColor(navItem.getColor());
            drawerItemNav.setLabel(navItem.getText());
            drawerItemNav.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClickOnRecyclerItem(RecyclerItem recyclerItem) {
                    navItem.getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(navItem.getIntent());
                }
            });

            drawer.addItem(drawerItemNav);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        drawer.setOpened(false);
    }

    public void logOut() {
        Session.clear();
        goToHomeActivityAndExit();
    }

    private void goToHomeActivityAndExit() {
        final WaitDialog waitDialog = new WaitDialog(this);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MotherActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.EXIT, true);

                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        }, TIMEOUT);

        taskPlanner.start();
        waitDialog.show();
    }
}
