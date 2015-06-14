package proadmin.gui.recycler.tab;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.android.proadmin.R;

import proadmin.gui.color.ColorOnTouchListener;
import proadmin.gui.recycler.AbstractRecyclerItem;

/**
 * <p>
 * Container item class for Tables view :
 * </p>
 * Created by Samir on 22/03/2015.
 */
public class TabItemProject extends AbstractRecyclerItem {

    private CharSequence title, grade;
    private int nbrSquads;
    private Intent intent;

    private TextView textViewTitle, textViewGrade, textViewNbrSquads;

    public TabItemProject() {
        super(R.layout.tabitem_project);

        this.title = "Title";
        this.grade = "Grade";
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        try {
            this.textViewTitle.setText(this.title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public CharSequence getGrade() {
        return this.grade;
    }

    public void setGrade(CharSequence grade) {
        this.grade = grade;

        try {
            this.textViewGrade.setText(this.grade);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public int getNbrSquads() {
        return this.nbrSquads;
    }

    public void setNbrSquads(int nbrSquads) {
        this.nbrSquads = nbrSquads;

        try {
            this.textViewNbrSquads.setText(Integer.toString(this.nbrSquads));
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
    public View inflate(ViewStub viewStub) {
        View view = super.inflate(viewStub);

        this.textViewTitle = (TextView) view.findViewById(R.id.tabitem_project_textview_title);
        this.textViewTitle.setText(this.title);

        this.textViewGrade = (TextView) view.findViewById(R.id.tabitem_project_textview_grade);
        this.textViewGrade.setText(this.grade);

        this.textViewNbrSquads = (TextView) view.findViewById(R.id.tabitem_project_textview_nbrsquads);
        this.textViewNbrSquads.setText(Integer.toString(this.nbrSquads));

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
        view.setOnTouchListener(new ColorOnTouchListener(color));

        return view;
    }
}
