package packDatabase;

import java.util.ArrayList;

import packEntite.Commentaire;
import packEntite.Encadrant;
import packEntite.Etudiant;
import packEntite.Evaluation;
import packEntite.Fiche;
import packEntite.Groupe;
import packEntite.Note;
import packEntite.Projet;
import packEntite.Securite;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;

/* Classe DAOBase pour rï¿½cupï¿½rer la base de donnï¿½es */

public class DAOBase {
	
	//Constante de version de la base de donnï¿½es
	protected final static int VERSION = 1;
	
	//Constante du fichier de la base de donnï¿½es
	protected final static String NOM = "database.db";
	
	//Objet Base de donnï¿½es SQLite
	protected SQLiteDatabase mDb = null;
	
	//Objet Base de donnï¿½es de notre base DatabaseHandler
	protected DatabaseHandler mHandler = null;
	
	public DAOBase(Context pContext) {
		this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
	}
	
	//Mï¿½thode d'ouverture de la base de donnï¿½es
	public SQLiteDatabase open() {
		mDb = mHandler.getWritableDatabase();
		return mDb;
	}
	
	//Mï¿½thode de fermeture de la base de donnï¿½es
	public void close() {
		mDb.close();
	}
	
	//Mï¿½thode pour renvoyer la base de donnï¿½es
	public SQLiteDatabase getDb() {
		return mDb;
	}
	
	
	
	
	//////////////////////////////
	//							//
	//	I - INTERFACE PROJET	//
	//							//
	//////////////////////////////
	
	
	
	//I.A - DECLARATION DES CONSTANTES DE NOM DE TABLES ET COLONNES
	
		
		//I.A.1 - Table Annee
			public static final String ANNEE_KEY = "key";
			public static final String ANNEE_ANNEE = "annee";
			public static final String ANNEE_TABLE_NAME = "Annee";
			
			
		//I.A.2 - Table Projet	
			public static final String PROJET_KEY = "key";
			public static final String PROJET_NIVEAUPROJET = "niveauProjet";
			public static final String PROJET_TITRE = "titre";
			public static final String PROJET_DESCRIPTION = "description";
			public static final String PROJET_TABLE_NAME = "Projet";
			
			
		//I.A.3 - Table AnneeProjet
			public static final String ANNEEPROJET_ANNEE_KEY = "annee_key";
			public static final String ANNEEPROJET_PROJET_KEY = "projet_key";
			public static final String ANNEEPROJET_IDPROJET = "idProjet";
			public static final String ANNEEPROJET_TABLE_NAME = "AnneeProjet";
			
		
	//I.B - METHODES POUR LA TABLE ANNEE
		
		/*Remarque
	 		*  - 1 : il n'y a pas de mï¿½thode modifier pour ï¿½viter la reproduction d'une mï¿½me annï¿½e avec plusieurs clï¿½ 
	 		*  - 2 : il n'y a pas de mï¿½thode supprimer annï¿½e
	 		*/	
			
		//I.B.1 - Sï¿½lectionner la clï¿½ d'une annï¿½e 
			//Si ï¿½chec renvoie la valeur 0 
			public long selectKeyAnnee(long annee) {
			
				long annee_key = 0;
				
				Cursor cursor = mDb.rawQuery("select " 
						+ ANNEE_KEY
						+ " from " + ANNEE_TABLE_NAME 
						+ " where " + ANNEE_ANNEE + " = ?", new String[]{ String.valueOf(annee)} );
				
				while (cursor.moveToNext()) { annee_key = cursor.getLong(0); }				
				cursor.close();
					
				return annee_key;
			}
			
			
		//I.B.2 - Sï¿½lectionner une annee avec sa clï¿½ 
			//Si ï¿½chec renvoie la valeur NULL
			public long selectAnnee(long annee_key) {
				
				long annee = 0;
					
				Cursor cursor = mDb.rawQuery("select " 
						+ ANNEE_ANNEE
						+ " from " + ANNEE_TABLE_NAME 
						+ " where " + ANNEE_KEY + " = ?", new String[]{ String.valueOf(annee_key)} );
					
				while (cursor.moveToNext()) { annee = cursor.getLong(0); }
				cursor.close();
					
				return annee;
			}
			
			
		//I.B.3 - Sï¿½lectionner une annee avec sa clï¿½ 
			//Si ï¿½chec renvoie la valeur NULL
			public ArrayList<Long> selectAllAnnee() {
				
				ArrayList<Long> listeAnnee = new ArrayList<Long>();
					
				Cursor cursor = mDb.rawQuery("select " 
						+ ANNEE_ANNEE
						+ " from " + ANNEE_TABLE_NAME, null);
					
				while (cursor.moveToNext()) { listeAnnee.add(cursor.getLong(0)); }
				cursor.close();
					
				return listeAnnee;
			}
			
			
		//I.B.4 - Sï¿½lectionner les annï¿½es (liste de clï¿½s annï¿½es) d'attribution d'un projet avec la clï¿½ du projet. 
			
			/* Remarque : 
			 	* on rï¿½cupï¿½re une liste de Long
			 	* mais Long n'est pas un type primitif
			 	* pour avoir la valeur primitive on utilisera la mï¿½thode longValue() dans chaque objet Long de la liste
			 	* Si ï¿½chec renvoie une liste vide
			 	*/
			public ArrayList<Long> selectListeAnnee(long projet_key) {
			
				ArrayList<Long> listeAnnee = new ArrayList<Long>();		
				
				Cursor cursor = mDb.rawQuery("select " 
						+ ANNEEPROJET_ANNEE_KEY
						+ " from " + ANNEEPROJET_TABLE_NAME 
						+ " where " + ANNEEPROJET_PROJET_KEY + " = ?", new String[]{ String.valueOf(projet_key)} );	
				
				while (cursor.moveToNext()) { listeAnnee.add(selectAnnee(cursor.getLong(0))); }			
				cursor.close();	
					
				return listeAnnee;
			}
			
			
		//I.B.5 - Ajouter une annee			
			public void ajouterAnnee(long annee) {	
			
				long annee_key = selectKeyAnnee(annee);
				
				if (annee_key == 0) {
					ContentValues value = new ContentValues();
					value.put(ANNEE_ANNEE, annee);
					mDb.insert(ANNEE_TABLE_NAME, null, value);
				}
			}
			
					
	//I.C - METHODES POUR LA TABLE PROJET */
		
		//I.C.1 - Supprimer un projet avec sa clï¿½
			
			/* Remarque : 
			 	* La suppression d'un projet entraine la suppression 
			 	* des lignes dans la table ANNEEPROJET qui sont liï¿½es au projet.
			 	* La table ANNEEPROJET ne doit plus contenir les attributions du projet supprimï¿½
			 	*/
				
