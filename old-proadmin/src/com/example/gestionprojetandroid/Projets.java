package com.example.gestionprojetandroid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import packDatabase.DAOBase;
import packEntite.Projet;
import packEntite.ProjetSelect;
import packUtil.AdapterListeProjets;
import packUtil.EcouteurEditorActionProjet;
import packUtil.SpinnerTri;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

/**
 * <b>Classe pour l activite Projets</b> * 
 * <p>cette activite enregistre et permet de consulter les projets</p>
 * 
 * @author SAID ALI Samir, BROU Jean-Philippe, RAVIER Simon, JAMAL Hadi
 * 
 * @version 1.0
 */
public class Projets extends Activity {
	
	TextView tv;
	/**
	 * Widgets
	 */
	public Spinner spinner_annee, spinner_annee_creation_gestion;
	public Button bouton_liste_projets, bouton_nouveau_projet, bouton_supprimer_projet, bouton_importer;
	public ListView listView_projet;
	public TextView text_nouveau_consultation_projet;
	public EditText edit_idProjet, edit_nomProjet, edit_description;
	public RadioGroup radioGroup_niveaux;
	public RadioButton radioButton_l1, radioButton_l2, radioButton_l3, radioButton_m1, radioButton_m2;
	
	/**
	 * Annee en cours
	 */
	public long annee_actuelle, annee_selected;
	
	/**
	 * Projet temporaire
	 */
	public Projet projet_temp;
	
	/**
	 * Base de données
	 */
	public DAOBase db = new DAOBase(this);		
	
	
	//----------------------------------------------------------------//
	//-------------------- CREATION DE L'ACTIVITE --------------------//
	//----------------------------------------------------------------//
	
