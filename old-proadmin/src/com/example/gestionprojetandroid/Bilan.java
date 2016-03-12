package com.example.gestionprojetandroid;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import packDatabase.DAOBase;
import packEntite.Etudiant;
import packEntite.Fiche;
import packEntite.Groupe;
import packEntite.Note;
import packEntite.Projet;
import packUtil.SpinnerTri;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Bilan extends Activity {

	/**
	 * Widgets
	 */
	public Spinner spinner_annee, spinner_groupe;
	public TextView libelle_nomProjet, libelle_etudiant, bilanGeneral_moyenne_groupe, bilanIndividual_moyenne_etudiant;
	public ListView listView_bilan_general, listView_bilan_individuel;
	public Button bilan_bouton_retour;
	
	
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
	 * Base de donnees
	 */
	public DAOBase db = new DAOBase(this);
	
	
	//----------------------------------------------------------------//
	//-------------------- CREATION DE L'ACTIVITE --------------------//
	//----------------------------------------------------------------//
	
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bilan);		
		
		
		this.db.open();
		
		Date date_actu = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy", Locale.FRENCH);
		this.annee_actuelle = Long.parseLong(formater.format(date_actu));
		
		
		//Spinner annee
		this.spinner_annee = (Spinner) findViewById(R.id.bilan_spinner_annee);
		
		ArrayList<Long> liste_annee = this.db.selectAllAnnee();
				
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
		
		SpinnerTri spinner_tri_annee = new SpinnerTri(this, this.spinner_annee, liste_string_annee);
		
		//Ecouteur sur spinner annee
		this.spinner_annee.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				//Creation de la liste des groupes enregistrÃ©s
				long annee = Long.parseLong((String) Bilan.this.spinner_annee.getSelectedItem());
				listerGroupes(annee);	
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//DO NOTHING
			}
		});
		
		//Spinner groupe
		this.spinner_groupe = (Spinner) findViewById(R.id.bilan_spinner_groupe);
		
		//Ecouteur sur spinner annee
		this.spinner_groupe.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				long annee = Long.parseLong((String) Bilan.this.spinner_annee.getSelectedItem());
				String idGroupe = (String) Bilan.this.spinner_groupe.getSelectedItem();
				
				//chargement du bilan du groupe choisi
				chargerBilanGeneral(annee, idGroupe);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//DO NOTHING
			}
		});
		
		ArrayList<Groupe> liste_groupes = this.db.selectListeGroupeAnnee(this.annee_actuelle);
		
		if(liste_groupes.isEmpty()) {
			
			AlertDialog.Builder adb = new AlertDialog.Builder(Bilan.this);
			adb.setMessage("Vous devez d\'abord cr\351er les groupes\n\nRedirection vers Groupes...");
	        adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {

	            @Override
				public void onClick(DialogInterface arg0, int arg1) {
	            	
	            	//Redirection vers activite Projets
	            	Intent activity_groupes = new Intent(getApplicationContext(), Groupes.class);
	            	finish();
	            	startActivity(activity_groupes);
				}					
	        });
            adb.show();	
            
			//Ajout de la liste des identifiants projets dans le spinner Projet
			SpinnerTri spinner_tri_projet = new SpinnerTri(this, this.spinner_groupe, new ArrayList<String>());            
		}
		else {
			//Tri de la liste des projets par identifiant croissant
			Collections.sort(liste_groupes, new Comparator<Groupe>() {
					
				@Override
				public int compare(Groupe gr1, Groupe gr2) {
					if (gr1.getIdGroupe().compareTo(gr2.getIdGroupe()) < 0) return -1;
					else if (gr1.getIdGroupe().compareTo(gr2.getIdGroupe()) > 0) return 1;
					else return 0;
				}
			});
			
			//Liste des identifiants
			ArrayList<String> liste_idGroupe = new ArrayList<String>();
			for(int i=0; i<liste_groupes.size(); i++) {
				liste_idGroupe.add(liste_groupes.get(i).getIdGroupe());
			}
			
			//Ajout de la liste des identifiants projets dans le spinner Projet
			SpinnerTri spinner_tri_projet = new SpinnerTri(this, this.spinner_groupe, liste_idGroupe);
		}
		
		
		//---------------------------------------------------------------//
		//-------------------- SECTION BILAN GENERAL --------------------//
		//---------------------------------------------------------------//
		
		//Libelle nom projet
		this.libelle_nomProjet = (TextView) findViewById(R.id.bilan_nomProjet);
		
		//ListView bilan general des etudiants
		this.listView_bilan_general = (ListView) findViewById(R.id.listViewBilanGeneral);
		
		//Ecouteur sur listview bilan
		this.listView_bilan_general.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				//Map groupe selectionne 
				HashMap<String, String> map = (HashMap<String, String>) Bilan.this.listView_bilan_general.getAdapter().getItem(position);
				
				//String nom etudiant
				String nom = map.get("listViewBilanGeneral_nom");
				
				//String prenom etudiant
				String prenom = map.get("listViewBilanGeneral_prenom");
				
				long etudiant_key = Bilan.this.db.selectKeyEtudiant(nom, prenom);
				
				if (etudiant_key != 0) {
					
					//Annee selectionne
					long annee = Bilan.this.groupe_temp.getAnnee();
					
					//Identifiant du groupe selectionne
					String idGroupe = Bilan.this.groupe_temp.getIdGroupe();				
					
					//Consultation du bilan de l etudiant
					chargerBilanIndividuel(annee, idGroupe, etudiant_key);
				}	
			}
		});
		
		//Texte moyenne bilan general
		this.bilanGeneral_moyenne_groupe = (TextView) findViewById(R.id.bilanGeneral_moyenne_groupe);
		
		
		//------------------------------------------------------------------//
		//-------------------- SECTION BILAN INDIVIDUEL --------------------//
		//------------------------------------------------------------------//
		
		//Bouton bilan retour
		this.bilan_bouton_retour = (Button) findViewById(R.id.bilan_bouton_retour);
		
		//Ecouteur sur bouton bilan
		this.bilan_bouton_retour.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Affichage bilan general
				findViewById(R.id.include_linearlayout_listeViewBilanGeneral).setVisibility(View.VISIBLE);
				findViewById(R.id.include_linearlayout_listeViewBilanIndividuel).setVisibility(View.GONE);			
			}
		});
		
		//Text etudiant
		this.libelle_etudiant = (TextView) findViewById(R.id.bilanIndividuel_etudiant);
		
		//ListView bilan general des etudiants
		this.listView_bilan_individuel = (ListView) findViewById(R.id.listViewBilanIndividuel);
		
		//Text moyenne bilan individuel
		this.bilanIndividual_moyenne_etudiant = (TextView) findViewById(R.id.bilanIndividuel_moyenne_etudiant);
		
		//groupe temporaire vide
		this.groupe_temp = new Groupe("", this.annee_actuelle, 0, 0);
		
		//liste vide des etudiants
		this.liste_etudiants = new ArrayList<Etudiant>();
		
		//Annee du groupe choisi via Fiche d evaluation
		long annee = this.annee_actuelle;
		
		//Identifiant du groupe choisi via Fiche d evaluation
		String idGroupe = "";
		
		//Groupe selectionne via Fiche d evaluation
		Bundle b = getIntent().getExtras();
		
		if (b != null) {
			
			long groupe_key = b.getLong("groupe_key");
			
			//Groupe choisi via Fiche d evaluation
			this.groupe_temp = this.db.selectGroupe(groupe_key);
			
			//Annee du groupe choisi via Fiche d evaluation
			annee = this.groupe_temp.getAnnee();
			
			//Identifiant du groupe choisi via Fiche d evaluation
			idGroupe = this.groupe_temp.getIdGroupe();
		}
		else {
			
			//Annee du groupe dans spinner annee
			annee = Long.parseLong((String) this.spinner_annee.getSelectedItem());
			
			//Identifiant du groupe dans spinner groupe
			idGroupe = (String) this.spinner_groupe.getSelectedItem();
		}
		
		//chargement du bilan du groupe choisi via Fiche d evaluation
		chargerBilanGeneral(annee, idGroupe);
		
	}
	
	
	
	//----------------------------------------------------------------------//
	//-------------------- METHODE LISTER LES ETUDIANTS --------------------//
	//----------------------------------------------------------------------//
	
	@SuppressWarnings("unused")
	private void listerGroupes(long annee) {
		
		//Liste des groupes de l'annee
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
		
		//Liste des identifiants des groupes
		ArrayList<String> liste_idGroupe = new ArrayList<String>();
		
		for(int i=0; i<liste_groupes.size(); i++) {
			liste_idGroupe.add(liste_groupes.get(i).getIdGroupe());
		}
		
		//chargement du spinner groupe
		SpinnerTri spinner_tri_groupe = new SpinnerTri(this, this.spinner_groupe, liste_idGroupe);		
		
	}
	
	
	//--------------------------------------------------------------------------//
	//-------------------- METHODE CHARGER UN BILAN GENERAL --------------------//
	//--------------------------------------------------------------------------//
	/**
	 * Methode pour charger un bilan
	 * 
	 * @param annee
	 * @param idGroupe
	 */
	private void chargerBilanGeneral(long annee, String idGroupe) {
		
		//Affichage bilan general
		findViewById(R.id.include_linearlayout_listeViewBilanGeneral).setVisibility(View.VISIBLE);
		findViewById(R.id.include_linearlayout_listeViewBilanIndividuel).setVisibility(View.GONE);
				
		//Verification de la cle
		long groupe_key = this.db.selectKeyGroupe(annee, idGroupe);
		
		//SÃ©lection du groupe dans la base
		this.groupe_temp = this.db.selectGroupe(groupe_key);
		
		//Selection de la liste des etudiants
		this.liste_etudiants = this.db.selectListeEtudiant(groupe_key);
		
		if (this.liste_etudiants.isEmpty()) {
			
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setMessage("Vous devez d\'abord cr\351er les \351tudiants du groupe\n\nRedirection vers Groupes...");
			adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
            	
					//Redirection vers activite groupe
					Intent activity_groupe = new Intent(getApplicationContext(), Groupes.class);
					finish();
					startActivity(activity_groupe);
				}					
			});
	        adb.show();	
		}
		
		//Selection des fiches du groupe
		ArrayList<Fiche> liste_fiches = this.db.selectListeFiche(groupe_key);
		
		if (liste_fiches.isEmpty()) {
			
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setMessage("Vous devez d\'abord archiver les fiches d\'\351valuation du groupe\n\nRedirection vers Evaluation...");
			adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
            	
					//Redirection vers activite evaluation
					Intent activity_evaluation = new Intent(getApplicationContext(), FicheEvaluation.class);
					finish();
					startActivity(activity_evaluation);
				}					
			});
	        adb.show();	
		}
	    
		long fiche_key, etudiant_key;
		double moyenne_etudiant, somme_moyennes = 0;
		int abscence;
		long somme_notes;
		ArrayList<Note> liste_notes_etudiant;
		Note note_etudiant;
		
		//Creation de la ArrayList qui nous permettra de remplire la listView
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	    
		//Creation d'une map pour chaque element
		HashMap<String, String> map;
		
		//Boucle pour la edition de la map contenant les informations
		for(int i=0; i<this.liste_etudiants.size(); i++) {
			
			etudiant_key = this.db.selectKeyEtudiant(this.liste_etudiants.get(i).getNomEtudiant(), this.liste_etudiants.get(i).getPrenomEtudiant());
			
			liste_notes_etudiant = new ArrayList<Note>();
			
			for(int j=0; j<liste_fiches.size(); j++) {
				
				if (liste_fiches.get(j).getArchiver() == 0) {
					break;
				}
				else {
					fiche_key = this.db.selectKeyFiche(liste_fiches.get(j).getGroupeKey(), liste_fiches.get(j).getNumSemaine());
					note_etudiant = this.db.selectNoteEtudiant(fiche_key, etudiant_key);
					liste_notes_etudiant.add(note_etudiant);
				}
			}
			
			abscence = 0;
			somme_notes = 0;
			moyenne_etudiant = 0;
			
			for(int k=0; k<liste_notes_etudiant.size(); k++) {
				
				if (liste_notes_etudiant.get(k).getNote() == 0) abscence++;
				somme_notes = somme_notes + liste_notes_etudiant.get(k).getNote();
			}
			
			//Moyenne etudiant
			if (!liste_notes_etudiant.isEmpty()) moyenne_etudiant = ((double) somme_notes) / liste_notes_etudiant.size();
			else moyenne_etudiant = 0;
			
			//Moyenne avec précision décimale sur deux chiffres
			DecimalFormat twoDForm = new DecimalFormat("#.##");
	        moyenne_etudiant =  Double.valueOf(twoDForm.format(moyenne_etudiant));
			
			//Creation d'une HashMap pour inserer les informations du premier item de notre listView
			map = new HashMap<String, String>();
			
			//on insere l' identifiant du groupe
			map.put("listViewBilanGeneral_nom", this.liste_etudiants.get(i).getNomEtudiant());
			//on insere l'identifiant du projet
			map.put("listViewBilanGeneral_prenom", this.liste_etudiants.get(i).getPrenomEtudiant());
			//on insere le titre du projet dans la map
			map.put("listViewBilanGeneral_abscence", String.valueOf(abscence));
			//on insere le nombre d'etudiants
			map.put("listViewBilanGeneral_moyenne", String.valueOf(moyenne_etudiant) + " / 7");
			
			//enfin on ajoute cette hashMap dans la arrayList
			listItem.add(map);
			
			//Moyenne groupe
			somme_moyennes = somme_moyennes + moyenne_etudiant;
		}
			
		//Creation d'un SimpleAdapter qui se chargera de mettre les items present dans notre liste (listItem)
			SimpleAdapter adapter = new SimpleAdapter (this.getApplicationContext(), listItem, R.layout.linearlayout_item_listview_bilan_general,
			      new String[] {"listViewBilanGeneral_nom", "listViewBilanGeneral_prenom", "listViewBilanGeneral_abscence", "listViewBilanGeneral_moyenne"}, 
			      new int[] {R.id.listViewBilanGeneral_nom, R.id.listViewBilanGeneral_prenom, R.id.listViewBilanGeneral_abscence, R.id.listViewBilanGeneral_moyenne});
	 
		//On attribue a notre listView l'adapter que l'on vient de creer
		this.listView_bilan_general.setAdapter(adapter);
		
		//Text projet
		if (groupe_key != 0) {
			Projet proj = this.db.selectProjet(this.groupe_temp.getAnnee(), this.groupe_temp.getProjetKey());
			this.libelle_nomProjet.setText(proj.getTitre());
		}
		
		double moyenne_groupe = 0;
		if (!this.liste_etudiants.isEmpty()) {
			
			//Moyenne groupe
			moyenne_groupe = somme_moyennes / this.liste_etudiants.size();
			
			//Moyenne avec précision décimale sur deux chiffres
			DecimalFormat twoDForm = new DecimalFormat("#.##");
	        moyenne_groupe =  Double.valueOf(twoDForm.format(moyenne_groupe));
		}

		
		//Moyenne generale
		this.bilanGeneral_moyenne_groupe.setText(String.valueOf(moyenne_groupe) + " / 7");
		
	}
	
	
	//-----------------------------------------------------------------------------//
	//-------------------- METHODE CHARGER UN BILAN INDIVIDUEL --------------------//
	//-----------------------------------------------------------------------------//
	/**
	 * Methode pour charger un bilan
	 * 
	 * @param annee
	 * @param idGroupe
	 * @param etudiant_key
	 */
	private void chargerBilanIndividuel(long annee, String idGroupe, long etudiant_key) {
		
		//Affichage bilan individuel
		findViewById(R.id.include_linearlayout_listeViewBilanGeneral).setVisibility(View.GONE);
		findViewById(R.id.include_linearlayout_listeViewBilanIndividuel).setVisibility(View.VISIBLE);
		
		//text etudiant
		Etudiant etudiant = this.db.selectEtudiant(etudiant_key);
		this.libelle_etudiant.setText(etudiant.getNomEtudiant() + " " + etudiant.getPrenomEtudiant());
		
		//Verification de la cle
		long groupe_key = this.db.selectKeyGroupe(annee, idGroupe);
		
		//Selection des fiches du groupe
		ArrayList<Fiche> liste_fiches = this.db.selectListeFiche(groupe_key);
	    
		long fiche_key;
		int semaine, progression = 0;
		Note note_etudiant = null;
		long somme_notes = 0;		
		
		//Creation de la ArrayList qui nous permettra de remplire la listView
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	    
		//Creation d'une map pour chaque element
		HashMap<String, String> map;
		
		//Boucle pour la edition de la map contenant les informations
		for(int i=0; i<liste_fiches.size(); i++) {
			
			Fiche fiche = this.db.selectFiche(groupe_key, (i+1));
			if (fiche.getArchiver() == 0) break;
			
			fiche_key = this.db.selectKeyFiche(liste_fiches.get(i).getGroupeKey(), liste_fiches.get(i).getNumSemaine());
			
			progression++;
			
			note_etudiant = this.db.selectNoteEtudiant(fiche_key, etudiant_key);
			
			if (note_etudiant == null) {
				for(int j=0; j<liste_fiches.size(); j++) {
					this.db.ajouterNote(fiche_key, new Note(0, etudiant_key));
				}
				note_etudiant = this.db.selectNoteEtudiant(fiche_key, etudiant_key);
			}
			
			somme_notes = somme_notes + note_etudiant.getNote();
			semaine = i+1;
			
			//Creation d'une HashMap pour inserer les informations du premier item de notre listView
			map = new HashMap<String, String>();
			
			//on insere le numero de la semaine
			map.put("listViewBilanIndividuel_semaine", "Semaine " + semaine);
			//on insere lna note de la semaine
			map.put("listViewBilanIndividuel_note", String.valueOf(note_etudiant.getNote() + " / 7"));
			
			//enfin on ajoute cette hashMap dans la arrayList
			listItem.add(map);
		}
			
		//Creation d'un SimpleAdapter qui se chargera de mettre les items present dans notre liste (listItem)
			SimpleAdapter adapter = new SimpleAdapter (this.getApplicationContext(), listItem, R.layout.linearlayout_item_listview_bilan_individuel,
			      new String[] {"listViewBilanIndividuel_semaine", "listViewBilanIndividuel_note"}, 
			      new int[] {R.id.listViewBilanIndividuel_semaine, R.id.listViewBilanIndividuel_note});
	 
		//On attribue a notre listView l'adapter que l'on vient de creer
		this.listView_bilan_individuel.setAdapter(adapter);
		
		//Moyenne
		double moyenne = 0;
		if (progression != 0) {
			
			moyenne = ((double) somme_notes) / progression;
			
			//Moyenne avec précision décimale sur deux chiffres
			DecimalFormat twoDForm = new DecimalFormat("#.##");
	        moyenne =  Double.valueOf(twoDForm.format(moyenne));
		}
		
        //Moyenne individuel
        this.bilanIndividual_moyenne_etudiant.setText(String.valueOf(moyenne) + " / 7");
    
	}
	
	
	//-----------------------------------------------------//
		//-------------------- MENU OPTION --------------------//
		//-----------------------------------------------------//
		
		//Mï¿½thode qui se dï¿½clenchera lorsque vous appuierez sur le bouton menu du tï¿½lï¿½phone
	    public boolean onCreateOptionsMenu(Menu menu) {
	 
	        //Crï¿½ation d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
	        MenuInflater inflater = getMenuInflater();
	        //Instanciation du menu XML spï¿½cifier en un objet Menu
	        inflater.inflate(R.layout.menu_autre, (android.view.Menu) menu);
			return true;
	     }

	       //Mï¿½thode qui se dï¿½clenchera au clic sur un item
	      public boolean onOptionsItemSelected(MenuItem item) {
	         //On regarde quel item a ï¿½tï¿½ cliquï¿½ grï¿½ce ï¿½ son id et on dï¿½clenche une action
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
	  	//-------------------- ARRET DE L'ACTIVITE --------------------//
	  	//-------------------------------------------------------------//
	  	
	  	@Override
	  	protected void onStop() {
	  		super.onStop();
	  		
	  		//Fermeture de la base
	  		this.db.close();
	  	}

}