			public void supprimerProjet(long projet_key) {	
				
				//Suppression des groupes du projets
				ArrayList<Groupe> liste_groupes = selectListeGroupeProjet(projet_key);
				long groupe_key = 0;
				for(int i=0; i<liste_groupes.size(); i++) {
					
					groupe_key = selectKeyGroupe(liste_groupes.get(i).getAnnee(), liste_groupes.get(i).getIdGroupe());
					
					supprimerGroupe(groupe_key);
				}
				
				//Suppression du projet dans la table ANNEEPROJET
				mDb.delete(ANNEEPROJET_TABLE_NAME, ANNEEPROJET_PROJET_KEY + " = ?", new String[] { String.valueOf(projet_key) } );
									
				//Suppression du projet dans PROJET
				mDb.delete(PROJET_TABLE_NAME, PROJET_KEY + " = ?", new String[] { String.valueOf(projet_key) } );
			}
			
			
		//I.C.2 - Supprimer un projet avec sa clï¿½
			
			/* Remarque : 
			 	* La suppression d'un projet entraine la suppression 
			 	* des lignes dans la table ANNEEPROJET qui sont liï¿½es au projet.
			 	* La table ANNEEPROJET ne doit plus contenir les attributions du projet supprimï¿½
			 	*/
				
			public void supprimerProjetAnnee(long annee, long projet_key) {	
				
				//Suppression des groupes de l'annee associÃ©s au projet
				ArrayList<Groupe> liste_groupe = selectListeGroupeAnnee(annee);
				long groupe_key;
				for(int i=0; i<liste_groupe.size(); i++) {
					if (liste_groupe.get(i).getProjetKey() == projet_key) {
						groupe_key = selectKeyGroupe(annee, liste_groupe.get(i).getIdGroupe());
						supprimerGroupe(groupe_key);
					}
				}
				
				long annee_key = selectKeyAnnee(annee);
				
				//Suppression du projet dans la table ANNEEPROJET
				mDb.delete(ANNEEPROJET_TABLE_NAME, ANNEEPROJET_ANNEE_KEY + " = ? AND " + ANNEEPROJET_PROJET_KEY + " = ?", new String[] { String.valueOf(annee_key), String.valueOf(projet_key) } );
									
			}
			
			
		//I.C.3 - Modifier un projet avec sa clï¿½ et les nouvelles valeurs (Projet projet)
			public void modifierProjet(long annee, long projet_key, Projet proj) {
				
				long annee_key = selectKeyAnnee(annee);
				ContentValues value = new ContentValues();
				value.put(ANNEEPROJET_IDPROJET, proj.getIdProjet());
				mDb.update(ANNEEPROJET_TABLE_NAME, value, ANNEEPROJET_ANNEE_KEY + " = ? AND " + ANNEEPROJET_PROJET_KEY + " = ?", new String[] { String.valueOf(annee_key), String.valueOf(projet_key) });
				
				value.clear();
				value.put(PROJET_NIVEAUPROJET, proj.getNiveauProjet());
				value.put(PROJET_TITRE, proj.getTitre());
				value.put(PROJET_DESCRIPTION, proj.getDescription());
				mDb.update(PROJET_TABLE_NAME, value, PROJET_KEY + " = ?", new String[] { String.valueOf(projet_key) });
			}
			
			
		//I.C.4 - Sï¿½lectionner l'identifiant d'un projet pour l'annï¿½e en cours
			//Si ï¿½chec renvoie la chaine vide
			private String selectIdProjetAnnee(long annee, long projet_key) {
				
				String idProjet = "";				
				long annee_key = selectKeyAnnee(annee);
				
				Cursor cursor = mDb.rawQuery("select " 
						+ ANNEEPROJET_IDPROJET
						+ " from " + ANNEEPROJET_TABLE_NAME
						+ " where " + ANNEEPROJET_ANNEE_KEY + " = ? AND " + ANNEEPROJET_PROJET_KEY + " = ?", new String[]{ String.valueOf(annee_key), String.valueOf(projet_key)} );
				while (cursor.moveToNext()) {
					idProjet = cursor.getString(0);
				}
				cursor.close();
				
				return idProjet;
			}
		
			
		//I.C.5 - Sï¿½lectionner un projet avec sa clï¿½
			//Si ï¿½chec renvoie la valeur NULL
			public Projet selectProjet(long annee, long projet_key) {
				
				Projet proj = null;
				String idProjet = selectIdProjetAnnee(annee, projet_key);
				
				Cursor cursor = mDb.rawQuery("select " 
						+ PROJET_NIVEAUPROJET + ", " + PROJET_TITRE + ", " + PROJET_DESCRIPTION
						+ " from " + PROJET_TABLE_NAME 
						+ " where " + PROJET_KEY + " = ?", new String[]{ String.valueOf(projet_key)} );
				while (cursor.moveToNext()) {					
					proj = new Projet(idProjet, cursor.getString(0), cursor.getString(1), cursor.getString(2));
				}
				cursor.close();
				
				return proj;
			}
			
			
		//I.C.6 - Sï¿½lectionner la clï¿½ d'un projet avec son identifiant et son annee
			//Si ï¿½chec renvoie la valeur -1
			public long selectKeyProjet(String titre) {
				
				//On initialise la clï¿½ de retour
				long projet_key = 0;
					
				Cursor cursor = mDb.rawQuery("select " 
						+ PROJET_KEY
						+ " from " + PROJET_TABLE_NAME 
						+ " where " + PROJET_TITRE + " = ?", new String[]{ String.valueOf(titre)} );
					
				while (cursor.moveToNext()) { projet_key = cursor.getLong(0); }
				cursor.close();
					
				return projet_key;
			}	
			
			
		//I.C.7 - Sï¿½lectionner les projets (liste de clï¿½s projets) d'une annï¿½e avec la clï¿½ de l'annï¿½e
			
			/* Remarque : 
			 	* on rï¿½cupï¿½re une liste de Long
			 	* mais Long n'est pas un type primitif
			 	* pour avoir la valeur primitive on utilisera la mï¿½thode longValue() dans chaque objet Long de la liste
			 	* Si ï¿½chec renvoie une liste vide
			 	*/
			
			private ArrayList<Long> selectListeProjetKey(long annee_key) {
				
				ArrayList<Long> listeProjetKey = new ArrayList<Long>();		
					
				Cursor cursor = mDb.rawQuery("select " 
						+ ANNEEPROJET_PROJET_KEY
						+ " from " + ANNEEPROJET_TABLE_NAME 
						+ " where " + ANNEEPROJET_ANNEE_KEY + " = ?", new String[]{ String.valueOf(annee_key)} );	
					
				while (cursor.moveToNext()) { listeProjetKey.add(cursor.getLong(0)); }
				cursor.close();		
					
				return listeProjetKey;
			}
			
			
		//I.C.8 - Sï¿½lectionner les projets d'une annï¿½e avec l'annee
			
			/* Remarque : 
			 	* on rï¿½cupï¿½re une liste de Projet
			 	* Si ï¿½chec renvoie une liste vide
			 	*/
			
