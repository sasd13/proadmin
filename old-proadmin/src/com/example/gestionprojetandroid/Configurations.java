package com.example.gestionprojetandroid;

import packDatabase.DAOBase;
import packEntite.Encadrant;
import packEntite.Securite;
import packUtil.EcouteurEditorActionConfiguration;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView.BufferType;

/**
 * <b>Classe pour l activite Configuration</b> * 
 * <p>cette activite enregistre les informations de l'encadrant, la securite et le demarrage de l application</p>
 * 
 * @author SAID ALI Samir, BROU Jean-Philippe, RAVIER Simon, JAMAL Hadi
 * 
 * @version 1.0
 */
public class Configurations extends Activity {
	
	/**
	 * Widgets
	 */
	public Button bouton_dftConfig, bouton_planning;
	public EditText edit_encadrant_nom, edit_encadrant_prenom, edit_encadrant_email, edit_securite_mdp, edit_securite_confirm_mdp;
	public RadioButton radio_securite_oui, radio_demarrage_bilan, radio_securite_non, radio_demarrage_accueil, radio_demarrage_projets, radio_demarrage_groupes, radio_demarrage_fiches, radio_demarrage_configurations;
	public RadioGroup radioGroup_securite, radioGroup_demarrage;
	
	/**
	 * Encadrant temporaire
	 */
	public Encadrant encadrant_temp = null;
	
	/**
	 * Securite temporaire
	 */
	public Securite securite_temp = null;
	