	//@SuppressLint("ShowToast")
	@SuppressWarnings("unused")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projets);
				
		
		/**
		 * Ouverture de la base
		 */
		this.db.open();	
		
		/**
		 * Annee en cours
		 */
		Date date_actu = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy", Locale.FRENCH);
		this.annee_actuelle = Long.parseLong(formater.format(date_actu));
		this.annee_selected = Long.parseLong(formater.format(date_actu));

		/**
		 * Vérification de l'année en cours, si absente on l'ajoute dans la base
		 */
		if (this.db.selectKeyAnnee(this.annee_actuelle) == 0) this.db.ajouterAnnee(this.annee_actuelle);		
		
		//-----------------------------------------------------------//
		//-------------------- INTERFACE PROJETS --------------------//
		//-----------------------------------------------------------//	
				
		/**
		 * Bouton Liste
		 */
		this.bouton_liste_projets = (Button) findViewById(R.id.projet_bouton_liste);
		
		/**
		 * Ecouteur sur le bouton liste
		 */
		this.bouton_liste_projets.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Sauvegarde du projet temporaire en cours
				sauvegarder();
				
				//Chargement du projet temporaire vide
				charger("");
				
				//Affichage en mode liste
				findViewById(R.id.include_linearlayout_liste_projets).setVisibility(View.VISIBLE);
				findViewById(R.id.include_linearlayout_nouveau_projet).setVisibility(View.GONE);
				
				//Creation de la liste des projets
				lister(Projets.this.annee_selected);
			}
		});
		
		/**
		 * Bouton Nouveau
		 */
		this.bouton_nouveau_projet = (Button) findViewById(R.id.projet_bouton_nouveau);
		
		/**
		 * Ecouteur sur le bouton nouveau
		 */
		this.bouton_nouveau_projet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Sauvegarde du projet temporaire en cours
				sauvegarder();
				
				//Chargement du projet temporaire vide
				charger("");
				
				//Affichage en mode edition
				findViewById(R.id.include_linearlayout_liste_projets).setVisibility(View.GONE);
				findViewById(R.id.include_linearlayout_nouveau_projet).setVisibility(View.VISIBLE);
			}
		});
		
		
		//------------------------------------------------------------------//
		//-------------------- SECTION LISTE DE PROJETS --------------------//
		//------------------------------------------------------------------//
		
		/**
		 * Spinner annee
		 */
		this.spinner_annee = (Spinner) findViewById(R.id.projet_spinner_annee);	
		
		/**
		 * Selection des annees dans la base
		 */
		ArrayList<Long> liste_annees = this.db.selectAllAnnee();
		
		//Tri de la liste des annees par ordre decroissant
		Collections.sort(liste_annees, new Comparator<Long>() {

			@Override
			public int compare(Long annee1, Long annee2) {
				if (annee1.longValue() > annee2.longValue()) return -1;
				else if (annee1.longValue() < annee2.longValue()) return 1;
				else return 0;
			}
		});
		
		ArrayList<String> liste_string_annee = new ArrayList<String>();
		for(int i=0; i<liste_annees.size(); i++) {
			liste_string_annee.add(String.valueOf(liste_annees.get(i).longValue()));
		}
		
		/**
		 * Ajout de la liste des annees dans le spinner Annee
		 */
		SpinnerTri spinner_tri_annee = new SpinnerTri(this, this.spinner_annee, liste_string_annee);
		
		/**
		 * Ecouteur sur spinner Annee
		 */
		this.spinner_annee.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				//Creation de la liste des projets enregistrés
				long annee = Long.parseLong((String) Projets.this.spinner_annee.getSelectedItem());
				Projets.this.annee_selected = annee;
				lister(annee);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//DO NOTHING			
			}
		});		
		
		/**
		 * Creation de la ListView
		 */
		this.listView_projet = (ListView) findViewById(R.id.listViewProjet);
		
		//Ecouteur sur la liste des projets
		this.listView_projet.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				//Projet selectionne
				ProjetSelect projet_select = (ProjetSelect) parent.getItemAtPosition(position);
				
				//Nom du projet
				String nomProjet = projet_select.getTitre();			
				
				//Consultation du projet
				charger(nomProjet);				
			}
		});
		
		/**
		 * Bouton importer les projets
		 */
		this.bouton_importer = (Button) findViewById(R.id.projet_bouton_importer_projet);
		this.bouton_importer.setOnClickListener(new OnClickListener() {
		 
			@Override
			public void onClick(View v) {
				
				if (!((Button) v).getText().toString().contains("Annuler")) {
					AlertDialog.Builder adb = new AlertDialog.Builder(Projets.this);
					adb.setMessage("Voulez vous importer les projets s\351lectionn\351s \340 l'ann\351e en cours");
		            adb.setNegativeButton("Non", null);
		            adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {

		            	@Override
						public void onClick(DialogInterface arg0, int arg1) {
		            		
		    				long projet_key;
		    				ProjetSelect projet_select;
		    				
		    				for(int i=0;i<Projets.this.listView_projet.getAdapter().getCount();i++) {
		    					
		    					projet_select = (ProjetSelect) Projets.this.listView_projet.getAdapter().getItem(i);
		    					if(projet_select.getSelected()){
		    						
		    						projet_key = Projets.this.db.selectKeyProjet(projet_select.getTitre());		    						
		    						Projets.this.db.ajouterProjet(Projets.this.annee_actuelle, projet_select.getProjet());
		    					}
		    				}
		    				
		    				//Retour à la liste des projets enregistrés
							lister(Projets.this.annee_selected);
						}		            	
		            });
		            adb.show();	
				}
				else {
					AlertDialog.Builder adb = new AlertDialog.Builder(Projets.this);
					adb.setMessage("Attention!!! En supprimant les projets s\351lectionn\351s de l'ann\351e en cours, vous supprimez les groupes et les fiches de l'ann\351e\n\nConfirmer la suppression?");
		            adb.setNegativeButton("Non", null);
		            adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {

		            	@Override
						public void onClick(DialogInterface arg0, int arg1) {
		            		
		    				long projet_key;
		    				ProjetSelect projet_select;
		    				
		    				for(int i=0;i<Projets.this.listView_projet.getAdapter().getCount();i++) {
		    					
		    					projet_select = (ProjetSelect) Projets.this.listView_projet.getAdapter().getItem(i);
		    					if(projet_select.getSelected()){
		    						
		    						projet_key = Projets.this.db.selectKeyProjet(projet_select.getTitre());
		    						ArrayList<Long> liste_annee = Projets.this.db.selectListeAnnee(projet_key);
		    						
		    						if (liste_annee.size() > 1) Projets.this.db.supprimerProjetAnnee(Projets.this.annee_actuelle, projet_key);
		    					}
		    				}
							
							//Retour à la liste des projets enregistrés
							lister(Projets.this.annee_selected);
						}		            	
		            });
		            adb.show();	
				}
			}
		});
		
		
		//--------------------------------------------------------------------------------//
		//-------------------- SECTION NOUVEAU/CONSULTATION DE PROJET --------------------//
		//--------------------------------------------------------------------------------//		
		
		/**
		 * TexteView creation/consultation
		 */
		this.text_nouveau_consultation_projet = (TextView) findViewById(R.id.projet_libelle_creationProjet);
		
		/**
		 * Creation du projet temporaire
		 */
		this.projet_temp = new Projet("identifiant", "Licence 1", "Nom du projet", "description");
		
		/**
		 * Spinner Annee Creation Gestion
		 */
		this.spinner_annee_creation_gestion = (Spinner) findViewById(R.id.projet_spinner_annee_creation_gestion);
		this.spinner_annee_creation_gestion.setEnabled(false);
		
		/**
		 * Ecouteur sur l'action des EdiText
		 */
		EcouteurEditorActionProjet ecouteur_editText = new EcouteurEditorActionProjet(this.annee_selected, this.projet_temp, this.db);
		
		/**
		 * Nom du Projet
		 */
		this.edit_nomProjet = (EditText) findViewById(R.id.projet_editText_nomProjet);
		this.edit_nomProjet.setOnEditorActionListener(ecouteur_editText);
		
		/**
		 * Identifiant du Projet
		 */
		this.edit_idProjet = (EditText) findViewById(R.id.projet_editText_idProjet);
		this.edit_idProjet.setOnEditorActionListener(ecouteur_editText);
		
		/**
		 * Identifiant du Projet
		 */
		this.edit_description = (EditText) findViewById(R.id.projet_editText_description);
		this.edit_description.setOnEditorActionListener(ecouteur_editText);
		
		/**
		 * RadioGroup Niveaux
		 */
		this.radioGroup_niveaux = (RadioGroup) findViewById(R.id.projet_radioGroup_niveaux);
		
		/**
		 * Ecouteur sur les niveaux
		 */
		this.radioGroup_niveaux.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				
				//Verification de la cle du projet
				long projet_key = Projets.this.db.selectKeyProjet(Projets.this.projet_temp.getTitre());
				
				switch (arg1) {
				
					case R.id.projet_radioButton_licence1 : Projets.this.projet_temp.setNiveauProjet("Licence 1"); break;
						
					case R.id.projet_radioButton_licence2 : Projets.this.projet_temp.setNiveauProjet("Licence 2"); break;
						
					case R.id.projet_radioButton_licence3 : Projets.this.projet_temp.setNiveauProjet("Licence 3"); break;
						
					case R.id.projet_radioButton_master1 : Projets.this.projet_temp.setNiveauProjet("Master 1"); break;
						
					case R.id.projet_radioButton_master2 : Projets.this.projet_temp.setNiveauProjet("Master 2"); break;			
				}
				
				//Si le projet existe
				if (projet_key != 0) {
					
					//Mise à jour du projet dans la base
					Projets.this.db.modifierProjet(Projets.this.annee_selected, projet_key, Projets.this.projet_temp);
				}				
			}
		});
		
		/**
		 * RadioButton Licence 1
		 */
		this.radioButton_l1 = (RadioButton) findViewById(R.id.projet_radioButton_licence1);
		
		/**
		 * RadioButton Licence 2
		 */
		this.radioButton_l2 = (RadioButton) findViewById(R.id.projet_radioButton_licence2);
		
		/**
		 * RadioButton Licence 3
		 */
		this.radioButton_l3 = (RadioButton) findViewById(R.id.projet_radioButton_licence3);
		
		/**
		 * RadioButton Master 1
		 */
		this.radioButton_m1 = (RadioButton) findViewById(R.id.projet_radioButton_master1);
				
		/**
		 * RadioButton Master 2
		 */
		this.radioButton_m2 = (RadioButton) findViewById(R.id.projet_radioButton_master2);
		
		/**
		 * Bouton supprimer projet
		 */
		this.bouton_supprimer_projet = (Button) findViewById(R.id.projet_bouton_supprimer);
		
		/**
		 * Ecouteur sur le bouton supprimer projet
		 */
		this.bouton_supprimer_projet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(Projets.this.db.selectKeyProjet(Projets.this.projet_temp.getTitre()) != 0) {
					
					AlertDialog.Builder adb = new AlertDialog.Builder(Projets.this);
					adb.setMessage("Attention!!! En supprimant le projet, vous effacerez les groupes et les fiches qui sont li\351es\n\nConfirmer la suppression?");
		            adb.setNegativeButton("Non", null);
		            adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {

		            	@Override
						public void onClick(DialogInterface arg0, int arg1) {
		            		
		            		//Suppression du projet
							Projets.this.db.supprimerProjet(Projets.this.db.selectKeyProjet(Projets.this.projet_temp.getTitre()));	
							
							//Chargement du projet temporaire vide
							charger("");
							
							//Retour à la liste des projets enregistrés
							lister(Projets.this.annee_selected);
						}		            	
		            });
		            adb.show();	
				}	
			}
		});
		
		/**
		 * Creation de la liste des projets de l'année en cours
		 */
		lister(this.annee_actuelle);
	}
	
	
	//------------------------------------------------------------//
	//-------------------- LISTER LES PROJETS --------------------//
	//------------------------------------------------------------//
	/**
	 * Methode pour lister les projets
	 * 
	 * @param annee
	 */
	private void lister(long annee) {
		//Affichage en mode liste
		findViewById(R.id.include_linearlayout_liste_projets).setVisibility(View.VISIBLE);
		findViewById(R.id.include_linearlayout_nouveau_projet).setVisibility(View.GONE);
		
		//Selection des projets de l'annee choisie
		ArrayList<Projet> liste_projets = this.db.selectListeProjet(annee);
		   
		//Tri de la liste des projets par identifiant croissant
		Collections.sort(liste_projets, new Comparator<Projet>() {
				
			@Override
			public int compare(Projet p1, Projet p2) {
				if (p1.getIdProjet().compareTo(p2.getIdProjet()) < 0) return -1;
				else if (p1.getIdProjet().compareTo(p2.getIdProjet()) > 0) return 1;
				else return 0;
			}
		});
			
		//Creation de la ArrayList qui nous permettra de remplire la listView
		ArrayList<ProjetSelect> listItem = new ArrayList<ProjetSelect>();
	    
		ProjetSelect projet_select;
		String idProjet, niveauProjet, titre, description;
		boolean selected = false;
		
		//Boucle pour la edition de la map contenant les informations
		for(int i=0; i<liste_projets.size(); i++) {
			
			idProjet = liste_projets.get(i).getIdProjet();		
			niveauProjet = liste_projets.get(i).getNiveauProjet();
			titre = liste_projets.get(i).getTitre();
			description = liste_projets.get(i).getDescription();	
			
			if (description.length() > 33) description = description.substring(0, 30-3) + "...";
			
			//On instancie un element
			projet_select = new ProjetSelect(idProjet, niveauProjet, titre, description, selected);
			
			//enfin on ajoute cette hashMap dans la arrayList
			listItem.add(projet_select);
		}
		
		//On cree l'adaptateur
		AdapterListeProjets adapter = new AdapterListeProjets(this, R.layout.linearlayout_item_listview_projet, listItem);
	 
		//On attribue a notre listView l'adapter que l'on vient de creer
		this.listView_projet.setAdapter(adapter);
		
		//Desactivation du bouton annuler importation
		if (annee != this.annee_actuelle) this.bouton_importer.setText(R.string.importer);
		else this.bouton_importer.setText(R.string.annuler_importation);
	}
	
	
	//-------------------------------------------------------------------//
	//-------------------- METHODE CHARGER UN PROJET --------------------//
	//-------------------------------------------------------------------//
	/**
	 * Methode pour charger un projet
	 * @param titre
	 */
	@SuppressWarnings("unused")
	private void charger(String titre) {
				
		//Verification de la cle
		long projet_key = this.db.selectKeyProjet(titre);
		
		this.projet_temp = null;
		
		//Liste des annees du projet
		ArrayList<String> liste_annees_projet = new ArrayList<String>();
		
		//Si le projet n'existe pas
		if (projet_key == 0) {
			
			//Changement du texte Consultation en texte Creation
			this.text_nouveau_consultation_projet.setText(R.string.libelle_creation_projet);
			
			//Creation du projet temporaire vide
			this.projet_temp = new Projet("identifiant", "Licence 1", "Nom du projet", "description");
			
			//Remplissage de la liste des annees
			liste_annees_projet.add(String.valueOf(this.annee_actuelle));
			
			//Desactivation du spinner annee creation gestion
			this.spinner_annee_creation_gestion.setEnabled(false);
			
			//Selection du niveau Licence 1
			this.radioButton_l1.setChecked(true);	
			
			//Desactivation du bouton supprimer
			this.bouton_supprimer_projet.setEnabled(false);
		}
		else {
			
			//Affichage en mode edition
			findViewById(R.id.include_linearlayout_liste_projets).setVisibility(View.GONE);
			findViewById(R.id.include_linearlayout_nouveau_projet).setVisibility(View.VISIBLE);	
			
			//Changement du texte Création en texte Consultation
			this.text_nouveau_consultation_projet.setText(R.string.libelle_consultation_projet);
			
			//Annee choisie
			long annee = Long.parseLong((String) this.spinner_annee.getSelectedItem());	
			
			//Sélection du projet dans la base
			this.projet_temp = this.db.selectProjet(annee, projet_key);
			
			//Selection des annees du projet dans la base
			ArrayList<Long> liste_annees = this.db.selectListeAnnee(projet_key);
			
			//Tri de la liste des annees par ordre croissant
			Collections.sort(liste_annees, new Comparator<Long>() {

				@Override
				public int compare(Long annee1, Long annee2) {
					if (annee1.longValue() < annee2.longValue()) return -1;
					else if (annee1.longValue() > annee2.longValue()) return 1;
					else return 0;
				}
			});
			
			for(int i=0; i<liste_annees.size(); i++) {
				liste_annees_projet.add(String.valueOf(liste_annees.get(i).longValue()));
			}
			
			//Activation du spinner annee creation gestion
			this.spinner_annee_creation_gestion.setEnabled(true);
			
			//Selection du niveau
			HashMap<String, String> map_niveau = new HashMap<String, String>();			
			map_niveau.put("Licence 1", "1");
			map_niveau.put("Licence 2", "2");
			map_niveau.put("Licence 3", "3");
			map_niveau.put("Master 1", "4");
			map_niveau.put("Master 2", "5");
			
			switch(Integer.parseInt(map_niveau.get(this.projet_temp.getNiveauProjet()))) {
				
				case 1 : this.radioButton_l1.setChecked(true); break;					
				case 2 : this.radioButton_l2.setChecked(true); break;					
				case 3 : this.radioButton_l3.setChecked(true); break;					
				case 4 : this.radioButton_m1.setChecked(true); break;					
				case 5 : this.radioButton_m2.setChecked(true); break;
			}
			
			//Activation du bouton supprimer
			this.bouton_supprimer_projet.setEnabled(true);
		}	
		
		//Remplissage du Spinner Annee creation gestion
		SpinnerTri spinner_tri_annee_cg = new SpinnerTri(this, this.spinner_annee_creation_gestion, liste_annees_projet);
		
		//Remplissage des EditText
		if (this.projet_temp.getTitre().compareTo("Nom du projet") == 0) this.edit_nomProjet.setText("", BufferType.EDITABLE);
		else this.edit_nomProjet.setText(this.projet_temp.getTitre(), BufferType.EDITABLE);
		
		if (this.projet_temp.getIdProjet().compareTo("identifiant") == 0) this.edit_idProjet.setText("", BufferType.EDITABLE);
		else this.edit_idProjet.setText(this.projet_temp.getIdProjet(), BufferType.EDITABLE);
		
		if (this.projet_temp.getDescription().compareTo("description") == 0) this.edit_description.setText("", BufferType.EDITABLE);	
		else this.edit_description.setText(this.projet_temp.getDescription(), BufferType.EDITABLE);				
	}
	
	
	//-----------------------------------------------------------------------//
	//-------------------- METHODE SAUVEGARDER UN PROJET --------------------//
	//-----------------------------------------------------------------------//
	/**
	 * Methode pour sauvegarder un projet
	 */
	private void sauvegarder() {
		
		//Récupération de la clé du projet à modifier
		long projet_key = this.db.selectKeyProjet(this.projet_temp.getTitre());
		
		//Reset du projet temporaire (sauf le niveau)
		this.projet_temp = new Projet("identifiant", this.projet_temp.getNiveauProjet(), "Nom du projet", "description");
		
		//Mise à jour du projet temporaire
		if(!this.edit_nomProjet.getText().toString().isEmpty()) this.projet_temp.setTitre(this.edit_nomProjet.getText().toString());
		if(!this.edit_idProjet.getText().toString().isEmpty()) this.projet_temp.setIdProjet(this.edit_idProjet.getText().toString());
		if(!this.edit_description.getText().toString().isEmpty()) this.projet_temp.setDescription(this.edit_description.getText().toString());
				
		//Si le projet existe
		if (projet_key != 0) {
				
			//Annee choisie
			long annee = Long.parseLong((String) this.spinner_annee.getSelectedItem());			
				
			//Modification de l'identifiant pour l'année en cours
			this.db.modifierProjet(annee, projet_key, this.projet_temp);
		}
		else {
			
			//Si les EditText nom et identifiant sont vides
			if ((this.projet_temp.getTitre().compareTo("Nom du projet") != 0) && (this.projet_temp.getIdProjet().compareTo("identifiant") != 0)) {
				
				ArrayList<Projet> liste_projets = this.db.selectListeProjet(this.annee_actuelle);
				boolean go = true;
				for(int i=0; i<liste_projets.size(); i++) {
					if (liste_projets.get(i).getIdProjet().compareTo(this.projet_temp.getIdProjet()) == 0) {
						go = false;
					}
				}
				
				if (go == true) {
					
					//Ajout du projet dans la base
					this.db.ajouterProjet(this.annee_actuelle, this.projet_temp);
				}
			}
		}
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