			public ArrayList<Projet> selectListeProjet(long annee) {
				
				ArrayList<Projet> listeProjet = new ArrayList<Projet>();
				
				long annee_key = selectKeyAnnee(annee);
				
				if(annee_key != 0) {
					ArrayList<Long> listeProjetKey = selectListeProjetKey(annee_key);
					
					Projet projet = new Projet();
					for (int i=0; i<listeProjetKey.size(); i++) {
						projet = selectProjet(annee, listeProjetKey.get(i).longValue());
						listeProjet.add(projet);				
					}
				}
				
				return listeProjet;
			}
			
			
		//I.C.9 - Ajouter un projet ï¿½ une annï¿½e 	
			public void ajouterProjet(long annee, Projet proj) {
				
				//On sï¿½lectionne la clï¿½ de l'annï¿½e
				long annee_key = selectKeyAnnee(annee);
					
				//Si la clï¿½ de l'annï¿½e existe on continue, sinon on n'ajoute pas le projet
				if (annee_key != 0) {
					
					//On sï¿½lectionne la clï¿½ du projet
					long projet_key = selectKeyProjet(proj.getTitre());
						
					//Cas 1 : Si le projet n'existe pas on l'ajoute dans la table PROJET
					if (projet_key == 0) {
						
						//On insï¿½re le projet dans la table PROJET
						ContentValues value = new ContentValues();
						value.put(PROJET_NIVEAUPROJET, proj.getNiveauProjet());
						value.put(PROJET_TITRE, proj.getTitre());
						value.put(PROJET_DESCRIPTION, proj.getDescription());		
						mDb.insert(PROJET_TABLE_NAME, null, value);
							
						//On resï¿½lectionne la clï¿½ du projet aprï¿½s insertion
						projet_key = selectKeyProjet(proj.getTitre());
							
						//On ajoute dans la table ANNEEPROJET la clï¿½ de l'annï¿½e, la clï¿½ du projet et l'identifiant pour l'annï¿½e en cours
						value.clear();
						value.put(ANNEEPROJET_ANNEE_KEY, annee_key);
						value.put(ANNEEPROJET_PROJET_KEY, projet_key);
						value.put(ANNEEPROJET_IDPROJET, proj.getIdProjet());
						mDb.insert(ANNEEPROJET_TABLE_NAME, null, value);
					}	
							
					//Cas 2 : Si le projet existe on vï¿½rifie s'il n'a pas dï¿½jï¿½ ï¿½tï¿½ attribuï¿½ ï¿½ la mï¿½me annï¿½e
					else {
						
						//On crï¿½e une liste des clï¿½es projets associï¿½s ï¿½ l'annï¿½e
						ArrayList<Long> listeProjetKey = selectListeProjetKey(annee_key);
								
						//On crï¿½e un boolï¿½en qui vï¿½rifie l'attribution du projet
						boolean attribue = false;
						
						//On vï¿½rifie si le projet n'a pas ï¿½tï¿½ attribuï¿½
						for(int i=0; i<listeProjetKey.size(); i++) {
							if (projet_key == listeProjetKey.get(i).longValue()) {
								attribue = true;
								break;
							}
						}
							
						//S'il n'a pas ï¿½tï¿½ attribuï¿½ on l'attribue ï¿½ la nouvelle annï¿½e
						if (attribue == false) {
							
							//On ajoute dans la table ANNEEPROJET la clï¿½ de l'annï¿½e, la clï¿½ du projet et l'identifiant pour l'annï¿½e en cours
							ContentValues value = new ContentValues();
							value.put(ANNEEPROJET_ANNEE_KEY, annee_key);
							value.put(ANNEEPROJET_PROJET_KEY, projet_key);
							value.put(ANNEEPROJET_IDPROJET, proj.getIdProjet());
							mDb.insert(ANNEEPROJET_TABLE_NAME, null, value);
						}
					}
				}
			}
	
	
	
	
	//////////////////////////////
	//							//
	//	II - INTERFACE GROUPE	//
	//							//
	//////////////////////////////
		
		
		
	//II.A - DECLARATION DES CONSTANTES DE NOM DE TABLES ET COLONNES 
		
		
		//II.A.1 - Table Groupe
			public static final String GROUPE_KEY = "key";
			public static final String GROUPE_IDGROUPE = "idGroupe";
			public static final String GROUPE_ANNEE = "annee";
			public static final String GROUPE_NBRETUDIANT = "nbrEtudiant";
			public static final String GROUPE_PROJET_KEY = "projet_key";
			public static final String GROUPE_TABLE_NAME = "Groupe";
		
		
		//II.A.2 - Table Etudiant
			public static final String ETUDIANT_KEY = "key";
			public static final String ETUDIANT_NOMETUDIANT = "nomEtudiant";
			public static final String ETUDIANT_PRENOMETUDIANT = "prenomEtudiant";
			public static final String ETUDIANT_TABLE_NAME = "Etudiant";
		
		
		//II.A.3 - Table EtudiantGroupe
			public static final String ETUDIANTGROUPE_ETUDIANT_KEY = "etudiant_key";
			public static final String ETUDIANTGROUPE_GROUPE_KEY = "groupe_key";
			public static final String ETUDIANTGROUPE_TABLE_NAME = "EtudiantGroupe";
		
		
		
