package packUtil;

import java.util.ArrayList;

import com.example.gestionprojetandroid.R;

import packEntite.ProjetSelect;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AdapterListeProjets extends ArrayAdapter<ProjetSelect> {

	private Context context;
	private ArrayList<ProjetSelect> liste_projets_select;
	
	public AdapterListeProjets(Context context, int textViewResourceId, ArrayList<ProjetSelect> liste_projets_select) {
		super(context, textViewResourceId, liste_projets_select);
		
		this.context = context;
		this.liste_projets_select = liste_projets_select;
	}
	
	public class ViewHolder {
		TextView text_idProjet;
		TextView text_nomProjet;
		TextView text_niveau;
		TextView text_description;			
		CheckBox checkbox;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Log.v("ConvertView", String.valueOf(position));
		 
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.linearlayout_item_listview_projet, null);
		 
			holder = new ViewHolder();
			holder.text_idProjet = (TextView) convertView.findViewById(R.id.listViewProjet_idProjet);
			holder.text_nomProjet = (TextView) convertView.findViewById(R.id.listViewProjet_nomProjet);
			holder.text_niveau = (TextView) convertView.findViewById(R.id.listViewProjet_niveau);
			holder.text_description = (TextView) convertView.findViewById(R.id.listViewProjet_description);
			holder.checkbox = (CheckBox) convertView.findViewById(R.id.listViewProjet_checkBox);
			convertView.setTag(holder);
		 
			holder.checkbox.setOnClickListener( new View.OnClickListener() { 
				
				public void onClick(View v) { 
					CheckBox cb = (CheckBox) v ; 
					ProjetSelect projet_select = (ProjetSelect) cb.getTag();
					projet_select.setSelected(cb.isChecked());
				} 
		    }); 
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		ProjetSelect projet_select = this.liste_projets_select.get(position);
		
		holder.text_idProjet.setText(projet_select.getIdProjet());
		holder.text_nomProjet.setText(projet_select.getTitre());
		holder.text_niveau.setText(projet_select.getNiveauProjet());
		holder.text_description.setText(projet_select.getDescription());
		holder.checkbox.setChecked(projet_select.getSelected());
		holder.checkbox.setTag(projet_select);
		 
		return convertView;
	}
}
