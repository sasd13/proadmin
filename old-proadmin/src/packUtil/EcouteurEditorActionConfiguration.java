package packUtil;

import packDatabase.DAOBase;
import packEntite.Encadrant;
import packEntite.Securite;

import com.example.gestionprojetandroid.R;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class EcouteurEditorActionConfiguration implements OnEditorActionListener {
	
	private Encadrant encadrant_temp;
	private Securite securite_temp;
	private EditText edit_securite_mdp;
	private DAOBase db;
	
	public EcouteurEditorActionConfiguration (Encadrant encadrant_temp, EditText edit_securite_mdp, Securite securite_temp, DAOBase db) {
		this.encadrant_temp = encadrant_temp;
		this.edit_securite_mdp = edit_securite_mdp;
		this.securite_temp = securite_temp;
		this.db = db;
	}

	@Override
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		
		//Si on confirme le text
		if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getAction() == KeyEvent.ACTION_DOWN) && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
				
				switch (view.getId()) {
				
					//Si EdiText Nom encadrant
					case R.id.config_encadrant_nom :				
						//Nom de l'encadrant
						if (!((EditText) view).getText().toString().isEmpty()) this.encadrant_temp.setNom(((EditText) view).getText().toString());	
						this.db.modifierEncadrant(this.encadrant_temp);
						break;
			
					//Si EdiText Prenom encadrant
					case R.id.config_encadrant_prenom :				
						//Prenom de l'encadrant
						if (!((EditText) view).getText().toString().isEmpty()) this.encadrant_temp.setPrenom(((EditText) view).getText().toString());	
						this.db.modifierEncadrant(this.encadrant_temp);
						break;
			
					//Si EdiText Email encadrant
					case R.id.config_encadrant_email :				
						//Email de l'encadrant
						if (!((EditText) view).getText().toString().isEmpty()) this.encadrant_temp.setEmail(((EditText) view).getText().toString());	
						this.db.modifierEncadrant(this.encadrant_temp);
						break;
			
					//Si EditText confirmer mot de passe
					case R.id.config_securite_confirm_mdp :		
						//Confirmation du mot de passe
						if (!((EditText) view).getText().toString().isEmpty()) {
							String mdp = this.edit_securite_mdp.getText().toString();
							String confirm_mdp = ((EditText) view).getText().toString();
					
							if (mdp.compareTo(confirm_mdp) == 0) {
								this.securite_temp.setMotDePasse(confirm_mdp);
								this.db.modifierSecurite(this.securite_temp);
							}
						}							
						break;
				}
				return true;
		}
		return false;
	}
}