	//II.B - METHODES DE LA TABLE GROUPE
		
		
		//II.B.1 - Supprimer un groupe avec sa clï¿½
			public void supprimerGroupe(long groupe_key) {
				
				//Suppresion des fiches du groupe
				ArrayList<Fiche> liste_fiches = selectListeFiche(groupe_key);
				long fiche_key = 0;
				for(int i=0; i<liste_fiches.size(); i++) {
					
					fiche_key = selectKeyFiche(groupe_key, liste_fiches.get(i).getNumSemaine());
					
					supprimerFiche(fiche_key);
				}
				
				//On supprime le groupe de la table ETUDIANTGROUPE 
				mDb.delete(ETUDIANTGROUPE_TABLE_NAME, ETUDIANTGROUPE_GROUPE_KEY + " = ?", new String[] { String.valueOf(groupe_key) } );
					
				//On supprime le groupe de la table GROUPE
				mDb.delete(GROUPE_TABLE_NAME, GROUPE_KEY + " = ?", new String[] { String.valueOf(groupe_key) } );
			}
	
				
		//II.B.2 - Modifier un groupe avec sa clï¿½ et les nouvelles valeurs (Groupe groupe)
			public void modifierGroupe(long groupe_key, Groupe gr) {
				ContentValues value = new ContentValues();
				value.put(GROUPE_IDGROUPE, gr.getIdGroupe());
				value.put(GROUPE_ANNEE, gr.getAnnee());
				value.put(GROUPE_NBRETUDIANT, gr.getNbrEtudiant());
				value.put(GROUPE_PROJET_KEY, gr.getProjetKey());
				mDb.update(GROUPE_TABLE_NAME, value, GROUPE_KEY + " = ?", new String[] { String.valueOf(groupe_key) });
			}
		
				
		//II.B.3 - Sï¿½lectionner la clï¿½ d'un groupe avec son annï¿½e et son identifiant
			//Si ï¿½chec renvoie la valeur 0
			public long selectKeyGroupe(long annee, String idGroupe) {
				
				long groupe_key = 0;
				
				Cursor cursor = mDb.rawQuery("select " 
						+ GROUPE_KEY
						+ " from " + GROUPE_TABLE_NAME 
						+ " where " + GROUPE_IDGROUPE + " = ? AND " + GROUPE_ANNEE + " = ?", new String[]{ String.valueOf(idGroupe), String.valueOf(annee)} );
					
				while (cursor.moveToNext()) { groupe_key = cursor.getLong(0); }
				cursor.close();
					
				return groupe_key;
			}
		
				
		//II.B.4 - Sï¿½lectionner un groupe avec sa clï¿½
			//Si ï¿½chec renvoie la valeur NULL
			public Groupe selectGroupe(long groupe_key) {
					
				Groupe gr = null;
				
				Cursor cursor = mDb.rawQuery("select " 
						+ GROUPE_IDGROUPE + ", " + GROUPE_ANNEE + ", " + GROUPE_NBRETUDIANT + ", " + GROUPE_PROJET_KEY
						+ " from " + GROUPE_TABLE_NAME 
						+ " where " + GROUPE_KEY + " = ?", new String[]{ String.valueOf(groupe_key)} );
					
				while (cursor.moveToNext()) {
					gr = new Groupe(cursor.getString(0), cursor.getLong(1), cursor.getLong(2), cursor.getLong(3));
				}
				cursor.close();
				
				return gr;
			}
				
		
		//II.B.5 - Sï¿½lectionner la liste des groupes d'une annï¿½e
			//Si ï¿½chec renvoie une liste vide
			public ArrayList<Groupe> selectListeGroupeAnnee(long annee) {
				
				ArrayList<Groupe> listeGroupe = new ArrayList<Groupe>();
					
				Cursor cursor = mDb.rawQuery("select " 
						+ GROUPE_IDGROUPE + ", " + GROUPE_NBRETUDIANT + ", " + GROUPE_PROJET_KEY
						+ " from " + GROUPE_TABLE_NAME 
						+ " where " + GROUPE_ANNEE + " = ? ", new String[]{ String.valueOf(annee)} );
					
				Groupe groupe = null; 
				while (cursor.moveToNext()) {
					groupe = new Groupe();
					groupe.setIdGroupe(cursor.getString(0));
					groupe.setAnnee(annee);
					groupe.setNbrEtudiant(cursor.getLong(1));
					groupe.setProjetKey(cursor.getLong(2));
					listeGroupe.add(groupe);
				}
				cursor.close();
				
				return listeGroupe;
			}
			
			
			//II.B.5 - Sï¿½lectionner la liste des groupes d'une annï¿½e
			//Si ï¿½chec renvoie une liste vide
			public ArrayList<Groupe> selectListeGroupeProjet(long projet_key) {
				
				ArrayList<Groupe> listeGroupe = new ArrayList<Groupe>();
					
				Cursor cursor = mDb.rawQuery("select " 
						+ GROUPE_IDGROUPE + ", " + GROUPE_ANNEE + ", " + GROUPE_NBRETUDIANT
						+ " from " + GROUPE_TABLE_NAME 
						+ " where " + GROUPE_PROJET_KEY + " = ? ", new String[]{ String.valueOf(projet_key)} );
					
				Groupe groupe = null; 
				while (cursor.moveToNext()) {
					groupe = new Groupe();
					groupe.setIdGroupe(cursor.getString(0));
					groupe.setAnnee(cursor.getLong(1));
					groupe.setNbrEtudiant(cursor.getLong(2));
					groupe.setProjetKey(projet_key);
					listeGroupe.add(groupe);
				}
				cursor.close();
				
				return listeGroupe;
			}
		
				
		//II.B.6 - Ajouter un groupe
			public void ajouterGroupe(Groupe gr) {
				
				//On vï¿½rifie si le groupe n'existe dans la table GROUPE avec sa clï¿½
				long groupe_key = selectKeyGroupe(gr.getAnnee(), gr.getIdGroupe()); 
				
				//Si la clï¿½ du groupe n'existe pas on ajoute le groupe
				if (groupe_key == 0) {
					ContentValues value = new ContentValues();
					value.put(GROUPE_IDGROUPE, gr.getIdGroupe());
					value.put(GROUPE_ANNEE, gr.getAnnee());
					value.put(GROUPE_NBRETUDIANT, gr.getNbrEtudiant());
					value.put(GROUPE_PROJET_KEY, gr.getProjetKey());
					mDb.insert(GROUPE_TABLE_NAME, null, value);
				}
			}	
				
				
				
