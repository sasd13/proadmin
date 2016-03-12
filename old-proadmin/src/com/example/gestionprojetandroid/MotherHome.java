package com.example.gestionprojetandroid;

import packDatabase.DAOBase;
import packEntite.Encadrant;
import packEntite.Securite;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


/**
 * La Classe "MotherHome" permet de gérer les préférences de démarrage d'écran dans l'interface "configuration"
 * de l'Application
 * Par exemple: si l'utiloisateur choisit comme écran de démarrage un autre écran que l'accueil,
 *  cette classe lui retourne l'activité demandée.
 */
public class MotherHome extends Activity {
	
	public DAOBase db = new DAOBase(this);	
	/**
	 * Securite temporaire
	 */
	public Securite securite_temp = null;
	public EditText etN, etP, etE;

	
	// Lecture des données / CONFIGURATIONS / Demmarrage/ Checkbox coché
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_motherhome);
		
		db.open();
		Encadrant enc = db.selectEncadrant();
		
	
		// AlertDialog si pas d'encadrant enregistré
		
		if(enc.getNom().equals("nom") || enc.getPrenom().equals("prenom") || enc.getEmail().equals("encadrant@parisdescartes.fr"))
		{
			// Mettre à jour les valeurs
			final Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.alertdialog_perso);
			dialog.setTitle("Profil Encadrant");
			//((AlertDialog) dialog).setIcon(android.R.drawable.ic_dialog_alert);
				
			// set the custom dialog components - text, image and button
			//TextView text = (TextView) dialog.findViewById(R.id.text_consultation);
			//text.setText("Que voulez-vous consulter?");
			
			//
			etN = (EditText) dialog.findViewById(R.id.editText_saisirNom);
			etP = (EditText) dialog.findViewById(R.id.editText_saisirPrenom);
			etE = (EditText) dialog.findViewById(R.id.editText_saisirEmail);
			//
			
			Button btn_valider_profil = (Button) dialog.findViewById(R.id.btn_confirm_profil);
			btn_valider_profil.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					// Si les données sont saisies
					if(!etN.getText().toString().equals(" ") &&  !etP.getText().toString().equals(" ") && !etE.getText().toString().equals(" ") )
					{
						Encadrant enc1 = db.selectEncadrant();
						enc1.setNom(etN.getText().toString());
						enc1.setPrenom(etP.getText().toString());
						enc1.setEmail(etE.getText().toString());
						
						db.modifierEncadrant(enc1);
						
						dialog.dismiss();
						
						Intent intent = new Intent(getApplication(), Accueil.class);
						startActivity(intent);
						finish();
					}
					else
					{
						//ne rien faire
					}
				}
			});
			dialog.show();
		}
		else // Si un encadrant existe
		{
			switch((int) enc.getDemarrageKey())
			{
			
				case 1:
					Intent intentA = new Intent(getApplication(), Accueil.class);
					startActivity(intentA);
					finish();
					break;
				case 2:
					Intent intentB = new Intent(getApplication(), Projets.class);
					startActivity(intentB);
					finish();
					break;
				case 3:
					Intent intentC = new Intent(getApplication(), Groupes.class);
					startActivity(intentC);
					finish();
					break;
				case 4:
					Intent intentD = new Intent(getApplication(), FicheEvaluation.class);
					startActivity(intentD);
					finish();
					break;
				case 5:
					Intent intentE = new Intent(getApplication(), Configurations.class);
					startActivity(intentE);
					finish();
					break;
				case 6:
					Intent intentF = new Intent(getApplication(), Bilan.class);
					startActivity(intentF);
					finish();
					break;
					
					
			}
		
		}
		
}
}
	

