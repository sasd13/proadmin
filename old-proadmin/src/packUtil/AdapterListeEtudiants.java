package packUtil;

import java.util.ArrayList;

import com.example.gestionprojetandroid.R;

import packDatabase.DAOBase;
import packEntite.Etudiant;
import packEntite.EtudiantSelect;
import packEntite.Groupe;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView.BufferType;

public class AdapterListeEtudiants extends ArrayAdapter<EtudiantSelect> {

	private Context context;
	private ArrayList<Etudiant> liste_etudiants;
	ArrayList<EtudiantSelect> liste_etudiants_select;
	private Groupe groupe_temp;
	private DAOBase db;
	
	public AdapterListeEtudiants(Context context, int textViewResourceId, ArrayList<Etudiant> liste_etudiants, ArrayList<EtudiantSelect> liste_etudiants_select, Groupe groupe_temp, DAOBase db) {
		super(context, textViewResourceId, liste_etudiants_select);
		
		this.context = context;
		this.liste_etudiants_select = liste_etudiants_select;
		this.liste_etudiants = liste_etudiants;
		this.groupe_temp = groupe_temp;
		this.db = db;
	}
	
	public class ViewHolder {
		EditText edit_nom;
		EditText edit_prenom;			
		CheckBox checkbox;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Log.v("ConvertView", String.valueOf(position));
		 
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.linearlayout_item_listview_etudiant, null);
		 
			holder = new ViewHolder();
			holder.edit_nom = (EditText) convertView.findViewById(R.id.listViewEtudiant_nom);
			holder.edit_prenom = (EditText) convertView.findViewById(R.id.listViewEtudiant_prenom);
			holder.checkbox = (CheckBox) convertView.findViewById(R.id.listViewEtudiant_checkBox);
			convertView.setTag(holder);
			
			EcouteurEditorActionEtudiant ecouteur_editText = new EcouteurEditorActionEtudiant(this.groupe_temp, this.liste_etudiants.get(position), this.db);
			holder.edit_nom.setOnEditorActionListener(ecouteur_editText);
			holder.edit_prenom.setOnEditorActionListener(ecouteur_editText);
		 
			holder.checkbox.setOnClickListener( new View.OnClickListener() { 
				
				public void onClick(View v) { 
					CheckBox cb = (CheckBox) v ; 
					EtudiantSelect etudiant_select = (EtudiantSelect) cb.getTag();
					etudiant_select.setSelected(cb.isChecked());
				} 
		    }); 
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Etudiant etudiant = this.liste_etudiants.get(position);
		EtudiantSelect etudiant_select = this.liste_etudiants_select.get(position);
		
		if (etudiant.getNomEtudiant().compareTo("nom") != 0) holder.edit_nom.setText(this.liste_etudiants.get(position).getNomEtudiant(), BufferType.EDITABLE);
		else holder.edit_nom.setText("", BufferType.EDITABLE);
			
		if (etudiant.getPrenomEtudiant().compareTo("prenom") != 0) holder.edit_prenom.setText(this.liste_etudiants.get(position).getPrenomEtudiant(), BufferType.EDITABLE);
		else holder.edit_prenom.setText("", BufferType.EDITABLE);
		
		holder.checkbox.setChecked(etudiant_select.getSelected());
		holder.checkbox.setTag(etudiant_select);
		 
		return convertView;
	}
}
