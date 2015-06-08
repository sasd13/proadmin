package proadmin.gui.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.proadmin.R;

import java.util.ArrayList;

/**
 * Created by Samir on 22/03/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<AbstractRecyclerItem> listAbstractRecyclerItem;
    private int itemStubLayout;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewStub stub;

        public ViewHolder(View view) {
            super(view);

            stub = (ViewStub) view.findViewById(R.id.recyclerviewitem_viewstub);
        }
    }

    public RecyclerAdapter(ArrayList<AbstractRecyclerItem> listAbstractRecyclerItem, int itemStubLayout) {
        this.listAbstractRecyclerItem = listAbstractRecyclerItem;
        this.itemStubLayout = itemStubLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.itemStubLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        AbstractRecyclerItem abstractRecyclerItem = this.listAbstractRecyclerItem.get(position);

        if(abstractRecyclerItem.getView() == null) {
            abstractRecyclerItem.inflate(((ViewHolder) viewHolder).stub);
        }
    }

    @Override
    public int getItemCount() {
        return this.listAbstractRecyclerItem.size();
    }
}
