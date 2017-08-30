package bitcom.sicapil.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import bitcom.sicapil.R;
import bitcom.sicapil.data.DataJenisPengurusan;

public class AdapterItemDefault extends RecyclerView.Adapter<AdapterItemDefault.ViewHolder> {

    Activity activity;
    private List<DataJenisPengurusan> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout body_item;

        public ViewHolder(View v) {
            super(v);
            body_item = (LinearLayout) v.findViewById(R.id.body_item);
        }
    }

    public AdapterItemDefault(Activity activity, List<DataJenisPengurusan> mDataset) {
        this.activity = activity;
        this.mDataset = mDataset;
    }

    @Override
    public AdapterItemDefault.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_default, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterItemDefault.ViewHolder holder, final int position) {
        DataJenisPengurusan data = mDataset.get(position);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
