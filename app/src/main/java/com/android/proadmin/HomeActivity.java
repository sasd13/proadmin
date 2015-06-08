package com.android.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.view.View;

import com.example.proadmin.R;

public class HomeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.page_principale);
        /*
        ImageView imageViewGroup = (ImageView) findViewById(R.id.fen_groupe);
        ImageView imageViewProject = (ImageView) findViewById(R.id.fen_projet);
        ImageView imageViewReport = (ImageView) findViewById(R.id.fen_fiche);
        ImageView imageViewCalendar = (ImageView) findViewById(R.id.fen_calendar);

        OnClickListener monEcouteur = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fen_groupe:
                        Intent intentSurGroupe = new Intent(getApplicationContext(), GroupsActivity.class);
                        startActivity(intentSurGroupe);
                        break;
                    case R.id.fen_projet: // 2.a.b ...
                        Intent intentSurProjet = new Intent(getApplicationContext(), ProjectsActivity.class);
                        startActivity(intentSurProjet);
                        break;
                    case R.id.fen_fiche: // 2.a.c ...
                        Intent intentSurFiche = new Intent(getApplicationContext(), ReportsActivity.class);
                        startActivity(intentSurFiche);
                        break;
                }
            }
        };

        imageViewGroup.setOnClickListener(monEcouteur);
        imageViewProject.setOnClickListener(monEcouteur);
        imageViewReport.setOnClickListener(monEcouteur);
        imageViewCalendar.setOnClickListener(monEcouteur);
        */
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
}