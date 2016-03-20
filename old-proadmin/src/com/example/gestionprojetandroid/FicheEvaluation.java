package com.example.gestionprojetandroid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import packDatabase.DAOBase;
import packEntite.Commentaire;
import packEntite.Etudiant;
import packEntite.Evaluation;
import packEntite.Fiche;
import packEntite.Groupe;
import packEntite.Note;
import android.view.View.OnClickListener;
import packUtil.AdapterListeNotes;
import packUtil.EcouteurEditorActionFiche;
import packUtil.EcouteurRatingBarFiche;
import packUtil.SpinnerTri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.BufferType;

/**
 * <b>Classe pour l activite Fiche d evaluation</b> * 
 * <p>cette activite enregistre et permet de consulter les fiches d evaluation</p>
 * 
 * @author SAID ALI Samir, BROU Jean-Philippe, RAVIER Simon, JAMAL Hadi
 * 
 * @version 1.0
 */
public class FicheEvaluation extends Activity {

	
	/**
	 * Widgets
	 */
	public TextView libelle_nomProjet;
	public ListView listView_fiche, listView_notes;
	public Spinner spinner_annee, spinner_semaine, spinner_chefProjet;
	public Button bouton_liste, bouton_archiver;
	public RatingBar bar_planning, bar_communication;
	public EditText edit_planning, edit_communication, edit_commentaire;
	
	/**
	 * Annee en cours
	 */
	public long annee_actuelle;
	
	/**
	 * Groupe temporaire
	 */
	public Groupe groupe_temp;
	
	/**
	 * Liste temporaire des etudiants
	 */
	public ArrayList<Etudiant> liste_etudiants;
	
	/**
	 * Fiche temporaire
	 */
	public Fiche fiche_temp; 
	
	/**
	 * Evaluation temporaire
	 */
	public Evaluation evaluation_temp;
	
	public ArrayList<Note> liste_notes ;
	
	/**
	 * Commentaire temporaire
	 */
	public Commentaire commentaire_temp;
	
	/**
	 * Base de donnees
	 */
	private DAOBase db = new DAOBase(this);
	
	
	//----------------------------------------------------------------//
	//-------------------- CREATION DE L'ACTIVITE --------------------//
	//----------------------------------------------------------------//

