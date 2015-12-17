package com.example.gestionprojetandroid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import packDatabase.DAOBase;
import packEntite.Etudiant;
import packEntite.EtudiantSelect;
import packEntite.Groupe;
import packEntite.Projet;
import packUtil.AdapterListeEtudiants;
import packUtil.SpinnerTri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.BufferType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * <b>Classe pour l activite Groupes</b> * 
 * <p>cette activite enregistre et permet de consulter les groupes et les etudiants</p>
 * 
 * @author SAID ALI Samir, BROU Jean-Philippe, RAVIER Simon, JAMAL Hadi
 * 
 * @version 1.0
 */
public class Groupes extends Activity {


	/**
	 * Widgets
	 */
	public Spinner spinner_annee, spinner_annee_creation, spinner_projet;	
	public Button bouton_liste_groupes, bouton_nouveau_groupe, bouton_ajouter_etudiant, bouton_supprimer_etudiant, bouton_supprimer_groupe;
	public TextView text_nouveau_consultation_groupe;
	public EditText edit_idGroupe;	
	public ListView listView_groupe, listView_etudiants;
	
	/**
	 * Annee en cours
	 */
	public long annee_actuelle;
	
	/**
	 * Groupe Temporaire
	 */
	public Groupe groupe_temp;
	
	/**
	 * Liste temporaire des etudiants
	 */
	public ArrayList<Etudiant> liste_etudiants;
	
