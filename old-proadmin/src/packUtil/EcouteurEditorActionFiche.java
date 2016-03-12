package packUtil;

import packDatabase.DAOBase;
import packEntite.Commentaire;
import packEntite.Evaluation;
import packEntite.Fiche;

import com.example.gestionprojetandroid.R;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class EcouteurEditorActionFiche implements OnEditorActionListener {
	
	private Fiche fiche_temp;
	private Evaluation evaluation_temp;
	private Commentaire commentaire_temp;
	private DAOBase db;
	
	public EcouteurEditorActionFiche (Fiche fiche_temp, Evaluation evaluation_temp, Commentaire commentaire_temp, DAOBase db) {
		this.fiche_temp = fiche_temp;
		this.evaluation_temp = evaluation_temp;
		this.commentaire_temp = commentaire_temp;
		this.db = db;
	}

	@Override
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		
		//Si on confirme le text
		if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getAction() == KeyEvent.ACTION_DOWN) && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
				
				long fiche_key = this.db.selectKeyFiche(this.fiche_temp.getGroupeKey(), this.fiche_temp.getNumSemaine());
				
				switch (view.getId()) {
				
					//Si EditText commentaire planning
					case R.id.fiche_editText_commentaires_planning :				
						
						if (!((EditText) view).getText().toString().isEmpty()) this.evaluation_temp.setCommentairePlanning(((EditText) view).getText().toString());	
						if (fiche_key != 0) this.db.modifierEvaluation(fiche_key, this.evaluation_temp);					
						break;			
					
					//Si EditText commentaire communication
					case R.id.fiche_editText_commentaires_communication :				
											
						if (!((EditText) view).getText().toString().isEmpty()) this.evaluation_temp.setCommentaireCommunication(((EditText) view).getText().toString());	
						if (fiche_key != 0) this.db.modifierEvaluation(fiche_key, this.evaluation_temp);						
						break;				
				
					//Si EdiText commentaire
					case R.id.fiche_editText_commentaire :				
						
						if (!((EditText) view).getText().toString().isEmpty()) this.commentaire_temp.setCommentaire(((EditText) view).getText().toString());	
						if(fiche_key != 0) this.db.modifierCommentaire(fiche_key, this.commentaire_temp);
						break;
				}
				return true;
		}
		return false;
	}
}
