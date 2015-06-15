package proadmin.gui.widget.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.android.proadmin.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    private ListAbstractRecyclerItems listAbstractRecyclerItem;
    private int itemStubLayout;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewStub stub;

        public ViewHolder(View view) {
            super(view);

            stub = (ViewStub) view.findViewById(R.id.recyclerviewitem_viewstub);
        }
    }

    public RecyclerAdapter(ListAbstractRecyclerItems listAbstractRecyclerItem, int itemStubLayout) {
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

        try {
            abstractRecyclerItem.inflate(((ViewHolder) viewHolder).stub);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.listAbstractRecyclerItem.size();
    }
}
