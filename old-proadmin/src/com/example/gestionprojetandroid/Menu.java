package com.example.gestionprojetandroid;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class Menu extends Activity implements OnClickListener, OnMenuItemClickListener{

	//-----------------------------------------------------//
		//-------------------- MENU OPTION --------------------//
		//-----------------------------------------------------//
		
		//Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du téléphone
	    public boolean onCreateOptionsMenu(Menu menu) {
	 
	        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
	        MenuInflater inflater = getMenuInflater();
	        //Instanciation du menu XML spécifier en un objet Menu
	        inflater.inflate(R.layout.menu_autre, (android.view.Menu) menu);
			return true;
	     }
	 @Override
	       //Méthode qui se déclenchera au clic sur un item
	      public boolean onOptionsItemSelected(MenuItem item) {
	         //On regarde quel item a été cliqué grâce à son id et on déclenche une action
	         switch (item.getItemId()) {
	         
			      case R.id.menu_quitter:
			    	  System.exit(0);
			    	  return true;
			    	  
			      case R.id.accueil:
			    	  Intent iAccueil = new Intent(this, Accueil.class);
			    	  finish();
			    	  startActivity(iAccueil);
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
	@Override
	public boolean onMenuItemClick(MenuItem arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

		
	    
}