    @SuppressWarnings("unused")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_fiche_evaluation);
	    
	    
	    /**
	     * ouverture de la base
	     */
	    this.db.open();
	    
		/**
		 * Annee en cours
		 */
		Date date_actu = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy", Locale.FRENCH);
		this.annee_actuelle = Long.parseLong(formater.format(date_actu));

		/**
		 * Verification de l'année en cours, si absente on l'ajoute dans la base
		 */
		if (this.db.selectKeyAnnee(this.annee_actuelle) == 0) this.db.ajouterAnnee(this.annee_actuelle);
				
		
		//----------------------------------------------------------------//
		//-------------------- INTERFACE LISTE FICHES --------------------//
		//----------------------------------------------------------------//
		
	    /**
	     * Spinner Annee
	     */
	    this.spinner_annee = (Spinner) findViewById(R.id.fiche_spinner_annee);
	    
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
		 * Remplissage du Spinner
		 */
		SpinnerTri spinner_tri_annee = new SpinnerTri(this, this.spinner_annee, liste_string_annee);
		
		/**
		 * Ecouteur sur spinner Annee
		 */
		this.spinner_annee.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				//Creation de la liste des projets enregistrés
				long annee = Long.parseLong((String) FicheEvaluation.this.spinner_annee.getSelectedItem());
				lister(annee);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//DO NOTHING			
			}
		});    
		
		/**
		 * Verification de la liste des groupes
		 */
		ArrayList<Groupe> liste_groupes = this.db.selectListeGroupeAnnee(this.annee_actuelle);
		if (liste_groupes.isEmpty()) {
			
			AlertDialog.Builder adb = new AlertDialog.Builder(FicheEvaluation.this);
			adb.setMessage("Vous devez d'abord cr\351er les groupes\n\nRedirection vers Groupes...");
			adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
            	
					//Redirection vers activite bilan
					Intent activity_groupe = new Intent(getApplicationContext(), Groupes.class);
					finish();
					startActivity(activity_groupe);
				}					
			});
	        adb.show();	
		}
		
		/**
		 * Creation de la ListView Groupe
		 */
		this.listView_fiche = (ListView) findViewById(R.id.listViewFiche);
		
		//Ecouteur sur la liste des groupes
		this.listView_fiche.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				//Map groupe selectionne 
				HashMap<String, String> map = (HashMap<String, String>) FicheEvaluation.this.listView_fiche.getAdapter().getItem(position);
				
				//Annee selectionne
				long annee = Long.parseLong((String) FicheEvaluation.this.spinner_annee.getSelectedItem());
				
				//Identifiant du groupe selectionne
				String idGroupe = map.get("listViewFiche_idGroupe");		
				
				long groupe_key = FicheEvaluation.this.db.selectKeyGroupe(annee, idGroupe);
				
				//Modification du groupe temporaire
				FicheEvaluation.this.groupe_temp.setAnnee(annee);
				FicheEvaluation.this.groupe_temp.setIdGroupe(idGroupe);
				
				//Si la liste des etudiants du groupe est vide
				ArrayList<Etudiant> list_etudiants = FicheEvaluation.this.db.selectListeEtudiant(groupe_key);
				
				if (list_etudiants.isEmpty()) {
					
					AlertDialog.Builder adb = new AlertDialog.Builder(FicheEvaluation.this);
					adb.setMessage("Vous devez d'abord cr\351er les \351tudiants du groupe");
					adb.setPositiveButton("OK", null);
			        adb.show();	
				}
				else {
					
					// custom dialog
					final Dialog dialog = new Dialog(FicheEvaluation.this);
					dialog.setContentView(R.layout.dialog_fiche);
					dialog.setTitle("Choix de consultation");
		 
					// set the custom dialog components - text, image and button
					TextView text = (TextView) dialog.findViewById(R.id.text_consultation);
					text.setText("Que souhaitez-vous consulter?");
					
					Button bouton_fiche = (Button) dialog.findViewById(R.id.bouton_fiche);
					bouton_fiche.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							dialog.dismiss();
							
							//Annee selectionne
							long annee = FicheEvaluation.this.groupe_temp.getAnnee();
							
							//Identifiant du groupe selectionne
							String idGroupe = FicheEvaluation.this.groupe_temp.getIdGroupe();
							
							long groupe_key = FicheEvaluation.this.db.selectKeyGroupe(annee, idGroupe);
							
							//Modification du groupe temporaire
							FicheEvaluation.this.groupe_temp = FicheEvaluation.this.db.selectGroupe(groupe_key);
							
							//Liste des fiches
							ArrayList<Fiche> liste_fiches = FicheEvaluation.this.db.selectListeFiche(groupe_key);
							
							long numSemaine = 1;
							if (!liste_fiches.isEmpty()) {
								for(int i=liste_fiches.size()-1; i>=0; i--) {
									if (liste_fiches.get(i).getArchiver() == 1) {
										numSemaine = i+2; 
										if (numSemaine > 12) numSemaine = 12;
										break;
									}
								}
							}
							
							//Affichage en mode edition
							findViewById(R.id.include_linearlayout_liste_fiches).setVisibility(View.GONE);
							findViewById(R.id.include_linearlayout_nouveau_fiche).setVisibility(View.VISIBLE);
							
							//Consultation du groupe
							charger(annee, idGroupe, numSemaine);	
						}
					});
					
					Button bouton_bilan = (Button) dialog.findViewById(R.id.bouton_bilan);
					bouton_bilan.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							//Annee selectionne
							long annee = FicheEvaluation.this.groupe_temp.getAnnee();
							
							//Identifiant du groupe selectionne
							String idGroupe = FicheEvaluation.this.groupe_temp.getIdGroupe();
							
							long groupe_key = FicheEvaluation.this.db.selectKeyGroupe(annee, idGroupe);
							
							ArrayList<Fiche> liste_fiches = FicheEvaluation.this.db.selectListeFiche(groupe_key);
							
							if (liste_fiches.isEmpty()) {
								
								dialog.dismiss();
								
								AlertDialog.Builder adb = new AlertDialog.Builder(FicheEvaluation.this);
								adb.setMessage("Vous devez d'abord archiver les fiches d\'\351valuation du groupe");
								adb.setPositiveButton("OK", null);
						        adb.show();	
							}
							else {
								
								dialog.dismiss();
								
								Intent activity_bilan = new Intent(FicheEvaluation.this, Bilan.class);
								Bundle b = new Bundle();
								b.putLong("groupe_key", groupe_key); 
								activity_bilan.putExtras(b);
								startActivity(activity_bilan);
								finish();
							}
						}
					});	
					
					dialog.show();
				}
			}
		});		
	   
		
		//-------------------------------------------------------------------------------//
		//-------------------- SECTION NOUVEAU/CONSULTATION DE FICHE --------------------//
		//-------------------------------------------------------------------------------//
		
		/**
		 * Creation du groupe temporaire
		 */
		this.groupe_temp = new Groupe("identifiant", this.annee_actuelle, 0, 0);
		
		/**
		 * Creation de la liste temporaire des etudiants
		 */
		this.liste_etudiants = new ArrayList<Etudiant>();
		
	   	/**
	   	 * Creation de la fiche temporaire vide
	   	 */
	   	this.fiche_temp = new Fiche(0, 0, 0, 0);
	   	
	   	/**
	   	 * creation de l'evaluation temporaire
	   	 */
	   	this.evaluation_temp = new Evaluation(0, "", 0, "");
	   	
	   	this.liste_notes = new ArrayList<Note>();
	   	
	   	/**
	   	 * creation du commentaire temporaire
	   	 */
	   	this.commentaire_temp = new Commentaire("");
		
	    /**
	     * Spinner Semaine
	     */
	    this.spinner_semaine = (Spinner) findViewById(R.id.fiche_spinner_semaine);
	    
	    /**
	     * Liste Semaine
	     */
	   	ArrayList<String> liste_semaine = new ArrayList<String>();
	   	for(int i=1; i<=12; i++) { liste_semaine.add("Semaine "+i); }
	    		
	   	/**
	   	 * Liste des semaines dans le spinner semaine
	   	 */
	   	SpinnerTri spinner_tri_semaine = new SpinnerTri(this, this.spinner_semaine, liste_semaine);
	   	
	   	/**
	   	 * Ecouteur sur spinner semaine
	   	 */
	   	this.spinner_semaine.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				//Annee du groupe
				long annee = FicheEvaluation.this.groupe_temp.getAnnee();
				
				//Identifiant du groupe
				String idGroupe = FicheEvaluation.this.groupe_temp.getIdGroupe();
				
				//Semaine selectionnee
				String semaine = (String) FicheEvaluation.this.spinner_semaine.getSelectedItem();
				semaine = semaine.substring(semaine.length()-2, semaine.length());
				if(semaine.charAt(0) == ' ') semaine = semaine.substring(1);
				long numSemaine = Long.parseLong(semaine);
				
				//Chargement de la fiche
				charger(annee, idGroupe, numSemaine);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//DO NOTHING				
			}
		});
	   	
	   	/**
	   	 * Bouton archiver
	   	 */
	   	this.bouton_archiver = (Button) findViewById(R.id.fiche_bouton_archiver);
	   	
	   	//Ecouteur sur bouton valider
	   	this.bouton_archiver.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (((Button) v).getText().toString().compareTo("Archiver") == 0) {
					
					AlertDialog.Builder adb = new AlertDialog.Builder(FicheEvaluation.this);
					adb.setMessage("En archivant la fiche d\'\351valuation vous confirmez les modifications et la progression hebdomaire\n\nPassage � la semaine suivante...");
					adb.setNegativeButton("Non", null);
					adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
		            	
							//Modification de la fiche
							FicheEvaluation.this.fiche_temp.setArchiver(1);
							
							//Modification dans la base
							sauvegarder();
							
							//Bouton desactiver
							FicheEvaluation.this.bouton_archiver.setText(R.string.desarchiver);
							
							//Desactivation des widgets
							activerWidget(false);
							
							//Annee choisie
							long annee = FicheEvaluation.this.groupe_temp.getAnnee();
							
							//identifiant du groupe
							String idGroupe = FicheEvaluation.this.groupe_temp.getIdGroupe();
							
							//Semaine de la fiche en cours
							String semaine = (String) FicheEvaluation.this.spinner_semaine.getSelectedItem();
							semaine = semaine.substring(semaine.length()-2, semaine.length());
							if(semaine.charAt(0) == ' ') semaine = semaine.substring(1);
							long numSemaine = Long.parseLong(semaine);
							
							//Semaine suivante
							numSemaine++;
							
							//Si la semaine suivante est disponible
							if (numSemaine < 13) {
								
								//Fiche suivante
								charger(annee, idGroupe, numSemaine);
							}
							else {
								
								//Bilan du groupe
								long groupe_key = FicheEvaluation.this.db.selectKeyGroupe(annee, idGroupe);
								
								Intent activity_bilan = new Intent(FicheEvaluation.this, Bilan.class);
								Bundle b = new Bundle();
								b.putLong("groupe_key", groupe_key); 
								activity_bilan.putExtras(b);
								startActivity(activity_bilan);
								finish();
							}
						}					
					});
			        adb.show();	
				}
				else {
					
					AlertDialog.Builder adb = new AlertDialog.Builder(FicheEvaluation.this);
					adb.setMessage("D\351sarchiver la fiche d\'\351valuation pour apporter des modifications?");
					adb.setNegativeButton("Non", null);
					adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
		            	
							//Modification de la fiche
							FicheEvaluation.this.fiche_temp.setArchiver(0);
							
							//Modification dans la base
							long fiche_key = FicheEvaluation.this.db.selectKeyFiche(FicheEvaluation.this.fiche_temp.getGroupeKey(), FicheEvaluation.this.fiche_temp.getNumSemaine());
							FicheEvaluation.this.db.modifierFiche(fiche_key, FicheEvaluation.this.fiche_temp);
							
							//Bouton desactiver
							FicheEvaluation.this.bouton_archiver.setText(R.string.archiver);
							
							//Activation des widgets
							activerWidget(true);
						}					
					});
			        adb.show();	
				}
			}
		});
	   	
	   	/**
	   	 * Bouton liste
	   	 */
	   	this.bouton_liste = (Button) findViewById(R.id.fiche_bouton_liste);
	   	
	   	/**
	   	 * Ecouteur sur bouton liste
	   	 */
	   	this.bouton_liste.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Sauvegarde de la fiche en cours
				sauvegarder();
				
				//Chargement du groupe temporaire vide et des etudiants vides
				charger(FicheEvaluation.this.annee_actuelle, "", 0);
				
				//Affichage en mode liste
				findViewById(R.id.include_linearlayout_liste_fiches).setVisibility(View.VISIBLE);
				findViewById(R.id.include_linearlayout_nouveau_fiche).setVisibility(View.GONE);
				
				//Creation de la liste des groupes enregistrés
				long annee = Long.parseLong((String) FicheEvaluation.this.spinner_annee.getSelectedItem());
				lister(annee);				
			}
		});
	   	
	    /**
	     * TextView Libelle nom de projet
	     */
	    this.libelle_nomProjet = (TextView) findViewById(R.id.fiche_libelle_projet);	
	   	
	   	//Spinner Chef Projet 
	   	this.spinner_chefProjet = (Spinner) findViewById(R.id.fiche_spinner_chefProjet);
	   	
	   	//Ecouteur sur spinner chef de projet
	   	this.spinner_chefProjet.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				//Verification de la cle de la fiche
				long fiche_key = FicheEvaluation.this.db.selectKeyFiche(FicheEvaluation.this.fiche_temp.getGroupeKey(), FicheEvaluation.this.fiche_temp.getNumSemaine());
				
				int selected = FicheEvaluation.this.spinner_chefProjet.getSelectedItemPosition();
				
				//Selection de la cle de l'etudiant choisi
				long etudiant_key = FicheEvaluation.this.db.selectKeyEtudiant(FicheEvaluation.this.liste_etudiants.get(selected).getNomEtudiant(), FicheEvaluation.this.liste_etudiants.get(selected).getPrenomEtudiant());
				
				//Si l'etudiant existe 
				if(etudiant_key != 0) {
					
					//on modifie la fiche temporaire
					FicheEvaluation.this.fiche_temp.setChefProjet(etudiant_key);					
					
					//On modifie la fiche
					FicheEvaluation.this.db.modifierFiche(fiche_key, FicheEvaluation.this.fiche_temp);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//DO NOTHING				
			}
		});
	    
	   	/**
	   	 * Ecouteur sur le Rating Bar
	   	 */
	   	EcouteurRatingBarFiche ecouteur_rating = new EcouteurRatingBarFiche(this.fiche_temp, this.evaluation_temp, this.db);
	    
	   	/**
	   	 * RatingBar Planning
	   	 */
		this.bar_planning = (RatingBar) findViewById(R.id.fiche_ratingBar_planning);
		
		/**
		 * Ecouteur bar planning
		 */
		this.bar_planning.setOnRatingBarChangeListener(ecouteur_rating);
		
		/**
		 * RatingBar Communication
		 */
		this.bar_communication = (RatingBar) findViewById(R.id.fiche_ratingBar_communication);
		
		/**
		 * Ecouteur bar communication
		 */
		this.bar_communication.setOnRatingBarChangeListener(ecouteur_rating);
		
		//Ecouteur sur l'action des EditText
		EcouteurEditorActionFiche ecouteur_editText = new EcouteurEditorActionFiche(this.fiche_temp, this.evaluation_temp, this.commentaire_temp, this.db);
		
		/**
		 * EditText Planning
		 */
		this.edit_planning = (EditText) findViewById(R.id.fiche_editText_commentaires_planning);
		
		/**
		 * Ecoueteur sur EditText planning
		 */
		this.edit_planning.setOnEditorActionListener(ecouteur_editText);
		
		/**
		 * EditText Communication
		 */
		this.edit_communication = (EditText) findViewById(R.id.fiche_editText_commentaires_communication);
		
		/**
		 * Ecouteur sur EditText communication
		 */
		this.edit_communication.setOnEditorActionListener(ecouteur_editText);
		
		/**
		 * Liste des notes
		 */
		this.listView_notes = (ListView) findViewById(R.id.listViewNotes);
		
		/**
		 * EditText Commentaire
		 */
		this.edit_commentaire = (EditText) findViewById(R.id.fiche_editText_commentaire);
		
		/**
		 * Ecouteur sur EditText commentaire
		 */
		this.edit_commentaire.setOnEditorActionListener(ecouteur_editText);
		
		/**
		 * Liste 
		 */
		lister(this.annee_actuelle);
    }
    
    
	//-------------------------------------------------------------------//
	//-------------------- METHODE LISTER LES FICHES --------------------//
	//-------------------------------------------------------------------//
	/**
	 * Methode pour lister les fiches groupe et acceder aux fiches d evaluation
	 * @param annee
	 */
	private void lister(long annee) {
		//Affichage en mode liste
		findViewById(R.id.include_linearlayout_liste_fiches).setVisibility(View.VISIBLE);
		findViewById(R.id.include_linearlayout_nouveau_fiche).setVisibility(View.GONE);
		
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
		
		//Liste des fiches
		ArrayList<Fiche> liste_fiches;
		
		int progression;
		
		//Boucle pour la edition de la map contenant les informations
		for(int i=0; i<liste_groupes.size(); i++) {
			
			progression = 0;
			
			//liste des fiches
			liste_fiches = this.db.selectListeFiche(this.db.selectKeyGroupe(liste_groupes.get(i).getAnnee(), liste_groupes.get(i).getIdGroupe()));
			
			for(int j=0; j<liste_fiches.size(); j++) {
				if (liste_fiches.get(j).getArchiver() == 1) progression++;
			}
			//Creation d'une HashMap pour inserer les informations du premier item de notre listView
			map = new HashMap<String, String>();
			
			//on insere l' identifiant du groupe
			map.put("listViewFiche_idGroupe", liste_groupes.get(i).getIdGroupe());
			//on insere l'identifiant du projet
			map.put("listViewFiche_idProjet", this.db.selectProjet(annee, liste_groupes.get(i).getProjetKey()).getIdProjet());
			//on insere le titre du projet dans la map
			map.put("listViewFiche_nomProjet", this.db.selectProjet(annee, liste_groupes.get(i).getProjetKey()).getTitre());
			//on insere le nombre d'etudiants
			map.put("listViewFiche_progression", String.valueOf(progression));
			
			//enfin on ajoute cette hashMap dans la arrayList
			listItem.add(map);
		}
			
		//Creation d'un SimpleAdapter qui se chargera de mettre les items present dans notre liste (listItem)
			SimpleAdapter adapter = new SimpleAdapter (this.getApplicationContext(), listItem, R.layout.linearlayout_item_listview_fiche,
			      new String[] {"listViewFiche_idGroupe", "listViewFiche_idProjet", "listViewFiche_nomProjet", "listViewFiche_progression"}, 
			      new int[] {R.id.listViewFiche_idGroupe, R.id.listViewFiche_idProjet, R.id.listViewFiche_nomProjet, R.id.listViewFiche_progression});
	 
		//On attribue a notre listView l'adapter que l'on vient de creer
		this.listView_fiche.setAdapter(adapter);
	}
	
	
	//----------------------------------------------------------//
	//-------------------- LISTER LES NOTES --------------------//
	//----------------------------------------------------------//
	
	/**
	 * Methode pour lister les notes dans une fiche d evaluation
	 */
	private void listerNotes() {
		
        AdapterListeNotes dataAdapter = new AdapterListeNotes(this, R.layout.linearlayout_item_listview_notes, this.liste_notes, this.fiche_temp, this.db);
        
		//On attribue a notre listView l'adapter que l'on vient de creer
		this.listView_notes.setAdapter(dataAdapter);
	}
	
	
	//-----------------------------------------------------------------//
	//-------------------- METHODE ACTIVER WIDGETS --------------------//
	//-----------------------------------------------------------------//
	/**
	 * Methode pour activer ou desactiver les widgets de la fiche
	 * 
	 * @param active
	 */
	private void activerWidget(boolean active) {
		this.spinner_chefProjet.setEnabled(active);
		this.bar_planning.setEnabled(active);
		this.edit_planning.setEnabled(active);
		this.bar_communication.setEnabled(active);
		this.edit_communication.setEnabled(active);
		if (active == true) this.listView_notes.setVisibility(View.VISIBLE);
		else this.listView_notes.setVisibility(View.INVISIBLE);
		this.edit_commentaire.setEnabled(active);
	}
	
	
	//-------------------------------------------------------------------//
	//-------------------- METHODE CHARGER UNE FICHE --------------------//
	//-------------------------------------------------------------------//
	/**
	 * Methode pour charger une fiche d evaluation
	 * 
	 * @param annee
	 * @param idGroupe
	 * @param numSemaine
	 */
	@SuppressWarnings("unused")
	private void charger(long annee, String idGroupe, long numSemaine) {
		
		//Creation du groupe temporaire
		this.groupe_temp = new Groupe(idGroupe, annee, 0, 0);
			
		//Creation de la fiche temporaire vide
		this.fiche_temp = new Fiche(0, 0, 0, 0);
		   	
		//creation de l'evaluation temporaire vide
		this.evaluation_temp = new Evaluation(0, "", 0, "");
		
		//Sélection de la liste des etudiants
		this.liste_etudiants = new ArrayList<Etudiant>();
		   	
		//creation du commentaire temporaire vide
		this.commentaire_temp = new Commentaire("");
		
		//Selection de la cle du groupe
		long groupe_key = this.db.selectKeyGroupe(annee, idGroupe);
		
		if (groupe_key != 0) {
			
			//Creation du groupe temporaire
			this.groupe_temp = this.db.selectGroupe(groupe_key);
				
			//Creation de la fiche temporaire vide
			this.fiche_temp.setGroupeKey(groupe_key);
			   	
			//Sélection de la liste des etudiants
			this.liste_etudiants = this.db.selectListeEtudiant(groupe_key);
			   	
			//Liste des fiches
			ArrayList<Fiche> liste_fiches = this.db.selectListeFiche(groupe_key);
				
			if (liste_fiches.isEmpty()) {
					
				long chefProjet_key = this.db.selectKeyEtudiant(this.liste_etudiants.get(0).getNomEtudiant(), this.liste_etudiants.get(0).getPrenomEtudiant());
				long etudiant_key;

				this.fiche_temp.setChefProjet(chefProjet_key);
					
				for(int i=1; i<=12; i++) {
						
					this.fiche_temp.setNumSemaine(Long.parseLong(String.valueOf(i)));
					this.liste_notes = new ArrayList<Note>();
					
					for(int j=0; j<this.liste_etudiants.size(); j++) {
						etudiant_key = this.db.selectKeyEtudiant(this.liste_etudiants.get(j).getNomEtudiant(), this.liste_etudiants.get(j).getPrenomEtudiant());
						this.liste_notes.add(new Note(0, etudiant_key));
					}
						
					this.db.ajouterFiche(this.fiche_temp, this.evaluation_temp, this.liste_notes, this.commentaire_temp);
				}
			}
						
			//Affichage du nom du projet
			String nomProjet = this.db.selectProjet(annee, this.db.selectGroupe(groupe_key).getProjetKey()).getTitre();
			this.libelle_nomProjet.setText(nomProjet);
					
			//Creation d'une liste de String des etudiants pour le spinner chefProjet
			ArrayList<String> liste_string_etudiants = new ArrayList<String>();				
			for(int i=0; i<this.liste_etudiants.size(); i++) {
				liste_string_etudiants.add(this.liste_etudiants.get(i).getNomEtudiant() + " " + this.liste_etudiants.get(i).getPrenomEtudiant());
			}
					
			//Remplissage spinner chef de pojet
			SpinnerTri spinner_tri_chefProjet = new SpinnerTri(this, this.spinner_chefProjet, liste_string_etudiants);
					
			//Choix de la semaine
			this.spinner_semaine.setSelection(Integer.parseInt(String.valueOf(numSemaine-1)));
						
			//verification de la cle de la fiche
			long fiche_key = this.db.selectKeyFiche(groupe_key, numSemaine);
			
			if (fiche_key != 0) {
				
				//Selection de la fiche
				this.fiche_temp = this.db.selectFiche(groupe_key, numSemaine);
					
				//Selection du chef de projet
				Etudiant chef_projet = this.db.selectEtudiant(this.fiche_temp.getChefProjet());				
				int position = 0;
				for(int i=0; i<this.liste_etudiants.size(); i++) {
					
					if ((this.liste_etudiants.get(i).getNomEtudiant().compareTo(chef_projet.getNomEtudiant()) == 0) && (this.liste_etudiants.get(i).getPrenomEtudiant().compareTo(chef_projet.getPrenomEtudiant()) == 0)) {
						position = i;
						break;
					}
				}
				this.spinner_chefProjet.setSelection(position);
									
				//Selection de l'evaluation
				this.evaluation_temp = this.db.selectEvaluation(fiche_key);		
						
				//Remplissage du ratingBar planning
				this.bar_planning.setRating(Float.parseFloat(String.valueOf(this.evaluation_temp.getNotePLanning())));
				this.bar_planning.setIsIndicator(false);
					
				//Remplissage du commentaire planning
				if (this.evaluation_temp.getCommentairePlanning().isEmpty()) this.edit_planning.setText("", BufferType.EDITABLE);
				else this.edit_planning.setText(this.evaluation_temp.getCommentairePlanning(), BufferType.EDITABLE);
									
				//Remplissage ratingBar communication
				this.bar_communication.setRating(Float.parseFloat(String.valueOf(this.evaluation_temp.getNoteCommunication())));
				this.bar_communication.setIsIndicator(false);
					
				//remplissage du commentaire communication
				if (this.evaluation_temp.getCommentaireCommunication().isEmpty()) this.edit_communication.setText("", BufferType.EDITABLE);
				this.edit_communication.setText(this.evaluation_temp.getCommentaireCommunication(), BufferType.EDITABLE);
					
				//Liste des notes
				this.liste_notes = this.db.selectListeNotes(fiche_key);
				
				if (this.liste_notes.size() < this.liste_etudiants.size()) {
					
					long etudiant_key;
					
					//Ajout des nouveaux etudiants dans la table NOTE
					for(int i=this.liste_notes.size(); i<this.liste_etudiants.size(); i++) {
						etudiant_key = this.db.selectKeyEtudiant(this.liste_etudiants.get(i).getNomEtudiant(), this.liste_etudiants.get(i).getPrenomEtudiant());
						this.db.ajouterNote(fiche_key, new Note(0, etudiant_key));
					}
					
					//Reselection de la liste des notes
					this.liste_notes = this.db.selectListeNotes(fiche_key);
				}

				//Creation de la liste des notes
				listerNotes();
											
				//Selection du commentaire
				this.commentaire_temp = this.db.selectCommentaire(fiche_key);

				//Remplissage du commentaire
				if (this.commentaire_temp.getCommentaire().isEmpty()) this.edit_commentaire.setText("", BufferType.EDITABLE);
				this.edit_commentaire.setText(this.commentaire_temp.getCommentaire(), BufferType.EDITABLE);
				
				//Si la fiche est desarchiv�e
				if (this.fiche_temp.getArchiver() == 0) {
					
					//Bouton archiver
					this.bouton_archiver.setText(R.string.archiver);
					
					//On active les widgets
					activerWidget(true);
				}
				else {
					
					//Bouoton desarchiver
					this.bouton_archiver.setText(R.string.desarchiver);
					
					//On desactive les widgets
					activerWidget(false);
				}

			}
			else {
				
				//Selection du chef de projet
				this.spinner_chefProjet.setSelection(0);
				
				//Remplissage du ratingBar planning
				this.bar_planning.setRating(0);
				this.bar_planning.setIsIndicator(false);
				
				//Remplissage du commentaire planning
				this.edit_planning.setText("", BufferType.NORMAL);
				
				//Remplissage ratingBar communication
				this.bar_communication.setRating(0);
				this.bar_communication.setIsIndicator(false);
				
				//remplissage du commentaire communication
				this.edit_communication.setText("", BufferType.NORMAL);
				
				//Liste des notes
				this.liste_notes = this.db.selectListeNotes(fiche_key);
				
				//Creation de la liste des notes
				listerNotes();
				
				//Remplissage du commentaire
				this.edit_commentaire.setText("", BufferType.NORMAL);
				
				//On active les widgets
				activerWidget(true);
			}
		}
	}
	
	
	//-------------------------------------------------------------------//
	//-------------------- METHODE SAUVEGARDER FICHE --------------------//
	//-------------------------------------------------------------------//
	/**
	 * Methode pour sauvegarder une fiche d evaluation
	 */
	private void sauvegarder() {
		
		//Récupération de la clé de la fiche a modifier
		long fiche_key = this.db.selectKeyFiche(this.fiche_temp.getGroupeKey(), this.fiche_temp.getNumSemaine());
		
		if (fiche_key != 0) {
			
			//Modification de la fiche
			this.db.modifierFiche(fiche_key, this.fiche_temp);
			
			//Modification des notes d evaluation
			float rating = this.bar_planning.getRating();		
			long note = (long) rating;
			this.evaluation_temp.setNotePLanning(note);
			
			rating = this.bar_communication.getRating();		
			note = (long) rating;
			this.evaluation_temp.setNoteCommunication(note);
			
			//Modification de l evaluation temporaire
			if (!this.edit_planning.getText().toString().isEmpty()) this.evaluation_temp.setCommentairePlanning(this.edit_planning.getText().toString());
			if (!this.edit_communication.getText().toString().isEmpty()) this.evaluation_temp.setCommentaireCommunication(this.edit_communication.getText().toString());
			
			//Modifiation de l'evaluation dans la base
			this.db.modifierEvaluation(fiche_key, this.evaluation_temp);
			
			for(int i=0; i<this.liste_notes.size(); i++) {
				this.db.modifierNote(fiche_key, this.liste_notes.get(i));
			}
			
			//Modification du commentaire
			if (!this.edit_commentaire.getText().toString().isEmpty()) this.commentaire_temp.setCommentaire(this.edit_commentaire.getText().toString());
			
			//Modification du commentaire dans la base
			this.db.modifierCommentaire(fiche_key, this.commentaire_temp);
		}
	}
	
	
	//-----------------------------------------------------//
		//-------------------- MENU OPTION --------------------//
		//-----------------------------------------------------//
		/**
		 * Methode pour le menu
		 * 
		 * @param menu
		 * @return
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
