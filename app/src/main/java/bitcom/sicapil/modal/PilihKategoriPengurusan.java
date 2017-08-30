package bitcom.sicapil.modal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import bitcom.sicapil.R;
import bitcom.sicapil.adapter.AdapterSpKategoriPengurusan;
import bitcom.sicapil.adapter.AdapterSpKepemilikanBerkas;

public class PilihKategoriPengurusan extends AppCompatActivity {

    private EditText sipemilik;

    Spinner sp_kepemilikan_berkas;
    String list_sp_kepemilikan_berkas[] = { "Pribadi","Keluarga","Kerabat","Tetangga" };

    Spinner sp_kategori_pengurusan;
    String list_sp_kategori_pengurusan[] = { "Baru","Perpanjang","Hilang" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_kategori);
        LinearLayout body = (LinearLayout) findViewById(R.id.body);
        setupUI(body);
        Intent i = getIntent();
        String getTitle = i.getStringExtra("title");
        TextView title = (TextView) findViewById(R.id.txt_title);
        title.setText(getTitle);

        sipemilik = (EditText) findViewById(R.id.kepemilikan_berkas);

        sp_kepemilikan_berkas = (Spinner) findViewById(R.id.sp_kepemilikan_berkas);
        AdapterSpKepemilikanBerkas adapter = new AdapterSpKepemilikanBerkas(getApplicationContext(), list_sp_kepemilikan_berkas);
        sp_kepemilikan_berkas.setAdapter(adapter);
        sp_kepemilikan_berkas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_kategori_pengurusan = (Spinner) findViewById(R.id.sp_kategori_pengurusan);
        AdapterSpKategoriPengurusan adapter2 = new AdapterSpKategoriPengurusan(getApplicationContext(), list_sp_kategori_pengurusan);
        sp_kategori_pengurusan.setAdapter(adapter2);
        sp_kategori_pengurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btn_mulai_pengurusan = (Button) findViewById(R.id.btn_mulai_pengurusan);
        btn_mulai_pengurusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Mulai Pengurusan", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(PilihKategoriPengurusan.this);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}
