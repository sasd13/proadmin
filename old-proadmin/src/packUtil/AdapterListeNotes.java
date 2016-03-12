package packUtil;

import java.util.ArrayList;

import packDatabase.DAOBase;
import packEntite.Etudiant;
import packEntite.Fiche;
import packEntite.Note;

import com.example.gestionprojetandroid.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

public class AdapterListeNotes extends ArrayAdapter<Note> {

    private Context context;
    private ArrayList<Note> liste_notes;
    private Fiche fiche_temp;
    private DAOBase db;
    

    public AdapterListeNotes(Context context, int textViewResourceId, ArrayList<Note> liste_notes, Fiche fiche_temp, DAOBase db) {
        super(context, textViewResourceId, liste_notes);
        
        this.context = context;
        this.liste_notes = liste_notes;
        this.fiche_temp = fiche_temp;
        this.db = db;		
    }

    // We keep this ViewHolder object to save time. It's quicker than findViewById() when repainting.
    static class ViewHolder {
        protected TextView text;
        protected Spinner spin;
    }

    @SuppressWarnings("unused")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));
        
        if (convertView == null) {

            LayoutInflater inflator = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.linearlayout_item_listview_notes, null);

            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.listViewNote_etudiant);
            holder.spin = (Spinner) convertView.findViewById(R.id.listViewNote_note);
            ArrayList<String> list_items = new ArrayList<String>();
            list_items.add("absent");
            list_items.add("---");
            list_items.add("--");
            list_items.add("-");
            list_items.add("=");
            list_items.add("+");
            list_items.add("++");
            list_items.add("+++");
            SpinnerTri spinner_tri_note = new SpinnerTri(this.context, holder.spin, list_items);
            convertView.setTag(holder);

            holder.spin.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					
					Note note = (Note) arg0.getTag();
					note.setNote(arg2);
                    
					long fiche_key = AdapterListeNotes.this.db.selectKeyFiche(AdapterListeNotes.this.fiche_temp.getGroupeKey(), AdapterListeNotes.this.fiche_temp.getNumSemaine());
					
					AdapterListeNotes.this.db.modifierNote(fiche_key, note);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					//DO NOTHING					
				}
            });
        } 
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Note note = this.liste_notes.get(position);
        Etudiant etu = this.db.selectEtudiant(note.getEtudiantKey());
        
        holder.text.setText(etu.getNomEtudiant() + " " + etu.getPrenomEtudiant());
        holder.spin.setSelection((int) note.getNote());
        holder.spin.setTag(note);       

        return convertView;
    }
}
