package packDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	/* Constantes de table pour les noms des colonnes de table, le nom de table, 
	   la requete de suppresion de table et la requete de creation */
	
	
	/* DECLARATION DES TABLES PRINCIPALES POUR LE FONCTIONNEMENT DE L'APLLICATION */
	
	//Table Annee
	public static final String ANNEE_KEY = "key";
	public static final String ANNEE_ANNEE = "annee";
	public static final String ANNEE_TABLE_NAME = "Annee";
	public static final String ANNEE_TABLE_DROP = "DROP TABLE IF EXISTS" + ANNEE_TABLE_NAME + ";";
	public static final String ANNEE_TABLE_CREATE = 
		"CREATE TABLE " + ANNEE_TABLE_NAME + " ("
			+ ANNEE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ ANNEE_ANNEE + " INTEGER UNIQUE);";
	
	//Table Projet
	public static final String PROJET_KEY = "key";
	public static final String PROJET_NIVEAUPROJET = "niveauProjet";
	public static final String PROJET_TITRE = "titre";
	public static final String PROJET_DESCRIPTION = "description";
	public static final String PROJET_TABLE_NAME = "Projet";
	public static final String PROJET_TABLE_DROP = "DROP PROJET IF EXISTS" + PROJET_TABLE_NAME + ";";
	public static final String PROJET_TABLE_CREATE = 
		"CREATE TABLE " + PROJET_TABLE_NAME + " (" 
			+ PROJET_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ PROJET_NIVEAUPROJET + " TEXT, "
			+ PROJET_TITRE + " TEXT, "
			+ PROJET_DESCRIPTION + " TEXT);";
	
	//Table AnneeProjet
	public static final String ANNEEPROJET_ANNEE_KEY = "annee_key";
	public static final String ANNEEPROJET_PROJET_KEY = "projet_key";
	public static final String ANNEEPROJET_IDPROJET = "idProjet";
	public static final String ANNEEPROJET_TABLE_NAME = "AnneeProjet";
	public static final String ANNEEPROJET_TABLE_DROP = "DROP PROJET IF EXISTS" + ANNEEPROJET_TABLE_NAME + ";";
	public static final String ANNEEPROJET_TABLE_CREATE = 
		"CREATE TABLE " + ANNEEPROJET_TABLE_NAME + " (" 
			+ ANNEEPROJET_ANNEE_KEY + " INTEGER, "
			+ ANNEEPROJET_PROJET_KEY +" INTEGER, " 
			+ ANNEEPROJET_IDPROJET + " TEXT, " 
			+ "FOREIGN KEY (" + ANNEEPROJET_ANNEE_KEY + ") REFERENCES " + ANNEE_TABLE_NAME + "( "+ ANNEE_KEY + "), "
			+ "FOREIGN KEY (" + ANNEEPROJET_PROJET_KEY + ") REFERENCES " + PROJET_TABLE_NAME + "( "+ PROJET_KEY + "), "
			+ "PRIMARY KEY (" + ANNEEPROJET_ANNEE_KEY + ", " + ANNEEPROJET_PROJET_KEY + "));";
	
	//Table Groupe
	public static final String GROUPE_KEY = "key";
	public static final String GROUPE_IDGROUPE = "idGroupe";
	public static final String GROUPE_ANNEE = "annee";
	public static final String GROUPE_NBRETUDIANT = "nbrEtudiant";
	public static final String GROUPE_PROJET_KEY = "projet_key";
	public static final String GROUPE_TABLE_NAME = "Groupe";
	public static final String GROUPE_TABLE_DROP = "DROP TABLE IF EXISTS" + GROUPE_TABLE_NAME + ";";
	public static final String GROUPE_TABLE_CREATE = 
		"CREATE TABLE " + GROUPE_TABLE_NAME + " (" 
			+ GROUPE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ GROUPE_IDGROUPE + " TEXT, " 
			+ GROUPE_ANNEE + " INTEGER, "
			+ GROUPE_NBRETUDIANT + " INTEGER, "
			+ GROUPE_PROJET_KEY +" INTEGER, " 
			+ "FOREIGN KEY (" + GROUPE_PROJET_KEY + ") REFERENCES " + PROJET_TABLE_NAME + "( "+ PROJET_KEY + "));";
	
	//Table Etudiant
	public static final String ETUDIANT_KEY = "key";
	public static final String ETUDIANT_NOMETUDIANT = "nomEtudiant";
	public static final String ETUDIANT_PRENOMETUDIANT = "prenomEtudiant";
	public static final String ETUDIANT_TABLE_NAME = "Etudiant";
	public static final String ETUDIANT_TABLE_DROP = "DROP TABLE IF EXISTS" + ETUDIANT_TABLE_NAME + ";";
	public static final String ETUDIANT_TABLE_CREATE = 
		"CREATE TABLE " + ETUDIANT_TABLE_NAME + " ("
			+ ETUDIANT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ ETUDIANT_NOMETUDIANT + " TEXT, "
			+ ETUDIANT_PRENOMETUDIANT + " TEXT);";
	
	//EtudiantGroupe
	public static final String ETUDIANTGROUPE_ETUDIANT_KEY = "etudiant_key";
	public static final String ETUDIANTGROUPE_GROUPE_KEY = "groupe_key";
	public static final String ETUDIANTGROUPE_TABLE_NAME = "EtudiantGroupe";
	public static final String ETUDIANTGROUPE_TABLE_DROP = "DROP TABLE IF EXISTS" + ETUDIANTGROUPE_TABLE_NAME + ";";
	public static final String ETUDIANTGROUPE_TABLE_CREATE = 
		"CREATE TABLE " + ETUDIANTGROUPE_TABLE_NAME + " (" 
			+ ETUDIANTGROUPE_ETUDIANT_KEY + " INTEGER, "
			+ ETUDIANTGROUPE_GROUPE_KEY +" INTEGER, " 
			+ "FOREIGN KEY (" + ETUDIANTGROUPE_ETUDIANT_KEY + ") REFERENCES " + ETUDIANT_TABLE_NAME + "( "+ ETUDIANT_KEY + "), "
			+ "FOREIGN KEY (" + ETUDIANTGROUPE_GROUPE_KEY + ") REFERENCES " + GROUPE_TABLE_NAME + "( "+ GROUPE_KEY + "), "
			+ "PRIMARY KEY (" + ETUDIANTGROUPE_ETUDIANT_KEY + ", " + ETUDIANTGROUPE_GROUPE_KEY + "));";	
	
	//Table Fiche
	public static final String FICHE_KEY = "key";
	public static final String FICHE_NUMSEMAINE = "numSemaine";
	public static final String FICHE_ARCHIVER = "archiver";
	public static final String FICHE_CHEFPROJET_KEY = "chefProjet_key";
	public static final String FICHE_GROUPE_KEY = "groupe_key";
	public static final String FICHE_TABLE_NAME = "Fiche";
	public static final String FICHE_TABLE_DROP = "DROP TABLE IF EXISTS" + FICHE_TABLE_NAME + ";";
	public static final String FICHE_TABLE_CREATE = 
		"CREATE TABLE " + FICHE_TABLE_NAME + " ("
			+ FICHE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ FICHE_ARCHIVER + " INTEGER, "
			+ FICHE_NUMSEMAINE + " INTEGER, "
			+ FICHE_CHEFPROJET_KEY + " INTEGER, "
			+ FICHE_GROUPE_KEY + " INTEGER, "
			+ "FOREIGN KEY (" + FICHE_GROUPE_KEY + ") REFERENCES " + GROUPE_TABLE_NAME + "( "+ GROUPE_KEY + "));";
	
	//Table Evaluation
	public static final String EVALUATION_KEY = "key";
	public static final String EVALUATION_FICHE_KEY = "fiche_key";
	public static final String EVALUATION_NOTEPLANNING = "notePlanning";
	public static final String EVALUATION_COMMENTAIREPLANNING = "commentairePlanning";
	public static final String EVALUATION_NOTECOMMUNICATION = "noteCommunication";
	public static final String EVALUATION_COMMENTAIRECOMMUNICATION = "commentaireCommunication";
	public static final String EVALUATION_TABLE_NAME = "Evaluation";
	public static final String EVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS" + EVALUATION_TABLE_NAME + ";";
	public static final String EVALUATION_TABLE_CREATE = 
		"CREATE TABLE " + EVALUATION_TABLE_NAME + " (" 
			+ EVALUATION_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ EVALUATION_FICHE_KEY + " INTEGER, "
			+ EVALUATION_NOTEPLANNING + " INTEGER, "
			+ EVALUATION_COMMENTAIREPLANNING + " TEXT, "
			+ EVALUATION_NOTECOMMUNICATION + " INTEGER, "
			+ EVALUATION_COMMENTAIRECOMMUNICATION + " TEXT);";
	
	//Table Note
	public static final String NOTE_KEY = "key";
	public static final String NOTE_FICHE_KEY = "fiche_key";
	public static final String NOTE_NOTE = "note";
	public static final String NOTE_ETUDIANT_KEY = "etudiant_key";
	public static final String NOTE_TABLE_NAME = "Note";
	public static final String NOTE_TABLE_DROP = "DROP TABLE IF EXISTS" + NOTE_TABLE_NAME + ";";
	public static final String NOTE_TABLE_CREATE = 
		"CREATE TABLE " + NOTE_TABLE_NAME + " (" 
			+ NOTE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NOTE_FICHE_KEY + " INTEGER, "
			+ NOTE_NOTE + " INTEGER, "
			+ NOTE_ETUDIANT_KEY + " INTEGER, "
			+ "FOREIGN KEY (" + NOTE_ETUDIANT_KEY + ") REFERENCES " + ETUDIANT_TABLE_NAME + "( "+ ETUDIANT_KEY + "));";
		
	//Table Commentaire
	public static final String COMMENTAIRE_KEY = "key";
	public static final String COMMENTAIRE_FICHE_KEY = "fiche_key";
	public static final String COMMENTAIRE_COMMENT = "comment";
	public static final String COMMENTAIRE_TABLE_NAME = "Commentaire";
	public static final String COMMENTAIRE_TABLE_DROP = "DROP TABLE IF EXISTS" + COMMENTAIRE_TABLE_NAME + ";";
	public static final String COMMENTAIRE_TABLE_CREATE = 
		"CREATE TABLE " + COMMENTAIRE_TABLE_NAME + " (" 
			+ COMMENTAIRE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COMMENTAIRE_FICHE_KEY + " INTEGER, "
			+ COMMENTAIRE_COMMENT + " TEXT);";
	
	
	/* DECLARATION DES TABLES POUR LA CONFIGURATION */
	
	//Table DftConfig
	public static final String DFTCONFIG_KEY = "key";
	public static final String DFTCONFIG_DFT_ECRAN_ACCUEIL = "dft_ecran_accueil";
	public static final String DFTCONFIG_DFT_RESTER_CONNECTE = "dft_rester_connecte";
	public static final String DFTCONFIG_DFT_ACTIVER_MDP = "dft_activer_mdp";
	public static final String DFTCONFIG_TABLE_NAME = "DftConfig";
	public static final String DFTCONFIG_TABLE_DROP = "DROP TABLE IF EXISTS" + DFTCONFIG_TABLE_NAME + ";";
	public static final String DFTCONFIG_TABLE_CREATE = 
		"CREATE TABLE " + DFTCONFIG_TABLE_NAME + " ("
			+ DFTCONFIG_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ DFTCONFIG_DFT_ECRAN_ACCUEIL + " TEXT, "
			+ DFTCONFIG_DFT_ACTIVER_MDP + " INTEGER, "
			+ DFTCONFIG_DFT_RESTER_CONNECTE + " INTEGER);";
	
	//Table Demarrage
	public static final String DEMARRAGE_KEY = "key";
	public static final String DEMARRAGE_ECRAN_DEMARRAGE = "nom";
	public static final String DEMARRAGE_TABLE_NAME = "Demarrage";
	public static final String DEMARRAGE_TABLE_DROP = "DROP TABLE IF EXISTS" + DEMARRAGE_TABLE_NAME + ";";
	public static final String DEMARRAGE_TABLE_CREATE = 
		"CREATE TABLE " + DEMARRAGE_TABLE_NAME + " ("
			+ DEMARRAGE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ DEMARRAGE_ECRAN_DEMARRAGE + " TEXT);";
	
	//Table Securite
	public static final String SECURITE_KEY = "key";
	public static final String SECURITE_MOT_DE_PASSE = "mot_de_passe";
	public static final String SECURITE_ACTIVER_MDP = "activer_mdp";
	public static final String SECURITE_TABLE_NAME = "Securite";
	public static final String SECURITE_TABLE_DROP = "DROP TABLE IF EXISTS" + SECURITE_TABLE_NAME + ";";
	public static final String SECURITE_TABLE_CREATE = 
			"CREATE TABLE " + SECURITE_TABLE_NAME + " ("
					+ SECURITE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ SECURITE_MOT_DE_PASSE + " TEXT, "
					+ SECURITE_ACTIVER_MDP + " INTEGER);";
		
	//Table Encadrant
	public static final String ENCADRANT_KEY = "key";
	public static final String ENCADRANT_NOM = "nom";
	public static final String ENCADRANT_PRENOM = "prenom";
	public static final String ENCADRANT_EMAIL = "email";
	public static final String ENCADRANT_DEMARRAGE_KEY = "demarrage_key";
	public static final String ENCADRANT_TABLE_NAME = "Encadrant";
	public static final String ENCADRANT_TABLE_DROP = "DROP TABLE IF EXISTS" + ENCADRANT_TABLE_NAME + ";";
	public static final String ENCADRANT_TABLE_CREATE = 
		"CREATE TABLE " + ENCADRANT_TABLE_NAME + " ("
			+ ENCADRANT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ ENCADRANT_NOM + " TEXT, "
			+ ENCADRANT_PRENOM + " TEXT, "
			+ ENCADRANT_EMAIL + " TEXT, "
			+ ENCADRANT_DEMARRAGE_KEY + " INTEGER, "
			+ "FOREIGN KEY (" + ENCADRANT_DEMARRAGE_KEY + ") REFERENCES " + DEMARRAGE_TABLE_NAME + "( "+ DEMARRAGE_KEY + "));";
	
	
	
	
	/* DECLARATION DE FONCTIONS DE LA BASE DE DONNEES */ 
	
	//Constructeur
	public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	//M�thode appel�e � la cr�ation de la base de donn�es	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(ANNEE_TABLE_CREATE);
		db.execSQL(PROJET_TABLE_CREATE);
		db.execSQL(ANNEEPROJET_TABLE_CREATE);
		db.execSQL(GROUPE_TABLE_CREATE);
		db.execSQL(ETUDIANT_TABLE_CREATE);
		db.execSQL(ETUDIANTGROUPE_TABLE_CREATE);
		db.execSQL(FICHE_TABLE_CREATE);
		db.execSQL(EVALUATION_TABLE_CREATE);
		db.execSQL(NOTE_TABLE_CREATE);
		db.execSQL(COMMENTAIRE_TABLE_CREATE);
		db.execSQL(DFTCONFIG_TABLE_CREATE);
		db.execSQL(DEMARRAGE_TABLE_CREATE);
		db.execSQL(SECURITE_TABLE_CREATE);
		db.execSQL(ENCADRANT_TABLE_CREATE);
		
		//Pr�d�finition de la configuration par d�faut
		ContentValues value = new ContentValues();
		value.put(DFTCONFIG_DFT_ECRAN_ACCUEIL, "accueil");
		value.put(DFTCONFIG_DFT_ACTIVER_MDP, 0);
		value.put(DFTCONFIG_DFT_RESTER_CONNECTE, 0);
		db.insert(DFTCONFIG_TABLE_NAME, null, value);
		
		//Pr�d�finition du d�marrage
		String[] ecran_demarrage = {"accueil", "projet", "groupe", "fiche", "configuration", "bilan"};
		for (int i=0; i<ecran_demarrage.length; i++) {
			value.clear();
			value.put(DEMARRAGE_ECRAN_DEMARRAGE, ecran_demarrage[i]);
			db.insert(DEMARRAGE_TABLE_NAME, null, value);
		}
		
		//Pr�d�finition de la s�curit�
		value.clear();
		value.put(SECURITE_MOT_DE_PASSE, "");
		value.put(SECURITE_ACTIVER_MDP, 0);
		db.insert(SECURITE_TABLE_NAME, null, value);
		
		//P�d�finition de l'encadrant
		value.clear();
		value.put(ENCADRANT_NOM, "nom");
		value.put(ENCADRANT_PRENOM, "prenom");
		value.put(ENCADRANT_EMAIL, "encadrant@parisdescartes.fr");
		value.put(ENCADRANT_DEMARRAGE_KEY, 1);
		db.insert(ENCADRANT_TABLE_NAME, null, value);
	}
	
	//M�thode appel�e � la mise � jour de la base de donn�es	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL(ANNEE_TABLE_DROP);
		db.execSQL(PROJET_TABLE_DROP);
		db.execSQL(ANNEEPROJET_TABLE_DROP);
		db.execSQL(GROUPE_TABLE_DROP);
		db.execSQL(ETUDIANT_TABLE_DROP);
		db.execSQL(ETUDIANTGROUPE_TABLE_DROP);
		db.execSQL(FICHE_TABLE_DROP);
		db.execSQL(EVALUATION_TABLE_DROP);
		db.execSQL(NOTE_TABLE_DROP);
		db.execSQL(COMMENTAIRE_TABLE_DROP);
		db.execSQL(DFTCONFIG_TABLE_DROP);
		db.execSQL(DEMARRAGE_TABLE_DROP);
		db.execSQL(SECURITE_TABLE_DROP);
		db.execSQL(ENCADRANT_TABLE_DROP);
		onCreate(db);
	}
	
	//M�thode pour activer la contrainte de cl� �trang�re � l'ouverture de la base de donn�es
	//Par d�faut la contrainte n'est pas active dans SQLite
	@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);
	    if (!db.isReadOnly()) {	        
	        db.execSQL("PRAGMA foreign_keys=ON;");
	    }
	}

}