	//II.C - METHODES DE LA TABLE ETUDIANT */
			
				
		//II.C.1 - Supprimer un etudiant avec sa clï¿½
			public void supprimerEtudiant(long etudiant_key) {
				
				//On supprime l'ï¿½tudiant de la table ETUDIANTGROUPE
				mDb.delete(ETUDIANTGROUPE_TABLE_NAME, ETUDIANTGROUPE_ETUDIANT_KEY + " = ?", new String[] { String.valueOf(etudiant_key) } );
					
				//On supprime l'ï¿½tudiant de la table ETUDIANT
				mDb.delete(ETUDIANT_TABLE_NAME, ETUDIANT_KEY + " = ?", new String[] { String.valueOf(etudiant_key) } );				
			}
			
			
		//II.C.2 - Supprimer un etudiant d'un groupe avec sa clï¿½
			public void supprimerEtudiantGroupe(long etudiant_key, long groupe_key) {
				
				//On supprime l'ï¿½tudiant de la table ETUDIANTGROUPE
				mDb.delete(ETUDIANTGROUPE_TABLE_NAME, ETUDIANTGROUPE_ETUDIANT_KEY + " = ? AND " + ETUDIANTGROUPE_GROUPE_KEY + " = ?", new String[] { String.valueOf(etudiant_key), String.valueOf(groupe_key) } );	
			}
			
		
		//II.C.3 - Modifier un etudiant avec sa clï¿½ et les nouvelles valeurs (Etudiant etudiant)
			public void modifierEtudiant(long etudiant_key, Etudiant etu) {
				ContentValues value = new ContentValues();
				value.put(ETUDIANT_NOMETUDIANT, etu.getNomEtudiant());
				value.put(ETUDIANT_PRENOMETUDIANT, etu.getPrenomEtudiant());
				mDb.update(ETUDIANT_TABLE_NAME, value, ETUDIANT_KEY + " = ?", new String[] { String.valueOf(etudiant_key) });
			}
		
		
		//II.C.4 - Sï¿½lectionner la clï¿½ d'un ï¿½tudiant avec son nom et son prï¿½nom
			//Mï¿½thode utilisï¿½e par la mï¿½thode ajouterEtudiant et pour la sï¿½lection du chef de projet
			//Si ï¿½chec renvoie la valeur 0
			public long selectKeyEtudiant(String nom, String prenom) {
				
				long etudiant_key = 0;
				
				Cursor cursor = mDb.rawQuery("select " 
						+ ETUDIANT_KEY
						+ " from " + ETUDIANT_TABLE_NAME 
						+ " where " + ETUDIANT_NOMETUDIANT + " = ? AND " + ETUDIANT_PRENOMETUDIANT + " = ?", new String[]{ String.valueOf(nom), String.valueOf(prenom)} );
				
				while (cursor.moveToNext()) { etudiant_key = cursor.getLong(0); }
				cursor.close();
				
				return etudiant_key;
			}
				
			
		//II.C.5 - Sï¿½lectionner un ï¿½tudiant avec sa clï¿½
			//Mï¿½thode pour la sï¿½lection du chef de projet
			//Si ï¿½chec renvoie la valeur NULL
			public Etudiant selectEtudiant(long etudiant_key) {
				
				Etudiant etu = null;
				
				Cursor cursor = mDb.rawQuery("select " 
						+ ETUDIANT_NOMETUDIANT + ", " + ETUDIANT_PRENOMETUDIANT
						+ " from " + ETUDIANT_TABLE_NAME 
						+ " where " + ETUDIANT_KEY + " = ?", new String[]{ String.valueOf(etudiant_key)} );
				
				while (cursor.moveToNext()) { etu = new Etudiant(cursor.getString(0), cursor.getString(1)); }
				cursor.close();
				
				return etu;
			}
		
				
		//II.C.6 - Sï¿½lectionner la liste des ï¿½tudiants membre d'un groupe
			//Si ï¿½chec renvoie une liste vide
			public ArrayList<Etudiant> selectListeEtudiant(long groupe_key) {
				
				//On initialise la liste de retour
				ArrayList<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
				
				//On sï¿½lectionne dans la table ETUDIANTGROUPE les clï¿½s des etudiants associï¿½es ï¿½ la clï¿½ du groupe
				Cursor cursor = mDb.rawQuery("select " 
						+ ETUDIANTGROUPE_ETUDIANT_KEY
						+ " from " + ETUDIANTGROUPE_TABLE_NAME 
						+ " where " + ETUDIANTGROUPE_GROUPE_KEY + " = ?", new String[]{ String.valueOf(groupe_key) } );		
								
				//Pour chaque clï¿½ du curseur on sï¿½lectionne l'ï¿½tudiat correspondant ï¿½ cette clï¿½ puis on l'ajoute dans la liste
				while (cursor.moveToNext()) { listeEtudiant.add(selectEtudiant(cursor.getLong(0))); }			
				cursor.close();	
					
				return listeEtudiant;
			}
			
					
		//II.C.7 - Ajouter un etudiant dans un groupe
			public void ajouterEtudiant(long groupe_key, Etudiant etu) {
				
				//On vï¿½rifie si l'ï¿½tudiant n'existe pas dï¿½jï¿½
				long etudiant_key = selectKeyEtudiant(etu.getNomEtudiant(), etu.getPrenomEtudiant());
				
				//Cas 1 : Si l'etudiant n'existe pas on l'ajoute dans la table ETUDIANT
				if (etudiant_key == 0) {
									
					//On insï¿½re l'etudiant dans la table ETUDIANT
					ContentValues value = new ContentValues();
					value.put(ETUDIANT_NOMETUDIANT, etu.getNomEtudiant());
					value.put(ETUDIANT_PRENOMETUDIANT, etu.getPrenomEtudiant());
					mDb.insert(ETUDIANT_TABLE_NAME, null, value);
							
					//On resï¿½lectionne la clï¿½ de l'etudiant aprï¿½s insertion
					etudiant_key = selectKeyEtudiant(etu.getNomEtudiant(), etu.getPrenomEtudiant());
							
					//On ajoute dans la table ETUDIANTGROUPE la clï¿½ de l'etudiant et la clï¿½ du groupe
					value.clear();
					value.put(ETUDIANTGROUPE_ETUDIANT_KEY, etudiant_key);
					value.put(ETUDIANTGROUPE_GROUPE_KEY, groupe_key);
					mDb.insert(ETUDIANTGROUPE_TABLE_NAME, null, value);	
				}	
						
				//Cas 2 : Si l'etudiant existe, on vï¿½rifie s'il n'est pas dï¿½jï¿½ membre du mï¿½me groupe
				else {
						
					//On sï¿½lectionne les groupes dont l'ï¿½tudiant est membre					
					Cursor cursor = mDb.rawQuery("select " 
							+ ETUDIANTGROUPE_GROUPE_KEY
							+ " from " + ETUDIANTGROUPE_TABLE_NAME 
							+ " where " + ETUDIANTGROUPE_ETUDIANT_KEY + " = ?", new String[]{ String.valueOf(etudiant_key)} );		
							
					//On crï¿½e un boolï¿½en qui vï¿½rifie la prï¿½sence de l'ï¿½tudiant
					boolean membre = false;
						
					//On vï¿½rifie si l'ï¿½tudiant est dï¿½jï¿½ membre dans le meme groupe					
					while (cursor.moveToNext()) {  
						if (groupe_key == cursor.getLong(0)) {
							membre = true;
							break;
						}			
					}
					cursor.close();
						
					//S'il n'est pas membre on l'insï¿½re dans le groupe
					if (membre == false) {
							
						//On ajoute dans la table ANNEEPROJET la clï¿½ de l'annï¿½e et la clï¿½ du projet
						ContentValues value = new ContentValues();
						value.put(ETUDIANTGROUPE_ETUDIANT_KEY, etudiant_key);
						value.put(ETUDIANTGROUPE_GROUPE_KEY, groupe_key);
						mDb.insert(ETUDIANTGROUPE_TABLE_NAME, null, value);	
					}
					
					//Si l'ï¿½tudiant est dï¿½jï¿½ memebre dans le mï¿½me groupe on ne fait rien
				}
			}
				
				
				
				
	//////////////////////////////
	//							//
	//	III - INTERFACE FICHE	//
	//							//
	//////////////////////////////
				
				
				
	//III.A - DECLARATION DES CONSTANTES DE NOM DE TABLES ET COLONNES
				
				
		//III.A.1 - Table Fiche
			public static final String FICHE_KEY = "key";
			public static final String FICHE_ARCHIVER = "archiver";
			public static final String FICHE_NUMSEMAINE = "numSemaine";
			public static final String FICHE_CHEFPROJET_KEY = "chefProjet_key";
			public static final String FICHE_GROUPE_KEY = "groupe_key";
			public static final String FICHE_TABLE_NAME = "Fiche";
					
				
		//III.A.2 - Table Evaluation
			public static final String EVALUATION_KEY = "key";
			public static final String EVALUATION_FICHE_KEY = "fiche_key";
			public static final String EVALUATION_NOTEPLANNING = "notePlanning";
			public static final String EVALUATION_COMMENTAIREPLANNING = "commentairePlanning";	
			public static final String EVALUATION_NOTECOMMUNICATION = "noteCommunication";
			public static final String EVALUATION_COMMENTAIRECOMMUNICATION = "commentaireCommunication";
			public static final String EVALUATION_TABLE_NAME = "Evaluation";
				
				
		//III.A.3 - Table Note
			public static final String NOTE_KEY = "key";
			public static final String NOTE_FICHE_KEY = "fiche_key";
			public static final String NOTE_NOTE = "note";
			public static final String NOTE_ETUDIANT_KEY = "etudiant_key";
			public static final String NOTE_TABLE_NAME = "Note";

				
		//III.A.4 - Table Commentaire
			public static final String COMMENTAIRE_KEY = "key";
			public static final String COMMENTAIRE_FICHE_KEY = "fiche_key";
			public static final String COMMENTAIRE_COMMENT = "comment";
			public static final String COMMENTAIRE_TABLE_NAME = "Commentaire";
					
				
					
