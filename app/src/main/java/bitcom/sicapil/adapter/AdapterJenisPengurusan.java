package bitcom.sicapil.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import bitcom.sicapil.R;
import bitcom.sicapil.data.DataJenisPengurusan;
import bitcom.sicapil.modal.PilihKategoriPengurusan;

public class AdapterJenisPengurusan extends RecyclerView.Adapter<AdapterJenisPengurusan.ViewHolder> {

    Activity activity;
    private List<DataJenisPengurusan> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout body_item;
        public ImageView image_item;
        public TextView label_item;

        public ViewHolder(View v) {
            super(v);
            body_item = (LinearLayout) v.findViewById(R.id.body_item);
            image_item = (ImageView) v.findViewById(R.id.image_item);
            label_item = (TextView) v.findViewById(R.id.label_item);
        }
    }

    public AdapterJenisPengurusan(Activity activity, List<DataJenisPengurusan> mDataset) {
        this.activity = activity;
        this.mDataset = mDataset;
    }

    @Override
    public AdapterJenisPengurusan.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jenis_pengurusan, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterJenisPengurusan.ViewHolder holder, final int position) {
        final DataJenisPengurusan data = mDataset.get(position);

        holder.label_item.setText(data.get_jenis_pengurusan());

        holder.body_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPilihKategoriPengurusan(data.get_label(),data.get_id_jenis_pengurusan());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void openPilihKategoriPengurusan(String title, String id_jenis_pungurusan) {
        Intent i = new Intent(activity, PilihKategoriPengurusan.class);
        i.putExtra("title", title);
        i.putExtra("id_jenis_pengurusan",id_jenis_pungurusan);
        activity.startActivity(i);
    }

}