	/**
	 * Base de donnees
	 */
	public DAOBase db = new DAOBase(this);
	
	
	//----------------------------------------------------------------//
	//-------------------- CREATION DE L'ACTIVITE --------------------//
	//----------------------------------------------------------------//

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configurations);

		/**
		 * Ouverture de la base
		 */
		this.db.open();
				
		/**
		 * Bouton configuration par defaut
		 */
		this.bouton_dftConfig = (Button) findViewById(R.id.config_bouton_dftConfig);
		
		/**
		 * Ecouteur sur le bouton configration par defaut
		 */
		this.bouton_dftConfig.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				AlertDialog.Builder adb = new AlertDialog.Builder(Configurations.this);
				adb.setMessage("R\351initialiser la configuration ?");
                adb.setNegativeButton("Non", null);
                adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {
                    
                	@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
                		//Reinitialisation des donnees configuration dans la base
        				Configurations.this.db.resetToDftConfig();
        				
        				//Désactivation du mot de passe
        				Configurations.this.securite_temp.setActiverMdp(0);
        				Configurations.this.radio_securite_non.setChecked(true);
        				
        				//Remise du demarrage en accueil
        				Configurations.this.encadrant_temp.setDemarrageKey(1);
        				Configurations.this.radio_demarrage_accueil.setChecked(true);
					}
                 });
                adb.show();		
			}
		});	
		
		
		//-----------------------------------------------------------//
		//-------------------- SECTION ENCADRANT --------------------//
		//-----------------------------------------------------------//
		
		/**
		 * EditText nom encadrant
		 */
		this.edit_encadrant_nom = (EditText) findViewById(R.id.config_encadrant_nom);
			
		/**
		 * EditText prenom encadrant
		 */
		this.edit_encadrant_prenom = (EditText) findViewById(R.id.config_encadrant_prenom);
		
		/**
		 * EditText email encadrant
		 */
		this.edit_encadrant_email = (EditText) findViewById(R.id.config_encadrant_email);
		
				
		//----------------------------------------------------------//
		//-------------------- SECTION SECURITE --------------------//
		//----------------------------------------------------------//
		
		/**
		 * Création de la securité
		 */
		this.securite_temp = this.db.selectSecurite();
		
		/**
		 * RadioGroup securite
		 */
		this.radioGroup_securite = (RadioGroup) findViewById(R.id.config_securite_radioGroup);
		
		/**
		 * Ecouteur sur les boutons activer/desactiver securite
		 */
		this.radioGroup_securite.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				switch (arg1) {
				
					//Securite oui
					case R.id.config_securite_radioButton_oui :
						Configurations.this.securite_temp.setActiverMdp(1);						
						break;
				
					//Securite non
					case R.id.config_securite_radioButton_non :
						Configurations.this.securite_temp.setActiverMdp(0);
						break;
				}	
				
				//Modification de la securite dans la base
				Configurations.this.db.modifierSecurite(Configurations.this.securite_temp);
			}
		});
		
		/**
		 * Radio Oui
		 */
		this.radio_securite_oui = (RadioButton) findViewById(R.id.config_securite_radioButton_oui);
		
		/**
		 * Radio Non
		 */
		this.radio_securite_non = (RadioButton) findViewById(R.id.config_securite_radioButton_non);
		
		/**
		 * EditText mot de passe
		 */
		this.edit_securite_mdp = (EditText) findViewById(R.id.config_securite_mdp);
		
		/**
		 * EditText confirmer mot de passe
		 */
		this.edit_securite_confirm_mdp = (EditText) findViewById(R.id.config_securite_confirm_mdp);
		
		//Ecouteur sur les EditText
		EcouteurEditorActionConfiguration ecouteur_editText = new EcouteurEditorActionConfiguration(this.encadrant_temp, this.edit_securite_mdp, this.securite_temp, this.db);
		this.edit_encadrant_nom.setOnEditorActionListener(ecouteur_editText);
		this.edit_encadrant_prenom.setOnEditorActionListener(ecouteur_editText);
		this.edit_encadrant_email.setOnEditorActionListener(ecouteur_editText);
		this.edit_securite_confirm_mdp.setOnEditorActionListener(ecouteur_editText);
		
		
		//-----------------------------------------------------------//
		//-------------------- SECTION DEMARRAGE --------------------//
		//-----------------------------------------------------------//
		
		/**
		 * RadioGroup demarrage
		 */
		this.radioGroup_demarrage = (RadioGroup) findViewById(R.id.config_radioGroup_demarrage);
		
		/**
		 * Ecouteur sur les boutons choix de demarrage
		 */
		this.radioGroup_demarrage.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				
				switch (arg1) {
				
					//Demarrage ecran Accueil
					case R.id.config_demarrage_radioButton_accueil : 
						Configurations.this.encadrant_temp.setDemarrageKey(1); 
						break;
						
					//Demarrage ecran Projets
					case R.id.config_demarrage_radioButton_projets : 
						Configurations.this.encadrant_temp.setDemarrageKey(2); 
						break;
						
					//Demarrage ecran Groupes
					case R.id.config_demarrage_radioButton_groupes : 
						Configurations.this.encadrant_temp.setDemarrageKey(3); 
						break;
						
					//Demarrage ecran Fiches
					case R.id.config_demarrage_radioButton_fiches : 
						Configurations.this.encadrant_temp.setDemarrageKey(4); 
						break;
						
					//Demarrage ecran Configuration
					case R.id.config_demarrage_radioButton_config : 
						Configurations.this.encadrant_temp.setDemarrageKey(5); 
						break;			
						//Demarrage ecran Bialn
					case R.id.config_demarrage_rb_bilan : 
						Configurations.this.encadrant_temp.setDemarrageKey(6); 
						break;			
				}
					
				//Modification de l'encadrant dans la base
				Configurations.this.db.modifierEncadrant(Configurations.this.encadrant_temp);				
			}
		});
		
		/**
		 * RadioButton accueil
		 */
		this.radio_demarrage_accueil = (RadioButton) findViewById(R.id.config_demarrage_radioButton_accueil);
		
		/**
		 * RadioButton projets
		 */
		this.radio_demarrage_projets = (RadioButton) findViewById(R.id.config_demarrage_radioButton_projets);
		
		/**
		 * RadioButton groupes
		 */
		this.radio_demarrage_groupes = (RadioButton) findViewById(R.id.config_demarrage_radioButton_groupes);
		
		/**
		 * RadioButton fiches
		 */
		this.radio_demarrage_fiches = (RadioButton) findViewById(R.id.config_demarrage_radioButton_fiches);
		
		/**
		 * RadioButton config
		 */
		this.radio_demarrage_configurations = (RadioButton) findViewById(R.id.config_demarrage_radioButton_config);
		/**
		 * RadioButton bilan
		 */
		this.radio_demarrage_bilan= (RadioButton) findViewById(R.id.config_demarrage_rb_bilan);
		
		/**
		 * Chargement de la configuration
		 */
		charger();
	}
	
	
	//--------------------------------------------------------------------------//
	//-------------------- METHODE CHARGER LA CONFIGURATION --------------------//
	//--------------------------------------------------------------------------//
	
	/**
	 * Methode pour charger la configuration
	 */
	private void charger() {
		//Creation de l'encadrant temporaire
		this.encadrant_temp = this.db.selectEncadrant();
		
		//Remplissage de de section encadrant
		if (this.encadrant_temp.getNom().compareTo("nom") == 0) this.edit_encadrant_nom.setText("", BufferType.EDITABLE);
		else this.edit_encadrant_nom.setText(this.encadrant_temp.getNom(), BufferType.EDITABLE);
		
		if (this.encadrant_temp.getPrenom().compareTo("prenom") == 0) this.edit_encadrant_prenom.setText("", BufferType.EDITABLE);
		else this.edit_encadrant_prenom.setText(this.encadrant_temp.getPrenom(), BufferType.EDITABLE);
		
		if (this.encadrant_temp.getEmail().compareTo("encadrant@parisdescartes.fr") == 0) this.edit_encadrant_email.setText("", BufferType.EDITABLE);
		else this.edit_encadrant_email.setText(this.encadrant_temp.getEmail(), BufferType.EDITABLE);
		
		//Creation de la securite temporaire
		this.securite_temp = this.db.selectSecurite();
		
		//Remplissage de la section securite
		if (this.securite_temp.getActiverMdp() == 1) this.radio_securite_oui.setChecked(true);
		else this.radio_securite_non.setChecked(true);
		this.edit_securite_mdp.setText(this.securite_temp.getMotDePasse(), BufferType.EDITABLE);
		this.edit_securite_confirm_mdp.setText(this.securite_temp.getMotDePasse(), BufferType.EDITABLE);
		
		//Remplissage de la section demarrage
		switch(Integer.parseInt(String.valueOf(this.encadrant_temp.getDemarrageKey()))) {
			
			case 1 : this.radio_demarrage_accueil.setChecked(true); break;
				
			case 2 : this.radio_demarrage_projets.setChecked(true); break;
				
			case 3 : this.radio_demarrage_groupes.setChecked(true); break;
				
			case 4 : this.radio_demarrage_fiches.setChecked(true); break;
				
			case 5 : this.radio_demarrage_configurations.setChecked(true); break;
			
			case 6 : this.radio_demarrage_bilan.setChecked(true); break;			
		}		
	}
	
	
	//------------------------------------------------------------------------------//
	//-------------------- METHODE SAUVEGARDER LA CONFIGURATION --------------------//
	//------------------------------------------------------------------------------//
	/**
	 * Methode pour sauvegarder la configuration
	 */
	private void sauvegarder() {
		//Mise à jour de l encadrant temporaire
		if (!this.edit_encadrant_nom.getText().toString().isEmpty()) this.encadrant_temp.setNom(this.edit_encadrant_nom.getText().toString());
		if (!this.edit_encadrant_prenom.getText().toString().isEmpty()) this.encadrant_temp.setNom(this.edit_encadrant_nom.getText().toString());
		if (!this.edit_encadrant_email.getText().toString().isEmpty()) this.encadrant_temp.setNom(this.edit_encadrant_nom.getText().toString());
		
		//Choix de démarrage
		if (this.radio_demarrage_accueil.isChecked()) this.encadrant_temp.setDemarrageKey(1);
		else if (this.radio_demarrage_projets.isChecked()) this.encadrant_temp.setDemarrageKey(2);
		else if (this.radio_demarrage_groupes.isChecked()) this.encadrant_temp.setDemarrageKey(3);
		else if (this.radio_demarrage_fiches.isChecked()) this.encadrant_temp.setDemarrageKey(4);
		else if (this.radio_demarrage_configurations.isChecked()) this.encadrant_temp.setDemarrageKey(5);
		else if (this.radio_demarrage_bilan.isChecked()) this.encadrant_temp.setDemarrageKey(6);
		
		//Mise à jour de l'encadrant dans la base
		this.db.modifierEncadrant(this.encadrant_temp);
		
		//Mise à jour de la securite temporaire
		if (this.radio_securite_oui.isChecked()) this.securite_temp.setActiverMdp(1);
		else this.securite_temp.setActiverMdp(0);
		if (!this.edit_securite_confirm_mdp.getText().toString().isEmpty()) {
			this.securite_temp.setMotDePasse(this.edit_securite_confirm_mdp.getText().toString());
		}
		
		//Mise à jour de la securite dans la base
		this.db.modifierSecurite(this.securite_temp);		
	}
	
	//-----------------------------------------------------//
		//-------------------- MENU OPTION --------------------//
		//-----------------------------------------------------//
		/**
		 * Methode pour le menu option
		 */
		//M�thode qui se d�clenchera lorsque vous appuierez sur le bouton menu du t�l�phone
	    public boolean onCreateOptionsMenu(Menu menu) {
	 
	        //Cr�ation d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
	        MenuInflater inflater = getMenuInflater();
	        //Instanciation du menu XML sp�cifier en un objet Menu
	        inflater.inflate(R.layout.menu_autre, (android.view.Menu) menu);
			return true;
	     }

	       //M�thode qui se d�clenchera au clic sur un item
	      public boolean onOptionsItemSelected(MenuItem item) {
	         //On regarde quel item a �t� cliqu� gr�ce � son id et on d�clenche une action
	         switch (item.getItemId()) {
	         
			      case R.id.quitter:
			    	  System.exit(0);
			    	  return true;
			    	  
			      case R.id.accueil:
			    	  Intent iAccueil = new Intent(this, Accueil.class);
			    	  finish();
			    	  startActivity(iAccueil);
			    	  return true;
			    	  
			      case R.id.fiche:
			    	  Intent iFiche = new Intent(this, FicheEvaluation.class);
			    	  finish();
			    	  startActivity(iFiche);
			    	  return true;
			    	  
			      case R.id.projets:
			    	  Intent iProjets = new Intent(this, Projets.class);
			    	  finish();
			    	  startActivity(iProjets);
			    	  return true;
			    	  
			      case R.id.groupes:
			    	  Intent iGroupes = new Intent(this, Groupes.class);
			    	  finish();
			    	  startActivity(iGroupes);
			    	  return true;
			    	  
			      case R.id.bilan:
			    	  Intent iBilan = new Intent(this, Bilan.class);
			    	  finish();
			    	  startActivity(iBilan);
			    	  return true;
			    	  
			      case R.id.config:
			    	  Intent iConfig = new Intent(this, Configurations.class);
			    	  finish();
			    	  startActivity(iConfig);
			    	  return true;
			    }
			         
	         return false;
	         }
	//-------------------------------------------------------------------//
	//-------------------- REDEMARRAGE DE L'ACTIVITE --------------------//
	//-------------------------------------------------------------------//		
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		//Ouverture de la base
		this.db.open();
	}
	
	
	//-------------------------------------------------------------//
	//-------------------- PAUSE DE L'ACTIVITE --------------------//
	//-------------------------------------------------------------//
	
	@Override
	protected void onPause() {
		super.onPause();
		
		//Sauvegarde des données
		sauvegarder();
	}

	
	//-------------------------------------------------------------//
	//-------------------- ARRET DE L'ACTIVITE --------------------//
	//-------------------------------------------------------------//
	
	@Override
	protected void onStop() {
		super.onStop();
		
		//Sauvegarde des données
		sauvegarder();
		
		//Fermeture de la base
		this.db.close();
	}
	
}
