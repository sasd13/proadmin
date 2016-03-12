package packUtil;

import com.example.gestionprojetandroid.R;

import packDatabase.DAOBase;
import packEntite.Evaluation;
import packEntite.Fiche;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class EcouteurRatingBarFiche implements OnRatingBarChangeListener {

	private Evaluation evaluation_temp;
	private Fiche fiche_temp;
	private DAOBase db;
	
	public EcouteurRatingBarFiche (Fiche fiche_temp, Evaluation evaluation_temp, DAOBase db) {
		this.evaluation_temp = evaluation_temp;
		this.fiche_temp = fiche_temp;
		this.db = db;
	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
		long note = (long) rating;
		
		switch (ratingBar.getId()) {
		
			//Note planning
			case R.id.fiche_ratingBar_planning:
				this.evaluation_temp.setNotePLanning(note);
				break;
				
			//Note planning
			case R.id.fiche_ratingBar_communication:
				this.evaluation_temp.setNoteCommunication(note);
				break;
		}
		
		long fiche_key = this.db.selectKeyFiche(this.fiche_temp.getGroupeKey(), this.fiche_temp.getNumSemaine());
		
		this.db.modifierEvaluation(fiche_key, this.evaluation_temp);
	}
}