	/**
	 * Base de données
	 */
	public DAOBase db = new DAOBase(this);

	
	//----------------------------------------------------------------//
	//-------------------- CREATION DE L'ACTIVITE --------------------//
	//----------------------------------------------------------------//
	
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupes);
		
		
		/**
		 * Ouverture de la base
		 */
		this.db.open();	
		
		/**
		 *  Annee en cours
		 */
		Date date_actu = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy", Locale.FRENCH);
		this.annee_actuelle = Long.parseLong(formater.format(date_actu));

		/**
		 * Vérification de l'année en cours, si absente on l'ajoute dans la base
		 */
		if (this.db.selectKeyAnnee(this.annee_actuelle) == 0) this.db.ajouterAnnee(this.annee_actuelle);
		
		
		//-----------------------------------------------------------//
		//-------------------- INTERFACE GROUPES --------------------//
		//-----------------------------------------------------------//
		
		/**
		 * Bouton Liste
		 */
		this.bouton_liste_groupes = (Button) findViewById(R.id.groupe_bouton_liste);
		
		/**
		 * Ecouteur sur le bouton liste
		 */
		this.bouton_liste_groupes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Sauvegarde du groupe et des etudiants en cours
				sauvegarder();
				
				//Chargement du groupe temporaire vide et des etudiants vides
				charger(Groupes.this.annee_actuelle, "");
				
				//Affichage en mode liste
				findViewById(R.id.include_linearlayout_liste_groupes).setVisibility(View.VISIBLE);
				findViewById(R.id.include_linearlayout_nouveau_groupe).setVisibility(View.GONE);
				
				//Creation de la liste des groupes enregistrés
				long annee = Long.parseLong((String) Groupes.this.spinner_annee.getSelectedItem());
				lister(annee);
			}
		});
		
		/**
		 * Bouton Nouveau
		 */
		this.bouton_nouveau_groupe = (Button) findViewById(R.id.groupe_bouton_nouveau);
		
		/**
		 * Ecouteur sur le bouton nouveau
		 */
		this.bouton_nouveau_groupe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Sauvegarde du groupe et des etudiants en cours
				sauvegarder();
				
				//Chargement du groupe temporaire vide et des etudiants vides
				charger(Groupes.this.annee_actuelle, "");
				
				//Affichage en mode edition
				findViewById(R.id.include_linearlayout_liste_groupes).setVisibility(View.GONE);
				findViewById(R.id.include_linearlayout_nouveau_groupe).setVisibility(View.VISIBLE);
			}
		});
				
		
		//------------------------------------------------------------------//
		//-------------------- SECTION LISTE DE GROUPES --------------------//
		//------------------------------------------------------------------//
		
		/**
		 * Spinner annee
		 */
		this.spinner_annee = (Spinner) findViewById(R.id.groupe_spinner_annee);
		
		/**
		 * Selection des annees dans la base
		 */
		ArrayList<Long> liste_annee = this.db.selectAllAnnee();
				
		/**
		 * Tri de la liste des annees par ordre decroissant
		 */
		Collections.sort(liste_annee, new Comparator<Long>() {

			@Override
			public int compare(Long annee1, Long annee2) {
				if (annee1.longValue() > annee2.longValue()) return -1;
				else if (annee1.longValue() < annee2.longValue()) return 1;
				else return 0;
			}
		});
		
		ArrayList<String> liste_string_annee = new ArrayList<String>();
		for(int i=0; i<liste_annee.size(); i++) {
			liste_string_annee.add(String.valueOf(liste_annee.get(i).longValue()));
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
				
				//Creation de la liste des groupes enregistrés
				long annee = Long.parseLong((String) Groupes.this.spinner_annee.getSelectedItem());
				lister(annee);				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//DO NOTHING			
			}
		});
        		
		/**
		 * Creation de la ListView Groupe
		 */
		this.listView_groupe = (ListView) findViewById(R.id.listViewGroupe);
		
		/**
		 * Ecouteur sur la liste des groupes
		 */
		this.listView_groupe.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				//Map groupe selectionne 
				HashMap<String, String> map = (HashMap<String, String>) Groupes.this.listView_groupe.getAdapter().getItem(position);
				
				//Annee selectionne
				long annee = Long.parseLong((String) Groupes.this.spinner_annee.getSelectedItem());
				
				//Identifiant du groupe selectionne
				String idGroupe = map.get("listViewGroupe_idGroupe");				
				
				//Consultation du groupe
				charger(annee, idGroupe);				
			}
		});		
		
		
		//--------------------------------------------------------------------------------//
		//-------------------- SECTION NOUVEAU/CONSULTATION DE GROUPE --------------------//
		//--------------------------------------------------------------------------------//		
		
		/**
		 * TexteView creation/consultation
		 */
		this.text_nouveau_consultation_groupe = (TextView) findViewById(R.id.groupe_libelle_creationGroupes);
		
		/**
		 * Creation du groupe temporaire
		 */
		this.groupe_temp = new Groupe("identifiant", this.annee_actuelle, 0, 0);
		
		/**
		 * Spinner annee de creation
		 */
		this.spinner_annee_creation = (Spinner) findViewById(R.id.groupe_spinner_annee_creation);
		this.spinner_annee_creation.setEnabled(false);
		
		/**
		 * Liste contenant l'annee en cours
		 */
		ArrayList<String> string_annee_creation = new ArrayList<String>();
		string_annee_creation.add(String.valueOf(this.annee_actuelle));
		
		/**
		 * Ajout de l'annee en cours dans le spinner Annee Creation
		 */
		SpinnerTri spinner_tri_annee_creation = new SpinnerTri(this, this.spinner_annee_creation, string_annee_creation);
		
		/**
		 * Spinner projet de selection
		 */
		this.spinner_projet = (Spinner) findViewById(R.id.groupe_spinner_projet);
		
		/**
		 * Ecouteur sur le spinner projet
		 */
		this.spinner_projet.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				//Annee de creation
				long annee_creation = Long.parseLong((String) Groupes.this.spinner_annee_creation.getSelectedItem());
				
				//Identifiant projet selectionne
				String idProjet = (String) Groupes.this.spinner_projet.getItemAtPosition(position);
				
				//Projet correspondant
				ArrayList<Projet> liste_projets = Groupes.this.db.selectListeProjet(annee_creation);
				long projet_key = 0;
				for(int i=0; i<liste_projets.size(); i++) {
					if (liste_projets.get(i).getIdProjet().compareTo(idProjet) == 0) {
						projet_key = Groupes.this.db.selectKeyProjet(liste_projets.get(i).getTitre());
					}
				}
				//Modification du groupe temporaire
				Groupes.this.groupe_temp.setProjetKey(projet_key);	
				
				//verification de la cle du groupe
				long groupe_key = Groupes.this.db.selectKeyGroupe(annee_creation, Groupes.this.groupe_temp.getIdGroupe());
				
				//Si le groupe existe, on modifie le groupe dans la base
				if(groupe_key != 0) {
					Groupes.this.db.modifierGroupe(groupe_key, Groupes.this.groupe_temp);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//DO NOTHING				
			}
		});
				
		/**
		 * Selection des projets dans la base pour l'annee choisie
		 */
		ArrayList<Projet> liste_projets = this.db.selectListeProjet(this.annee_actuelle);
		
		if(liste_projets.isEmpty()) {
			
			AlertDialog.Builder adb = new AlertDialog.Builder(Groupes.this);
			adb.setMessage("Vous devez d'abord cr\351er les projets\n\nRedirection vers Projets...");
	        adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {

	            @Override
				public void onClick(DialogInterface arg0, int arg1) {
	            	
	            	//Redirection vers activite Projets
	            	Intent activity_projets = new Intent(getApplicationContext(), Projets.class);
	            	finish();
	            	startActivity(activity_projets);
				}					
	        });
            adb.show();	
            
			//Ajout de la liste des identifiants projets dans le spinner Projet
			SpinnerTri spinner_tri_projet = new SpinnerTri(this, this.spinner_projet, new ArrayList<String>());            
		}
		else {
			//Tri de la liste des projets par identifiant croissant
			Collections.sort(liste_projets, new Comparator<Projet>() {
					
				@Override
				public int compare(Projet p1, Projet p2) {
					if (p1.getIdProjet().compareTo(p2.getIdProjet()) < 0) return -1;
					else if (p1.getIdProjet().compareTo(p2.getIdProjet()) > 0) return 1;
					else return 0;
				}
			});
			
			//Liste des identifiants
			ArrayList<String> liste_idProjets = new ArrayList<String>();
			for(int i=0; i<liste_projets.size(); i++) {
				liste_idProjets.add(liste_projets.get(i).getIdProjet());
			}
			
			//Ajout de la liste des identifiants projets dans le spinner Projet
			SpinnerTri spinner_tri_projet = new SpinnerTri(this, this.spinner_projet, liste_idProjets);
		}
		
		/**
		 * EditText identifiant groupe
		 */
		this.edit_idGroupe = (EditText) findViewById(R.id.groupe_editText_idGroupe);
		
		/**
		 * Ecouteur action Edittext identifiant
		 */
		this.edit_idGroupe.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
				
				//Si on confirme le text
				if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getAction() == KeyEvent.ACTION_DOWN) && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
						
						//Selection de la cle du groupe
						long groupe_key = Groupes.this.db.selectKeyGroupe(Groupes.this.groupe_temp.getAnnee(), Groupes.this.groupe_temp.getIdGroupe());
						
						//Si l'EditText identifiant n'est pas vide, on modifie
						if(!((EditText) view).getText().toString().isEmpty()) Groupes.this.groupe_temp.setIdGroupe(((EditText) view).getText().toString());
						
						//Si le groupe existe
						if(groupe_key != 0) {
							
							//Modification du groupe dans la base
							Groupes.this.db.modifierGroupe(groupe_key, Groupes.this.groupe_temp);
						}
						else {
							
							String idProjet = (String) Groupes.this.spinner_projet.getSelectedItem();
							
							//Sinon on cree le groupe
							if ((Groupes.this.groupe_temp.getIdGroupe().compareTo("identifiant") != 0) && (Groupes.this.groupe_temp.getIdGroupe().contains(idProjet))){
								
								//Ajout du groupe dans la base
								Groupes.this.db.ajouterGroupe(Groupes.this.groupe_temp);
							}												
						}
						return true;
				}				
				return false;
			}
		});
		
		
		//---------------------------------------------------------------//
		//-------------------- SECTION DES ETUDIANTS --------------------//
		//---------------------------------------------------------------//		
		
		/**
		 * Creation de la liste temporaire des etudiants 
		 */
		this.liste_etudiants = new ArrayList<Etudiant>();
		
		/**
		 * ListView des etudiants
		 */
		this.listView_etudiants = (ListView) findViewById(R.id.listViewEtudiant);
		
		/**
		 * Bouton ajouter etudiant
		 */
		this.bouton_ajouter_etudiant = (Button) findViewById(R.id.groupe_bouton_ajouterEtudiant);
		
		/**
		 * Ecouteur sur le bouton ajouter Etudiant
		 */
		this.bouton_ajouter_etudiant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				listerEtudiants(false);				
			}
		});
		
		/**
		 * Bouton supprimer etudiant
		 */
		this.bouton_supprimer_etudiant = (Button) findViewById(R.id.groupe_bouton_supprimerEtudiant);
		
		/**
		 * Ecouteur sur le bouton supprimer etudiant
		 */
		this.bouton_supprimer_etudiant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				AlertDialog.Builder adb = new AlertDialog.Builder(Groupes.this);
				adb.setMessage("Vous retirez les etudiants s\351lection\351s du groupe\n\nConfirmer la suppression?");
		        adb.setNegativeButton("Non", null);
		        adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {

		            @Override
					public void onClick(DialogInterface arg0, int arg1) {
		            	
		            	long groupe_key = Groupes.this.db.selectKeyGroupe(Groupes.this.groupe_temp.getAnnee(), Groupes.this.groupe_temp.getIdGroupe());
	    				long etudiant_key;
	    				Etudiant etudiant;
	    				EtudiantSelect etudiant_select;
	    				Boolean selected;
	    				
		            	//Pour chaque etudiant
						for(int i=0; i<Groupes.this.listView_etudiants.getAdapter().getCount(); i++) {
							
							etudiant = Groupes.this.liste_etudiants.get(i);
							etudiant_select = (EtudiantSelect) Groupes.this.listView_etudiants.getAdapter().getItem(i);
	    					
	    					if(etudiant_select.getSelected()){
	    						
	    						etudiant_key = Groupes.this.db.selectKeyEtudiant(etudiant.getNomEtudiant(), etudiant.getPrenomEtudiant());
	    						
	    						if (etudiant_key != 0) Groupes.this.db.supprimerEtudiantGroupe(etudiant_key, groupe_key);
	    					}
						}
						
						//Rechargement de la liste des etudiants
						Groupes.this.liste_etudiants = Groupes.this.db.selectListeEtudiant(groupe_key);
						listerEtudiants(true);
					}					
		        });
		        adb.show();			
			}			
		});
		
		/**
		 * Bouton supprimer groupe
		 */
		this.bouton_supprimer_groupe = (Button) findViewById(R.id.groupe_bouton_supprimer);
		
		/**
		 * Ecouteur sur le bouton supprimer groupe
		 */
		this.bouton_supprimer_groupe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				long groupe_key = Groupes.this.db.selectKeyGroupe(Groupes.this.groupe_temp.getAnnee(), Groupes.this.groupe_temp.getIdGroupe());
				
				if(groupe_key != 0) {
				
					AlertDialog.Builder adb = new AlertDialog.Builder(Groupes.this);
					adb.setMessage("Attention!!! En supprimant le groupe, vous effacerez les fiches qui sont li\351es\n\nConfirmer la suppression?");
		            adb.setNegativeButton("Non", null);
		            adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {

		            	@Override
						public void onClick(DialogInterface arg0, int arg1) {
		            		
		            		long groupe_key = Groupes.this.db.selectKeyGroupe(Groupes.this.groupe_temp.getAnnee(), Groupes.this.groupe_temp.getIdGroupe());
		    						            		
		            		//Suppression du projet
							Groupes.this.db.supprimerGroupe(groupe_key);	
							
							//Chargement du groupe temporaire vide
							charger(Groupes.this.annee_actuelle, "");
							
							//Retour à la liste des groupes enregistrés
							long annee = Long.parseLong((String) Groupes.this.spinner_annee.getSelectedItem());
							lister(annee);
						}
		            	
		            });
		            adb.show();	
				}	
			}
		});	
		
		/**
		 * Creation de la liste des groupes de l'annee en cours
		 */
		lister(this.annee_actuelle);		
	}
	
	
	//--------------------------------------------------------------------//
	//-------------------- METHODE LISTER LES GROUPES --------------------//
	//--------------------------------------------------------------------//
	/**
	 * Methode pour lister les groupes
	 * 
	 * @param annee
	 */
	private void lister(long annee) {
		
		//Affichage en mode liste
		findViewById(R.id.include_linearlayout_liste_groupes).setVisibility(View.VISIBLE);
		findViewById(R.id.include_linearlayout_nouveau_groupe).setVisibility(View.GONE);
		
		//Selection des groupes de l'annee choisie
		ArrayList<Groupe> liste_groupes = this.db.selectListeGroupeAnnee(annee);
	       
		//Tri de la liste des groupes par identifiant croissant
		Collections.sort(liste_groupes, new Comparator<Groupe>() {
				
			@Override
			public int compare(Groupe g1, Groupe g2) {
				if (g1.getIdGroupe().compareTo(g2.getIdGroupe()) < 0) return -1;
				else if (g1.getIdGroupe().compareTo(g2.getIdGroupe()) > 0) return 1;
				else return 0;
			}
		});
			
		//Creation de la ArrayList qui nous permettra de remplire la listView
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	    
		//Creation d'une map pour chaque element
		HashMap<String, String> map;
		
		//Boucle pour la edition de la map contenant les informations
		for(int i=0; i<liste_groupes.size(); i++) {
			
			//Creation d'une HashMap pour inserer les informations du premier item de notre listView
			map = new HashMap<String, String>();
			
			//on insere l' identifiant du groupe
			map.put("listViewGroupe_idGroupe", liste_groupes.get(i).getIdGroupe());
			//on insere l'identifiant du projet
			map.put("listViewGroupe_idProjet", this.db.selectProjet(annee, liste_groupes.get(i).getProjetKey()).getIdProjet());
			//on insere le titre du projet dans la map
			map.put("listViewGroupe_nomProjet", this.db.selectProjet(annee, liste_groupes.get(i).getProjetKey()).getTitre());
			//on insere le nombre d'etudiants
			map.put("listViewGroupe_nbrEtudiants", String.valueOf(liste_groupes.get(i).getNbrEtudiant()));
			
			//enfin on ajoute cette hashMap dans la arrayList
			listItem.add(map);
		}
			
		//Creation d'un SimpleAdapter qui se chargera de mettre les items present dans notre liste (listItem)
			SimpleAdapter mSchedule = new SimpleAdapter (this.getApplicationContext(), listItem, R.layout.linearlayout_item_listview_groupe,
			      new String[] {"listViewGroupe_idGroupe", "listViewGroupe_idProjet", "listViewGroupe_nomProjet", "listViewGroupe_nbrEtudiants"}, 
			      new int[] {R.id.listViewGroupe_idGroupe, R.id.listViewGroupe_idProjet, R.id.listViewGroupe_nomProjet, R.id.listViewGroupe_nbrEtudiants});
	 
		//On attribue a notre listView l'adapter que l'on vient de creer
		this.listView_groupe.setAdapter(mSchedule);
	}
	
	
	//----------------------------------------------------------------------//
	//-------------------- METHODE LISTER LES ETUDIANTS --------------------//
	//----------------------------------------------------------------------//
	/**
	 * Methode pour lister les etudiants d un groupe
	 * 
	 * le parametre choix permet de choisir soit d afficher une liste vide pour un groupe qui n a pas d etudiant, soit d afficher les etudiant pour un groupe non vide
	 * 
	 * @param choix
	 */
	
	@SuppressWarnings("unused")
	private void listerEtudiants(boolean choix) {
		
		sauvegarder();
		
		int debut = 0;		
		if (choix == false) {
			debut = this.liste_etudiants.size();
			this.liste_etudiants.add(new Etudiant("nom", "prenom"));
		}
		
		ArrayList<EtudiantSelect> liste_etudiants_select = new ArrayList<EtudiantSelect>();
		Etudiant etudiant;
		for(int i=0; i<this.liste_etudiants.size(); i++) {
			etudiant = this.liste_etudiants.get(i);
			liste_etudiants_select.add(new EtudiantSelect(etudiant.getNomEtudiant(), etudiant.getPrenomEtudiant(), false));
		}
		
		//On cree l'adaptateur
		AdapterListeEtudiants adapter = new AdapterListeEtudiants(this, R.layout.linearlayout_item_listview_etudiant, this.liste_etudiants, liste_etudiants_select, this.groupe_temp, this.db);
	 
		//On attribue a notre listView l'adapter que l'on vient de creer
		this.listView_etudiants.setAdapter(adapter);
	}
	
	
	
	
	//-------------------------------------------------------------------//
	//-------------------- METHODE CHARGER UN GROUPE --------------------//
	//-------------------------------------------------------------------//
	/**
	 * Methode pour charger un groupe
	 * 
	 * @param annee
	 * @param idGroupe
	 */
	@SuppressWarnings("unused")
	private void charger(long annee, String idGroupe) {
				
		//Verification de la cle
		long groupe_key = this.db.selectKeyGroupe(annee, idGroupe);
		
		//Creation du groupe temporaire
		this.groupe_temp = new Groupe("identifiant", this.annee_actuelle, 0, 0);
		
		//creation de la liste temporaire des etudiants;
		this.liste_etudiants = new ArrayList<Etudiant>();
		
		//Si le goupe n'existe pas
		if (groupe_key == 0) {
			
			//Changement du texte Consultation en texte Creation
			this.text_nouveau_consultation_groupe.setText(R.string.libelle_creation_groupe);
						
			//Remplissage spinner annee de creation
			ArrayList<String> string_annee_creation = new ArrayList<String>();
			string_annee_creation.add(String.valueOf(this.annee_actuelle));
			SpinnerTri spinner_tri_annee_creation = new SpinnerTri(this, this.spinner_annee_creation, string_annee_creation);
			
			//Selection des projets dans la base pour l'annee actuelle
			ArrayList<Projet> liste_projets = this.db.selectListeProjet(this.annee_actuelle);
			
			//Tri de la liste des projets par identifiant croissant
			Collections.sort(liste_projets, new Comparator<Projet>() {
						
				@Override
				public int compare(Projet p1, Projet p2) {
					if (p1.getIdProjet().compareTo(p2.getIdProjet()) < 0) return -1;
					else if (p1.getIdProjet().compareTo(p2.getIdProjet()) > 0) return 1;
					else return 0;
				}
			});
				
			//Liste des identifiants
			ArrayList<String> liste_idProjets = new ArrayList<String>();
			for(int i=0; i<liste_projets.size(); i++) {
				liste_idProjets.add(liste_projets.get(i).getIdProjet());
			}
				
			//Ajout de la liste des identifiants projets dans le spinner Projet
			SpinnerTri spinner_tri_projet = new SpinnerTri(this, this.spinner_projet, liste_idProjets);
				
			//Modification de la cle projet dans le groupe temporaire
			this.groupe_temp.setProjetKey((long) this.db.selectKeyProjet(liste_projets.get(0).getTitre()));
			
			//EditText identifiant vide
			this.edit_idGroupe.setText("", BufferType.EDITABLE);
			
			//Liste des etudiants
			for(int i=0; i<4; i++) {
				this.liste_etudiants.add(new Etudiant("nom", "prenom"));
			}
			
			//Desactivation des boutons de suppression
			this.bouton_supprimer_etudiant.setEnabled(false);
			this.bouton_supprimer_groupe.setEnabled(false);
		}
		//Si le groupe existe
		else {
			
			//Affichage en mode edition
			findViewById(R.id.include_linearlayout_liste_groupes).setVisibility(View.GONE);
			findViewById(R.id.include_linearlayout_nouveau_groupe).setVisibility(View.VISIBLE);	
			
			//Changement du texte Création en texte Consultation
			this.text_nouveau_consultation_groupe.setText(R.string.libelle_consultation_groupe);
			
			//Sélection du groupe dans la base
			this.groupe_temp = this.db.selectGroupe(groupe_key);
			
			//Remplissage du spinner annee creation
			ArrayList<String> string_annee_creation = new ArrayList<String>();
			string_annee_creation.add(String.valueOf(this.groupe_temp.getAnnee()));
			SpinnerTri spinner_tri_annee_creation = new SpinnerTri(this, this.spinner_annee_creation, string_annee_creation);
			
			//Selection du projet associé au groupe dans la base
			Projet projet = this.db.selectProjet(annee, this.groupe_temp.getProjetKey());
			
			//Liste des projets de l'annee de creation du groupe
			ArrayList<Projet> liste_projets = this.db.selectListeProjet(this.groupe_temp.getAnnee());
			
			//Tri de la liste des projets par identifiant croissant
			Collections.sort(liste_projets, new Comparator<Projet>() {
					
				@Override
				public int compare(Projet p1, Projet p2) {
					if (p1.getIdProjet().compareTo(p2.getIdProjet()) < 0) return -1;
					else if (p1.getIdProjet().compareTo(p2.getIdProjet()) > 0) return 1;
					else return 0;
				}
			});
			
			//Position de l'identifiant du projet dans la liste triee
			int position = 0;
			
			//Liste des identifiants projets
			ArrayList<String> liste_idProjets = new ArrayList<String>();
			for(int i=0; i<liste_projets.size(); i++) {
				
				//Ajout de l'identifiant dans la liste
				liste_idProjets.add(liste_projets.get(i).getIdProjet());
				
				//Récupération de la position de l'identifiant du projet dans la liste
				if (projet.getIdProjet().compareTo(liste_projets.get(i).getIdProjet()) == 0) {
					position = i;
				}
			}
			
			//Remplissage du spinner projets
			SpinnerTri spinner_tri_projet = new SpinnerTri(this, this.spinner_projet, liste_idProjets);
			this.spinner_projet.setSelection(position);
			
			//Remplissage de l'identifiant
			this.edit_idGroupe.setText(this.groupe_temp.getIdGroupe(), BufferType.EDITABLE);
			
			//Selection des etudiants du groupe
			this.liste_etudiants = this.db.selectListeEtudiant(groupe_key);
			
			//Nombre d'etudiants
			this.groupe_temp.setNbrEtudiant(this.liste_etudiants.size());
			
			//Activation des boutons de suppression
			if (!this.liste_etudiants.isEmpty()) this.bouton_supprimer_etudiant.setEnabled(true);
			this.bouton_supprimer_groupe.setEnabled(true);
		}
		
		//creation du conteneur des etudiants
		listerEtudiants(true);
	}
	
	
	//-----------------------------------------------------------------------//
	//-------------------- METHODE SAUVEGARDER UN GROUPE --------------------//
	//-----------------------------------------------------------------------//
	/**
	 * Methode pour sauvegarder un groupe
	 */
	private void sauvegarder() {
		
		//Récupération de la clé du groupe à modifier
		long groupe_key = this.db.selectKeyGroupe(this.groupe_temp.getAnnee(), this.groupe_temp.getIdGroupe());
		
		//Reset du groupe temporaire (sauf l'annee et le projet)
		this.groupe_temp = new Groupe("identifiant", this.groupe_temp.getAnnee(), 0, this.groupe_temp.getProjetKey());
		
		//Identifiant groupe
		if (!this.edit_idGroupe.getText().toString().isEmpty()) this.groupe_temp.setIdGroupe(this.edit_idGroupe.getText().toString());
			
		//Si le groupe n'existe pas
		if (groupe_key == 0) {
			
			String idProjet = (String) this.spinner_projet.getSelectedItem();
			
			if((this.groupe_temp.getIdGroupe().compareTo("identifiant") != 0) && (Groupes.this.groupe_temp.getIdGroupe().contains(idProjet))) {
				
				//Ajout du groupe dansla base
				this.db.ajouterGroupe(this.groupe_temp);
				
				//Récupération de la clé du groupe
				groupe_key = this.db.selectKeyGroupe(this.annee_actuelle, this.groupe_temp.getIdGroupe());
			}			
		}
		
		//Modification des etudiants
		long etudiant_key = 0;
		
		ArrayList<Etudiant> liste_etudiant;
		
		if (!this.liste_etudiants.isEmpty()) {
			
			for(int i=0; i<this.liste_etudiants.size(); i++) {
				
				//Verification de la cle de l'etudiant
				etudiant_key = this.db.selectKeyEtudiant(this.liste_etudiants.get(i).getNomEtudiant(), this.liste_etudiants.get(i).getPrenomEtudiant());
				
				//On verifie la modification de l'etudiant temporaire, s'il n'y a pas eu de modification on ne fait rien
				if((this.liste_etudiants.get(i).getNomEtudiant().compareTo("nom") != 0) && (this.liste_etudiants.get(i).getPrenomEtudiant().compareTo("prenom") != 0)) {
						
					//Si l'etudiant existe
					if(etudiant_key != 0) {
						
						//on le modifie dans la base
						this.db.modifierEtudiant(etudiant_key, this.liste_etudiants.get(i));
						
					}
					//Sinon
					else {
						
						liste_etudiant = this.db.selectListeEtudiant(groupe_key);
						
						boolean go = true;
						for(int j=0; j<liste_etudiant.size(); j++) {
							if ((liste_etudiant.get(j).getNomEtudiant().compareTo(this.liste_etudiants.get(i).getNomEtudiant()) == 0) && (liste_etudiant.get(j).getPrenomEtudiant().compareTo(this.liste_etudiants.get(i).getPrenomEtudiant()) == 0)) {
								go = false;
							}
						}
						
						if (go == true) {
							//On cree l'etudiant
							this.db.ajouterEtudiant(groupe_key, this.liste_etudiants.get(i));
						}
					}
					
					//Nombre d'etudiants
					this.groupe_temp.setNbrEtudiant(this.groupe_temp.getNbrEtudiant() + 1);
				}			
			}
		}
		
		if (groupe_key != 0) {
			
			//Mise à jour du groupe dans la base 
			this.db.modifierGroupe(groupe_key, this.groupe_temp);
		}
	}
	
		
		//-----------------------------------------------------//
		//-------------------- MENU OPTION --------------------//
		//-----------------------------------------------------//
		/**
		 * Methode pour le menu
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