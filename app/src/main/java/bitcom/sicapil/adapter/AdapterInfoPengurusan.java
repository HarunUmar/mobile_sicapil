package bitcom.sicapil.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.List;
import bitcom.sicapil.R;
import bitcom.sicapil.data.DataInfoPengurusan;

public class AdapterInfoPengurusan extends RecyclerView.Adapter<AdapterInfoPengurusan.ViewHolder> {

    Activity activity;
    private List<DataInfoPengurusan> iDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TableLayout body_table;
        public TextView txt_no,txt_jenis_pengurusan,txt_tgl_pengurusan,txt_status_verifikasi;

        public ViewHolder(View v) {
            super(v);
            body_table = (TableLayout) v.findViewById(R.id.body_table);
            txt_no = (TextView) v.findViewById(R.id.txt_no);
            txt_jenis_pengurusan = (TextView) v.findViewById(R.id.txt_jenis_pengurusan);
            txt_tgl_pengurusan = (TextView) v.findViewById(R.id.txt_tgl_pengurusan);
            txt_status_verifikasi = (TextView) v.findViewById(R.id.txt_status_verifikasi);
        }
    }
    public AdapterInfoPengurusan(Activity activity, List<DataInfoPengurusan> iDataset) {
        this.activity = activity;
        this.iDataset = iDataset;
    }
    @Override
    public AdapterInfoPengurusan.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tabel_info, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final AdapterInfoPengurusan.ViewHolder holder, final int position) {
        final DataInfoPengurusan data = iDataset.get(position);
        holder.txt_no.setText(String.valueOf(position+1));
        holder.txt_jenis_pengurusan.setText(data.jenis_pengurusan);
        holder.txt_tgl_pengurusan.setText(data.tgl_pengurusan);
        holder.txt_status_verifikasi.setText(data.get_status_verifikasi());
    }
    @Override
    public int getItemCount() {return iDataset.size();}


}
