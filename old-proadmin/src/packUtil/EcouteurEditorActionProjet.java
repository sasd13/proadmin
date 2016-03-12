package packUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import packDatabase.DAOBase;
import packEntite.Projet;

import com.example.gestionprojetandroid.R;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class EcouteurEditorActionProjet implements OnEditorActionListener {
	
	private long annee_selected;
	private Projet projet_temp;
	private DAOBase db;
	
	public EcouteurEditorActionProjet (long annee_selected, Projet projet_temp, DAOBase db) {
		this.annee_selected = annee_selected;
		this.projet_temp = projet_temp;
		this.db = db;
	}

	@Override
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		
		//Si on confirme le text
		if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getAction() == KeyEvent.ACTION_DOWN) && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
				
				long projet_key = this.db.selectKeyProjet(this.projet_temp.getTitre());
				
				switch (view.getId()) {
					
					//Nom du projet
					case R.id.projet_editText_nomProjet :
						if (!((EditText) view).getText().toString().isEmpty()) this.projet_temp.setTitre(((EditText) view).getText().toString());
						break;
						
					//Identifiant du projet
					case R.id.projet_editText_idProjet :
						if (!((EditText) view).getText().toString().isEmpty()) this.projet_temp.setIdProjet(((EditText) view).getText().toString());
						break;
						
					//Description du projet
					case R.id.projet_editText_description :	
						if (!((EditText) view).getText().toString().isEmpty()) this.projet_temp.setDescription(((EditText) view).getText().toString());
						break;
				}
		
				//Si le projet existe
				if (projet_key != 0) {
					
					//Modification de l'identifiant
					this.db.modifierProjet(this.annee_selected, projet_key, this.projet_temp);
				}
				//Sinon on le cree
				else {
					
					if ((this.projet_temp.getTitre().compareTo("Nom du projet") != 0) && (this.projet_temp.getIdProjet().compareTo("identifiant") != 0)) {
						
						//Annee en cours
						Date date_actu = new Date();
						SimpleDateFormat formater = new SimpleDateFormat("yyyy", Locale.FRENCH);
						long annee_actu = Long.parseLong(formater.format(date_actu));
						
						ArrayList<Projet> liste_projets = this.db.selectListeProjet(annee_actu);
						boolean go = true;
						for(int i=0; i<liste_projets.size(); i++) {
							if (liste_projets.get(i).getIdProjet().compareTo(this.projet_temp.getIdProjet()) == 0) {
								go = false;
							}
						}
						
						if (go == true) {
							
							//Ajout du projet dans la base
							this.db.ajouterProjet(annee_actu, this.projet_temp);
						}
					}
				}
				return true;
		}		
		return false;
	}
}
