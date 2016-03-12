package packUtil;

import java.util.ArrayList;

import com.example.gestionprojetandroid.R;

import packDatabase.DAOBase;
import packEntite.Etudiant;
import packEntite.Groupe;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class EcouteurEditorActionEtudiant implements OnEditorActionListener {
	
	private Groupe groupe_temp;
	private Etudiant etudiant_temp;
	private DAOBase db;
	
	public EcouteurEditorActionEtudiant (Groupe groupe_temp, Etudiant etudiant_temp, DAOBase db) {
		this.groupe_temp = groupe_temp;
		this.etudiant_temp = etudiant_temp;
		this.db = db;
	}

	@Override
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		
		long groupe_key = this.db.selectKeyGroupe(this.groupe_temp.getAnnee(), this.groupe_temp.getIdGroupe());
		
		//Si on confirme le text
		if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getAction() == KeyEvent.ACTION_DOWN) && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
				
				//Verification de la cle de l'etudiant
				long etudiant_key = this.db.selectKeyEtudiant(this.etudiant_temp.getNomEtudiant(), this.etudiant_temp.getPrenomEtudiant());
				
				//Si EditText nom etudiant
				if (view.getId() == R.id.listViewEtudiant_nom) {
					if (!((EditText) view).getText().toString().isEmpty()) this.etudiant_temp.setNomEtudiant(((EditText) view).getText().toString());
				}
				//Sinon EditText prenom etudiant
				else {
					if (!((EditText) view).getText().toString().isEmpty()) this.etudiant_temp.setPrenomEtudiant(((EditText) view).getText().toString());
				}	
				
				//Si l'etudiant existe
				if(etudiant_key != 0) {
					
					//On le modifie dans la base
					this.db.modifierEtudiant(etudiant_key, this.etudiant_temp);
				}
				else {
					if ((this.etudiant_temp.getNomEtudiant().compareTo("nom") != 0) && (this.etudiant_temp.getPrenomEtudiant().compareTo("prenom") != 0)) {
						
						ArrayList<Etudiant> liste_etudiant = this.db.selectListeEtudiant(groupe_key);
						
						boolean go = true;
						for(int i=0; i<liste_etudiant.size(); i++) {
							if ((liste_etudiant.get(i).getNomEtudiant().compareTo(this.etudiant_temp.getNomEtudiant()) == 0) && (liste_etudiant.get(i).getPrenomEtudiant().compareTo(this.etudiant_temp.getPrenomEtudiant()) == 0)) {
								go = false;
							}
						}
						
						if (go == true) {
							//Nombre d'etudiants
							this.groupe_temp.setNbrEtudiant(this.groupe_temp.getNbrEtudiant() + 1);
							
							//On cree l'etudiant
							this.db.ajouterEtudiant(groupe_key, this.etudiant_temp);
						}
					}
				}
				return true;				
		}		
		return false;
	}
}
