package com.example.gestionprojetandroid;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.view.View;

//Menu
public class Accueil extends Activity {
	
// A. ------ Lorsque l'activit� est lanc�e la premi�re fois
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_principale);
				
		
		// 1.------- On r�cup�re ici tous les images et les textes cliquables dans le fichier page_principale.xml 
				
				// 1.a Boutons repr�sentant les fenetres du projets
					ImageView boutonAccesGroupe = (ImageView)findViewById(R.id.fen_groupe); 
					ImageView boutonAccesProjet = (ImageView)findViewById(R.id.fen_projet); 
					ImageView boutonAccesFiche = (ImageView)findViewById(R.id.fen_fiche); 
					ImageView boutonAccesCalendar = (ImageView)findViewById(R.id.fen_calendar); 
					
				/* 1.c Textes
					TextView AccesNouveauProjet = (TextView)findViewById(R.id.bouton_nouveau_projet);
					final TextView AccesListeProjet = (TextView)findViewById(R.id.bouton_liste_projets);
					TextView AccesCreerGroupe = (TextView)findViewById(R.id.bouton_creer_groupe);
					TextView AccesListeGroupe = (TextView)findViewById(R.id.bouton_liste_groupes);*/
			
			   // 1.d LinearLayout
					/*final LinearLayout layoutProjet1 = (LinearLayout)findViewById(R.id.include_linearlayout_);
					monLinearLayoutProjet1 = (LinearLayout) View.inflate(this,
							R.layout.linearlayout_liste_tous_les_projets, null);
					layoutProjet1.addView(monLinearLayoutProjet1);*/
					
					
				
		// 2.-------- DŽclaration d'un Ecouteur d'Žv�enement commun nommŽ : monEcouteur
					
				OnClickListener monEcouteur = new OnClickListener(){
					@Override
					public void onClick(View v){
						switch (v.getId()){
						
						// 2.a Images Fenetres
						case R.id.fen_groupe:// 2.a.a DŽmarrage de l'activitŽ @layout/activity_groupes.xml
							
							Intent intentSurGroupe = new Intent (getApplicationContext(), Groupes.class);
							startActivity(intentSurGroupe);
							break;
						case R.id.fen_projet: // 2.a.b ...
							Intent intentSurProjet = new Intent (getApplicationContext(), Projets.class);
							startActivity(intentSurProjet);
							break;
						case R.id.fen_fiche: // 2.a.c ...
							Intent intentSurFiche = new Intent (getApplicationContext(), FicheEvaluation.class);
							startActivity(intentSurFiche);
							break;
							
							
							// 2.b Boutons Textes sur Images
					/*	case R.id.bouton_nouveau_projet: // 2.b.a bouton acc�s Nouveau projet

							Intent t = new Intent (getApplicationContext(), Projets.class);
							String texte = "moi";
							t.putExtra("label",texte);
							startActivity(t);
							break;
							
						case R.id.bouton_liste_projets: // 2.b.b bouton acc�s liste projet
							
							Intent intentSurBtnListeProjet = new Intent (getApplicationContext(), Projets.class);
							
							startActivity(intentSurBtnListeProjet);
							break;
							
						case R.id.bouton_creer_groupe: // 2.b.c bouton acc�s Creer groupe
							// inclure le fichier linearlayout
							
							// ouvrir l'activitŽ
							Intent intentSurBtnCreerGroupe = new Intent (getApplicationContext(), Groupes.class);
							startActivity(intentSurBtnCreerGroupe);
							break;
							
						case R.id.bouton_liste_groupes: // 2.b.d bouton acc�s Creer groupe
							// inclure le fichier linearlayout
							
							// ouvrir l'activitŽ
							Intent intentSurBtnListeGroupe = new Intent (getApplicationContext(), Groupes.class);
							startActivity(intentSurBtnListeGroupe);
							break;*/
					
						}// Fin balise switch
					}// Fin public void onClick...

				};// Fin de notre ecouteur commun
				
				
		//3. --------- On affecte chaque ŽlŽment cliquable avec l'Žv�nement monEcouteur
				boutonAccesGroupe.setOnClickListener(monEcouteur); // Concerne l'affectation de l'Žcouteur sur le bouton d'acc�s a l'activitŽ @layout/activity_groupes.xml
				boutonAccesProjet.setOnClickListener(monEcouteur); // ...
				boutonAccesCalendar.setOnClickListener(monEcouteur); // ...
				boutonAccesFiche.setOnClickListener(monEcouteur); // ...
				//----------------------------------------------- //
			/*	AccesNouveauProjet.setOnClickListener(monEcouteur); // ...
				AccesListeProjet.setOnClickListener(monEcouteur); // ...
				AccesCreerGroupe.setOnClickListener(monEcouteur); // ...
				AccesListeGroupe.setOnClickListener(monEcouteur); // ...*/
				
			}
	    
//B. 
	//-----------------------------------------------------//
	//-------------------- MENU OPTION --------------------//
	//-----------------------------------------------------//
	
	//M�thode qui se d�clenchera lorsque vous appuierez sur le bouton menu du t�l�phone
    public boolean onCreateOptionsMenu(Menu menu) {
 
        //Cr�ation d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML sp�cifier en un objet Menu
        inflater.inflate(R.layout.menu_accueil, (android.view.Menu) menu);
		return true;
     }

       //M�thode qui se d�clenchera au clic sur un item
      public boolean onOptionsItemSelected(MenuItem item) {
         //On regarde quel item a �t� cliqu� gr�ce � son id et on d�clenche une action
    	  
         switch (item.getItemId()) {
   
		      case R.id.quitter:
		    	  System.exit(0);
		    	  break;
		    	
		    }
		         
         return false;
         }

      public void onPause(){
    	  super.onPause();
    	  System.exit(0);
      }
      
      public void onDestroy(){
    	  super.onDestroy();
    	  System.exit(0);
      }
 
}