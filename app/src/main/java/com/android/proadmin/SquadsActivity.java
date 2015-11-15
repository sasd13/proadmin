package com.android.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import proadmin.constant.Extra;
import proadmin.beans.running.Team;
import proadmin.content.Year;
import proadmin.data.dao.DataAccessorManager;
import proadmin.data.dao.accessor.DataAccessor;
import proadmin.gui.color.ColorOnTouchListener;
import proadmin.gui.widget.recycler.tab.Tab;
import proadmin.gui.widget.recycler.tab.TabItemSquad;
import proadmin.gui.widget.recycler.tab.TabItemSquadTitle;
import proadmin.gui.widget.spin.Spin;

public class SquadsActivity extends ActionBarActivity {

    private DataAccessor dao = DataAccessorManager.getDao();
    private Tab tab;
    private Spin spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_squads);

        this.tab = new Tab(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.squads_recyclerview);
        this.tab.adapt(recyclerView);

        this.spin = new Spin(this);

        Spinner spinnerYears = (Spinner) findViewById(R.id.squads_spinner_year);
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadSquadsOfSelectedYear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        this.spin.adapt(spinnerYears, onItemSelectedListener);

        Button buttonNew = (Button) findViewById(R.id.squads_button_new);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SquadsActivity.this, SquadFormActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.MODE, Extra.MODE_NEW);

                startActivity(intent);
            }
        });
        buttonNew.setOnTouchListener(new ColorOnTouchListener(getResources().getColor(R.color.customGreen)));

        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        addYearsInSpinner();

        if (this.spin.size() > 0) {
            this.spin.setVisible(true);

            loadSquadsOfSelectedYear();
        } else {
            this.spin.setVisible(false);
        }
    }

    private void addYearsInSpinner() {
        this.spin.clearItems();

        this.dao.open();
        ListYears listYears = this.dao.selectYears();
        this.dao.close();

        for (Object year : listYears) {
            this.spin.addItem(year.toString());
        }

        this.spin.validate();
    }

    private void loadSquadsOfSelectedYear() {
        String stringYear = this.spin.getSelectedItem();
        Year year = new Year(Long.parseLong(stringYear));

        this.dao.open();
        ListSquads listSquads = this.dao.selectSquadsOfYear(year);
        this.dao.close();

        createTabForListProjects(year, listSquads);
    }

    private void createTabForListProjects(Year year, ListSquads listSquads) {
        this.tab.clearItems();
        this.tab.addItem(new TabItemSquadTitle());

        TabItemSquad itemSquad;
        Intent intent;

        this.dao.open();

        for (Object squad : listSquads) {
            itemSquad = new TabItemSquad();
            itemSquad.setName(((Team) squad).getName());
            itemSquad.setProjectTitle(((Team) squad).getProject().getTitle());

            intent = new Intent(this, SquadFormActivity.class);
            intent.putExtra(Extra.MODE, Extra.MODE_CONSULT);
            intent.putExtra(Extra.YEAR, year.toString());
            intent.putExtra(Extra.SQUAD_ID, ((Team) squad).getId());
            itemSquad.setIntent(intent);

            this.tab.addItem(itemSquad);
        }

        this.dao.close();
    }
}
