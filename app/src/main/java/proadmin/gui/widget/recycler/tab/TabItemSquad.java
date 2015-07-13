package proadmin.gui.widget.recycler.tab;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.android.proadmin.R;

import proadmin.gui.color.ColorOnTouchListener;
import proadmin.gui.widget.recycler.RecyclerItem;

/**
 * <p>
 * Container item class for Tables view :
 * </p>
 * Created by Samir on 22/03/2015.
 */
public class TabItemSquad extends RecyclerItem {

    private CharSequence name, projectTitle;
    private Intent intent;

    private TextView textViewName, textViewProjectTitle;

    public TabItemSquad() {
        super(R.layout.tabitem_squad);
    }

    public CharSequence getName() {
        return this.name;
    }

    public void setName(CharSequence name) {
        this.name = name;

        try {
            this.textViewName.setText(this.name);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public CharSequence getProjectTitle() {
        return this.projectTitle;
    }

    public void setProjectTitle(CharSequence projectTitle) {
        this.projectTitle = projectTitle;

        try {
            this.textViewProjectTitle.setText(this.projectTitle);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        this.textViewName = (TextView) this.view.findViewById(R.id.tabitem_squad_textview_name);
        this.textViewName.setText(this.name);

        this.textViewProjectTitle = (TextView) this.view.findViewById(R.id.tabitem_squad_textview_projecttitle);
        this.textViewProjectTitle.setText(this.projectTitle);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
        int color = view.getContext().getResources().getColor(R.color.background_material_light);
        this.view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}
