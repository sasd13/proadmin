package packUtil;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerTri {
	
	private Context context;
	private Spinner spin;
	private ArrayList<String> list_items;

	public SpinnerTri (Context context, Spinner spin, ArrayList<String> list_items) {
		this.context = context;
		this.spin = spin;
		this.list_items = list_items;
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, this.list_items);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.spin.setAdapter(aa);
	}
}
