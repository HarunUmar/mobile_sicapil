package bitcom.sicapil.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import bitcom.sicapil.R;

public class AdapterSpKategoriPengurusan extends ArrayAdapter<String> {

    Context c;
    String[] item;

    public AdapterSpKategoriPengurusan(Context ctx, String[] item) {
        super(ctx, R.layout.item_sp_kategori_pengurusan, item);
        this.c = ctx;
        this.item = item;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sp_kategori_pengurusan, null);
        }
        TextView txtLabel = (TextView) convertView.findViewById(R.id.txtLabel);

        txtLabel.setText(item[position]);

        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sp_kategori_pengurusan, null);
        }
        TextView txtLabel = (TextView) convertView.findViewById(R.id.txtLabel);

        txtLabel.setText(item[position]);

        return convertView;
    }
}
