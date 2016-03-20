package com.example.gestionprojetandroid;

import packDatabase.DAOBase;
import packEntite.Encadrant;
import packEntite.Securite;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Identification extends Activity {
	   
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
   
    public DAOBase db = new DAOBase(this);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
     
            final EditText login = (EditText) findViewById(R.id.editText_nomPrenomEncadrant);
            final EditText pass = (EditText) findViewById(R.id.editText_MotDePasseEncadrant);
            final Button loginButton = (Button) findViewById(R.id.btn_connex);
            final Button forgetButton = (Button) findViewById(R.id.btn_oublie_mdp);
            
            db.open();
            // lorsqu'on clique sur le bouton de validation
            loginButton.setOnClickListener(new OnClickListener() {
            	
				@Override
				public void onClick(View v) {
					AlertDialog alertDialog = new AlertDialog.Builder(Identification.this).create();
				
					Encadrant enc = db.selectEncadrant();
					Securite sec = db.selectSecurite();
					
					// Si les données ne correspondent pas à ceux de la base..
					if(!login.getText().toString().equals(enc.getNom() + " " + enc.getPrenom()) || !pass.getText().toString().equals(sec.getMotDePasse()))// On affiche une alertDialog si les données saisies sont inexactes
					{
						
						
						alertDialog.setTitle("SAISIES INCORRECTES");
						alertDialog.setMessage("Votre identité est inexacte\n Veuillez re-saisir vos informations");
						alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						      public void onClick(DialogInterface dialog, int which) {
						       //On ferme l'alertDialog
						    }
						 });
						alertDialog.show();
						
					}
					else if(login.getText().toString().equals(enc.getNom() + " " + enc.getPrenom()) || pass.getText().toString().equals(sec.getMotDePasse()))
					{
						// on quitte cette activité
						finish();
						// redirection vers l'activité accueil
						Intent intent = new Intent(Identification.this, MotherHome.class);
						startActivity(intent);
					}
					else
					{
						// On affiche un Toast demandant de s'identifier
						Toast.makeText(getApplicationContext(), "Identifiez-vous", Toast.LENGTH_SHORT).show();
					}
					
				}
            });	
         
            // lorsqu'on clique sur le bouton Oublié, lorsqu'on a oublié son mot de passe
            forgetButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
				
					
					/*
					Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
				            "mailto","brou.jean.philippe@gmail.com", null));
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
				startActivity(Intent.createChooser(emailIntent, "Send email..."));*/
					
				
					/*final Intent i = new Intent (android.content.Intent.ACTION_SEND);
					Intent emailText = new Intent (android.content.Intent.ACTION_SEND);
					Intent sujet = new Intent (android.content.Intent.ACTION_SEND);
					i.setType("message/rfc822");
					i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{address});
					i.putExtra(android.content.Intent.EXTRA_SUBJECT, sujet);
					i.putExtra(android.content.Intent.EXTRA_SUBJECT, emailText);
					Identification.this.startActivity(Intent.createChooser(i, "Test Android"));*/
				}
            });
            
            	
            }
            	
           
        }
     

