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

public class PilihKategoriPengurusan extends AppCompatActivity {

    private EditText Kepemilikan_berkas;
    private String[] germanFeminine = {
            "Milik Sendiri",
            "Orang Lain"
    };

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

        Kepemilikan_berkas = (EditText) findViewById(R.id.kepemilikan_berkas);
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

        Spinner pemilik_berkas = (Spinner) findViewById(R.id.sp_pemilik_berkas);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, germanFeminine);
        pemilik_berkas.setAdapter(adapter);
        pemilik_berkas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){


                }

                Toast.makeText(getApplicationContext(), "Pemilik Berkas  "+ adapter.getItem(i) +""+i+"", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
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
