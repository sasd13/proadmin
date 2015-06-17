package proadmin.gui.widget.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;

import com.android.proadmin.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    private ListRecyclerItems listRecyclerItems;
    private int itemLayout;

    private View view;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewStub stub;

        public ViewHolder(View view) {
            super(view);

            stub = (ViewStub) view.findViewById(R.id.recyclerviewitem_viewstub);
        }
    }

    public RecyclerAdapter(ListRecyclerItems listRecyclerItems, int itemLayout) {
        this.listRecyclerItems = listRecyclerItems;
        this.itemLayout = itemLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.view = LayoutInflater.from(parent.getContext()).inflate(this.itemLayout, parent, false);

        return new ViewHolder(this.view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewParent viewParent = ((ViewHolder) viewHolder).stub.getParent();

        if (viewParent == null) {

        }

        try {
            this.listRecyclerItems.get(position).inflate(((ViewHolder) viewHolder).stub);
        } catch (NullPointerException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.listRecyclerItems.size();
    }
}
