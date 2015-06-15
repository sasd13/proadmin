package proadmin.gui.widget.spin;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 15/06/2015.
 */
public class Spin {

    private Context context;
    private List<String> listItems;

    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private AdapterView.OnItemSelectedListener onItemSelectedListener;

    public Spin(Context context) {
        this.context = context;
        this.listItems = new ArrayList<>();

        this.arrayAdapter = new ArrayAdapter(this.context, android.R.layout.simple_spinner_item, this.listItems);
        this.arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void addItem(String item) {
        this.listItems.add(item);
    }

    public String removeItem(int position) {
        return this.listItems.remove(position);
    }

    public String getItem(int position) {
        return this.listItems.get(position);
    }

    public String getSelectedItem() {
        return getItem(getSelectedItemPosition());
    }

    public int getSelectedItemPosition() {
        return this.spinner.getSelectedItemPosition();
    }

    public int size() {
        return this.listItems.size();
    }

    public void clear() {
        this.listItems.clear();
    }

    public void adapt(Spinner spinner, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.spinner = spinner;
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void validate() {
        this.spinner.setAdapter(this.arrayAdapter);
        this.spinner.setSelection(0, false);
        this.spinner.setOnItemSelectedListener(this.onItemSelectedListener);
    }
}