	//III.B - METHODES DE LA TABLE EVALUATION */	
					
					
		//III.B.1 - Modifier une ï¿½valuation avec la clï¿½ de sa fiche et les nouvelles valeurs (Evaluation evaluation)			
			public void modifierEvaluation(long fiche_key, Evaluation eval) {
				ContentValues value = new ContentValues();
				value.put(EVALUATION_NOTEPLANNING, eval.getNotePLanning());
				value.put(EVALUATION_COMMENTAIREPLANNING, eval.getCommentairePlanning());
				value.put(EVALUATION_NOTECOMMUNICATION, eval.getNoteCommunication());
				value.put(EVALUATION_COMMENTAIRECOMMUNICATION, eval.getCommentaireCommunication());
				mDb.update(EVALUATION_TABLE_NAME, value, EVALUATION_FICHE_KEY + " = ?", new String[] { String.valueOf(fiche_key) });
			}
					

		//III.B.2 - Sï¿½lectionner une ï¿½valuation avec la clï¿½	de sa fiche		
			//Si ï¿½chec renvoie la valeur NULL			
			public Evaluation selectEvaluation(long fiche_key) {
						
				Evaluation eval = null;
						
				Cursor cursor = mDb.rawQuery("select " 
					+ EVALUATION_NOTEPLANNING + ", " + EVALUATION_COMMENTAIREPLANNING + ", " + EVALUATION_NOTECOMMUNICATION + ", " + EVALUATION_COMMENTAIRECOMMUNICATION  
					+ " from " + EVALUATION_TABLE_NAME 
					+ " where " + EVALUATION_FICHE_KEY + " = ?", new String[]{ String.valueOf(fiche_key)} );
							
				while (cursor.moveToNext()) { 
					eval = new Evaluation(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3)); }
					cursor.close();
						
