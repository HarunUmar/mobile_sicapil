package bitcom.sicapil.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import bitcom.sicapil.R;
import bitcom.sicapil.data.DataKategoriPengurusan;

public class AdapterSpKategoriPengurusan extends ArrayAdapter{

    Context c;
    TextView txtLabel;
    List<DataKategoriPengurusan> Kitem;

    public AdapterSpKategoriPengurusan(Context ctx, List<DataKategoriPengurusan> item) {
        super(ctx, R.layout.item_sp_kategori_pengurusan,item);
        this.c = ctx;
        this.Kitem= item;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sp_kategori_pengurusan, null);
        }
        txtLabel = (TextView) convertView.findViewById(R.id.txtLabel);
        TampilKategori(txtLabel,position);
        return convertView;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sp_kategori_pengurusan, null);
        }
        txtLabel = (TextView) convertView.findViewById(R.id.txtLabel);
        TampilKategori(txtLabel,position);
        return convertView;
    }

    public void TampilKategori(TextView lebel, int posisi){
        DataKategoriPengurusan items = Kitem.get(posisi);
        lebel.setText(items.get_nama_kategori());

    }

}