				return eval;
			}		
			
			
		//III.B.3 - Ajouter une evaluation avec la clé de sa fiche			
			private void ajouterEvaluation(long fiche_key, Evaluation evaluation) {
				
				Evaluation eval = selectEvaluation(fiche_key);
				
				if ((fiche_key != 0) && (eval == null)) {
					ContentValues value = new ContentValues();
					value.put(EVALUATION_FICHE_KEY, fiche_key);
					value.put(EVALUATION_NOTEPLANNING, evaluation.getNotePLanning());
					value.put(EVALUATION_COMMENTAIREPLANNING, evaluation.getCommentairePlanning());
					value.put(EVALUATION_NOTECOMMUNICATION, evaluation.getNoteCommunication());
					value.put(EVALUATION_COMMENTAIRECOMMUNICATION, evaluation.getCommentaireCommunication());
					mDb.insert(EVALUATION_TABLE_NAME, null, value);
				}
			}
				
						
	//III.C - METHODES DE LA TABLE NOTE */
						
		//III.C.1 - Modifier une note avec la clï¿½ de sa fiche et les nouvelles valeurs (Note note)			
			public void modifierNote(long fiche_key, Note note_etu) {
				ContentValues value = new ContentValues();
				value.put(NOTE_NOTE, note_etu.getNote());
				mDb.update(NOTE_TABLE_NAME, value, NOTE_FICHE_KEY + " = ? AND " + NOTE_ETUDIANT_KEY + " = ? ", new String[] { String.valueOf(fiche_key), String.valueOf(note_etu.getEtudiantKey()) });
			}
				
			
		//III.C.2 - Sï¿½lectionner la liste des notes d'une fiche
			//Si ï¿½chec renvoie la valeur NULL			
			public Note selectNoteEtudiant(long fiche_key, long etudiant_key) {
						
				Note note = null;
						
				Cursor cursor = mDb.rawQuery("select " 
					+ NOTE_NOTE
					+ " from " + NOTE_TABLE_NAME 
					+ " where " + NOTE_FICHE_KEY + " = ? AND " + NOTE_ETUDIANT_KEY + " = ?", new String[]{ String.valueOf(fiche_key), String.valueOf(etudiant_key)} );
							
				while (cursor.moveToNext()) { 
					note = new Note(cursor.getLong(0), etudiant_key);
				}
				cursor.close();
						
				return note;
			}
			
						
		//III.C.3 - Sï¿½lectionner la liste des notes d'une fiche
			//Si ï¿½chec renvoie la valeur NULL			
			public ArrayList<Note> selectListeNotes(long fiche_key) {
						
				ArrayList<Note> listeNotes = new ArrayList<Note>();
						
				Cursor cursor = mDb.rawQuery("select " 
					+ NOTE_NOTE + ", " + NOTE_ETUDIANT_KEY
					+ " from " + NOTE_TABLE_NAME 
					+ " where " + NOTE_FICHE_KEY + " = ? ", new String[]{ String.valueOf(fiche_key)} );
							
				Note note_etu = null; 
				while (cursor.moveToNext()) { 
					note_etu = new Note();
					note_etu.setNote(cursor.getLong(0));
					note_etu.setEtudiantKey(cursor.getLong(1));
					listeNotes.add(note_etu);
				}
				cursor.close();
						
				return listeNotes;
			}
			
			
		//III.C.4 - Ajouter une note avec la clé de sa fiche			
			public void ajouterNote(long fiche_key, Note note) {
				
				Note note_etu = selectNoteEtudiant(fiche_key, note.getEtudiantKey());
				
				if ((fiche_key != 0) && (note_etu == null)) {
					ContentValues value = new ContentValues();
					value.put(NOTE_FICHE_KEY, fiche_key);
					value.put(NOTE_NOTE, note.getNote());
					value.put(NOTE_ETUDIANT_KEY, note.getEtudiantKey());
					mDb.insert(NOTE_TABLE_NAME, null, value);
				}
			}
				
				
		//III.D - METHODES DE LA TABLE COMMENTAIRE */
						
			//III.D.1 - Modifier un commentaire avec sa clï¿½ et les nouvelles valeurs (Commentaire commentaire)			
				public void modifierCommentaire(long fiche_key, Commentaire com) {
					ContentValues value = new ContentValues();
					value.put(COMMENTAIRE_COMMENT, com.getCommentaire());		
					mDb.update(COMMENTAIRE_TABLE_NAME, value, COMMENTAIRE_FICHE_KEY + " = ?", new String[] { String.valueOf(fiche_key) });
				}
				

			//III.D.2 - Sï¿½lectionner un commentaire avec la clï¿½ de sa fiche			
				//Si ï¿½chec renvoie la valeur NULL			
				public Commentaire selectCommentaire(long fiche_key) {
						
					Commentaire com = null;
							
					Cursor cursor = mDb.rawQuery("select " 
							+ COMMENTAIRE_COMMENT
							+ " from " + COMMENTAIRE_TABLE_NAME 
							+ " where " + COMMENTAIRE_FICHE_KEY + " = ?", new String[]{ String.valueOf(fiche_key)} );
							
					while (cursor.moveToNext()) { com = new Commentaire(cursor.getString(0)); }
					cursor.close();
							
					return com;
				}	
				
				
			//III.D.3 - Ajouter un commentaire avec la clé de sa fiche			
				private void ajouterCommentaire(long fiche_key, Commentaire commentaire) {
					
					Commentaire com = selectCommentaire(fiche_key);
					
					if ((fiche_key != 0) && (com == null)) {
						ContentValues value = new ContentValues();
						value.put(COMMENTAIRE_FICHE_KEY, fiche_key);
						value.put(COMMENTAIRE_COMMENT, commentaire.getCommentaire());
						mDb.insert(COMMENTAIRE_TABLE_NAME, null, value);
					}
				}
				
				
				
					
		//III.E - METHODES DE LA TABLE FICHE */
				
			//III.E.1 - Supprimer un fiche avec sa clï¿½		
					
				/* Remarque :
				 	* la suppression d'une fiche entraine ï¿½galement la supprï¿½ssion de
				 	* son ï¿½valuation, ses notes et son commentaire
				 	*/
					
				public void supprimerFiche(long fiche_key) {
						
					//On supprime son ï¿½valuation
					mDb.delete(EVALUATION_TABLE_NAME, EVALUATION_FICHE_KEY + " = ?", new String[] { String.valueOf(fiche_key) } );
						
					//On supprime ses notes
					mDb.delete(NOTE_TABLE_NAME, NOTE_FICHE_KEY + " = ?", new String[] { String.valueOf(fiche_key) } );
						
					//On supprime son commentaire
					mDb.delete(COMMENTAIRE_TABLE_NAME, COMMENTAIRE_FICHE_KEY + " = ?", new String[] { String.valueOf(fiche_key) } );
							
					//On supprime la fiche
					mDb.delete(FICHE_TABLE_NAME, FICHE_KEY + " = ?", new String[] { String.valueOf(fiche_key) } );
				}
				
				
			//III.E.2 - Modifier un fiche avec sa clï¿½ et les nouvelles valeurs (Fiche fiche)			
				public void modifierFiche(long fiche_key, Fiche fiche_hebdo) {
					ContentValues value = new ContentValues();
					value.put(FICHE_ARCHIVER, fiche_hebdo.getArchiver());
					value.put(FICHE_NUMSEMAINE, fiche_hebdo.getNumSemaine());
					value.put(FICHE_CHEFPROJET_KEY, fiche_hebdo.getChefProjet());
					value.put(FICHE_GROUPE_KEY, fiche_hebdo.getGroupeKey());	
					mDb.update(FICHE_TABLE_NAME, value, FICHE_KEY + " = ?", new String[] { String.valueOf(fiche_key) });
				}
				

			//III.E.3 - Sï¿½lectionner la clï¿½ d'une fiche avec la clï¿½ du groupe et le numero de la semaine
				//Si ï¿½chec renvoi la valeur 0
				public long selectKeyFiche(long groupe_key, long numSemaine) {
						
					long fiche_key = 0;
					
					Cursor cursor = mDb.rawQuery("select " 
							+ FICHE_KEY
							+ " from " + FICHE_TABLE_NAME 
							+ " where " + FICHE_GROUPE_KEY + " = ? AND " + FICHE_NUMSEMAINE + " = ?", new String[]{ String.valueOf(groupe_key), String.valueOf(numSemaine)} );
						
					while (cursor.moveToNext()) { fiche_key = cursor.getLong(0); }
					cursor.close();
						
					return fiche_key;
				}
				
					
			//III.E.4 - Sï¿½lectionner une fiche avec sa clï¿½
				//Si ï¿½chec renvoie la valeur NULL
				public Fiche selectFiche(long groupe_key, long numSemaine) {
						
					Fiche fiche_hebdo = null;
						
					Cursor cursor = mDb.rawQuery("select " 
							+ FICHE_ARCHIVER + ", " + FICHE_CHEFPROJET_KEY
							+ " from " + FICHE_TABLE_NAME 
							+ " where " + FICHE_GROUPE_KEY + " = ? AND " + FICHE_NUMSEMAINE + " = ?", new String[]{ String.valueOf(groupe_key), String.valueOf(numSemaine)} );
						
					while (cursor.moveToNext()) { fiche_hebdo = new Fiche(cursor.getLong(0), numSemaine, cursor.getLong(1), groupe_key); }
					cursor.close();
							
					return fiche_hebdo;
				}
				
					
			//III.E.5 - Sï¿½lectionner la liste des fiches d'un groupe avec la clï¿½ du groupe
				//Si ï¿½chec renvoie une liste vide
				public ArrayList<Fiche> selectListeFiche(long groupe_key) {
						
					ArrayList<Fiche> listeFiche = new ArrayList<Fiche>();
						
					Cursor cursor = mDb.rawQuery("select " 
							+ FICHE_ARCHIVER + ", " + FICHE_NUMSEMAINE + ", " + FICHE_CHEFPROJET_KEY 
							+ " from " + FICHE_TABLE_NAME 
							+ " where " + FICHE_GROUPE_KEY + " = ?", new String[]{ String.valueOf(groupe_key)} );
						
					//On insere chaque valeur du curseur dans une fiche tempo puis on insï¿½re la fiche tempo dans la liste
					Fiche fiche = null; 
					while (cursor.moveToNext()) {
						fiche = new Fiche();
						fiche.setArchiver(cursor.getLong(0));
						fiche.setNumSemaine(cursor.getLong(1));
						fiche.setChefProjet(cursor.getLong(2));
						fiche.setGroupeKey(groupe_key);
						listeFiche.add(fiche);
					}
					cursor.close();
						
					return listeFiche;
				}
				
				
			//III.E.6 - Ajouter une fiche d'ï¿½valuation hebdomadaire
				public void ajouterFiche(Fiche fiche, Evaluation evaluation, ArrayList<Note> liste_notes, Commentaire commentaire) {
						
					//On vï¿½rifie si la fiche n'existe pas dans la table FICHE avec sa clï¿½
					long fiche_key = selectKeyFiche(fiche.getGroupeKey(), fiche.getNumSemaine());
						
					//Si la clï¿½ n'existe pas on crï¿½e la fiche
					if (fiche_key == 0) {
								
						//On insï¿½re la fiche dans la table FICHE
						ContentValues value = new ContentValues();
						value.put(FICHE_ARCHIVER, fiche.getArchiver());
						value.put(FICHE_NUMSEMAINE, fiche.getNumSemaine());
						value.put(FICHE_CHEFPROJET_KEY, fiche.getChefProjet());
						value.put(FICHE_GROUPE_KEY, fiche.getGroupeKey());
						mDb.insert(FICHE_TABLE_NAME, null, value);	
						
						fiche_key = selectKeyFiche(fiche.getGroupeKey(), fiche.getNumSemaine());
						
						//On insere l evaluation dans la table EVALUATION
						ajouterEvaluation(fiche_key, evaluation);
						
						//On insère les notes dans la table NOTE
						for(int i=0; i<liste_notes.size(); i++) {
							ajouterNote(fiche_key, liste_notes.get(i));
						}
						
						//On insère le commentaire dans la table COMMENTAIRE
						ajouterCommentaire(fiche_key, commentaire);						
					}
				}
		
		
		
		
	//////////////////////////////////////
	//									//
	//	IV - INTERFACE CONFIGURATION	//
	//									//
	//////////////////////////////////////
		
		
					
	//IV.A - DECLARATION DES CONSTANTES DE NOM DE TABLES ET COLONNES
				
					
		//IV.A.1 - Table DftConfig
			public static final String DFTCONFIG_KEY = "key";
			public static final String DFTCONFIG_DFT_ECRAN_ACCUEIL = "dft_ecran_accueil";
			public static final String DFTCONFIG_DFT_RESTER_CONNECTE = "dft_rester_connecte";
			public static final String DFTCONFIG_DFT_ACTIVER_MDP = "dft_activer_mdp";
			public static final String DFTCONFIG_TABLE_NAME = "DftConfig";
							
					
		//IV.A.2 - Table Demarrage
			public static final String DEMARRAGE_KEY = "key";
			public static final String DEMARRAGE_ECRAN_DEMARRAGE = "nom";
			public static final String DEMARRAGE_TABLE_NAME = "Demarrage";
							
					
		//IV.A.3 - Table Securite
			public static final String SECURITE_KEY = "key";
			public static final String SECURITE_MOT_DE_PASSE = "mot_de_passe";
			public static final String SECURITE_ACTIVER_MDP = "activer_mdp";
			public static final String SECURITE_TABLE_NAME = "Securite";
							
					
		//IV.A.4 - Table Encadrant
			public static final String ENCADRANT_KEY = "key";
			public static final String ENCADRANT_NOM = "nom";
			public static final String ENCADRANT_PRENOM = "prenom";
			public static final String ENCADRANT_EMAIL = "email";
			public static final String ENCADRANT_DEMARRAGE_KEY = "demarrage_key";
			public static final String ENCADRANT_TABLE_NAME = "Encadrant";
					
					
	//IV.B - METHODES DE LA TABLE DFTCONFIG */
					
							
		//IV.B.1 - Rï¿½initialiser la configuration
			public void resetToDftConfig() {
			
				//Reset de la sï¿½curitï¿½
				ContentValues value = new ContentValues();
				value.put(SECURITE_ACTIVER_MDP, 0);
				mDb.update(SECURITE_TABLE_NAME, value, SECURITE_KEY + " = ?", new String[] { String.valueOf(1) });
				
				//Reset du dï¿½marrage
				value.clear();
				value.put(ENCADRANT_DEMARRAGE_KEY, 1);
				mDb.update(ENCADRANT_TABLE_NAME, value, ENCADRANT_KEY + " = ?", new String[] { String.valueOf(1) });
			}
				
			
			
	//IV.C - METHODES DE LA TABLE DEMARRAGE */
	
		
		//IV.C.1 - Sï¿½lectionner la clï¿½ d'un dï¿½marrage avec son nom d'ecran de demarrage
			//Si ï¿½chec renvoie la valeur 0
			public long selectKeyDemarrage(String ecran_demarrage) {
				
				long demarrage_key = 0;	
								
				Cursor cursor = mDb.rawQuery("select " 
						+ DEMARRAGE_KEY
						+ " from " + DEMARRAGE_TABLE_NAME
						+ " where " + DEMARRAGE_ECRAN_DEMARRAGE + " = ?", new String[]{ String.valueOf(ecran_demarrage)} );
						
				while (cursor.moveToNext()) { demarrage_key = cursor.getLong(0); }
				cursor.close();
								
				return demarrage_key;
			}
						
					
		//IV.C.2 - Sï¿½lectionner un demarrage avec sa clï¿½
			//Si ï¿½chec renvoie la valeur NULL
			public String selectDemarrage(long demarrage_key) {
								
				String ecran_demarrage = "Erreur : cle inexistante";
				
				Cursor cursor = mDb.rawQuery("select " 
						+ DEMARRAGE_ECRAN_DEMARRAGE
						+ " from " + DEMARRAGE_TABLE_NAME
						+ " where " + DEMARRAGE_KEY + " = ?", new String[]{ String.valueOf(demarrage_key)} );
						
				while (cursor.moveToNext()) { ecran_demarrage = cursor.getString(0); }
				cursor.close();
								
				return ecran_demarrage;
			}
					

							
	//IV.D - METHODES DE LA TABLE SECURITE */
							
							
		//IV.D.1 - Modifier une sï¿½curitï¿½ les nouvelles valeurs (Encadrant encadrant)
			public void modifierSecurite(Securite secu) {
				ContentValues value = new ContentValues();
				value.put(SECURITE_MOT_DE_PASSE, secu.getMotDePasse());
				value.put(SECURITE_ACTIVER_MDP, secu.getActiverMdp());
				mDb.update(SECURITE_TABLE_NAME, value, SECURITE_KEY + " = ?", new String[] { String.valueOf(1) });
			}
							
							
		//IV.D.2 - Sï¿½lectionner la sï¿½curite
			//Si ï¿½chec renvoie la valeur NULL
			public Securite selectSecurite() {
							
			Securite secu = null;
								
				Cursor cursor = mDb.rawQuery("select " 
						+ SECURITE_MOT_DE_PASSE + ", " + SECURITE_ACTIVER_MDP
						+ " from " + SECURITE_TABLE_NAME
						+ " where " + SECURITE_KEY + " = ?", new String[]{ String.valueOf(1)} );
								
				while (cursor.moveToNext()) { secu = new Securite(cursor.getString(0), cursor.getLong(1)); }
				cursor.close();
								
				return secu;
			}
	
			
	//IV.E - METHODES DE LA TABLE ENCADRANT */
				
		/* Remarque :
		 	* il n'y a pas de mï¿½thode ajouter ni supprimer pour la table ENCADRANT
		 	* cette table est prï¿½configurï¿½e et on ne doit ni ajouter, ni supprimer des donnï¿½es
		 	
		 	* Si on souhaite crï¿½er un Encadrant, il faudra modifier les valeurs prï¿½configurï¿½es
		 	* avec la mï¿½thode modifier
		 	*/

		//IV.E.1 - Modifier un encadrant avec les nouvelles valeurs (Encadrant encadrant)
			public void modifierEncadrant(Encadrant enc) {
				ContentValues value = new ContentValues();
				value.put(ENCADRANT_NOM, enc.getNom());
				value.put(ENCADRANT_PRENOM, enc.getPrenom());
				value.put(ENCADRANT_EMAIL, enc.getEmail());
				value.put(ENCADRANT_DEMARRAGE_KEY, enc.getDemarrageKey());
				mDb.update(ENCADRANT_TABLE_NAME, value, ENCADRANT_KEY + " = ?", new String[] { String.valueOf(1) });
			}
					
						
		//IV.E.2 - Sï¿½lectionner un encadrant	
			//Si ï¿½chec renvoi la valeur NULL
			public Encadrant selectEncadrant() {
								
				Encadrant enc = null;
								
				Cursor cursor = mDb.rawQuery("select " 
						+ ENCADRANT_NOM + ", " + ENCADRANT_PRENOM + ", " + ENCADRANT_EMAIL + ", " + ENCADRANT_DEMARRAGE_KEY
						+ " from " + ENCADRANT_TABLE_NAME 
						+ " where " + ENCADRANT_KEY + " = ?", new String[]{ String.valueOf(1)} );
					
				while (cursor.moveToNext()) { 
					enc = new Encadrant(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3));
				}
				cursor.close();
				
				return enc;
			}
		
}
