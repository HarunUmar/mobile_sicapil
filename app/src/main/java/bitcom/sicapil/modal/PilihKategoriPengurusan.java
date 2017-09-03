package bitcom.sicapil.modal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bitcom.sicapil.MainActivity;
import bitcom.sicapil.R;
import bitcom.sicapil.adapter.AdapterSpKategoriPengurusan;
import bitcom.sicapil.adapter.AdapterSpKepemilikanBerkas;
import bitcom.sicapil.data.DataKategoriPengurusan;
import bitcom.sicapil.util.AppController;

import static bitcom.sicapil.util.EndpointAPI.URL_BACKEND;

public class PilihKategoriPengurusan extends AppCompatActivity {

    private EditText sipemilik;
    Spinner sp_kepemilikan_berkas;
    String list_sp_kepemilikan_berkas[] = { "Pribadi","Keluarga","Kerabat","Tetangga" };
    Spinner sp_kategori_pengurusan;
    private static String TAG = MainActivity.class.getSimpleName();
    private List<DataKategoriPengurusan> kDataset = new ArrayList<DataKategoriPengurusan>();
    AdapterSpKategoriPengurusan adapter2;
    private String id_kategori_pengurusan;
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
        final AdapterSpKepemilikanBerkas adapter = new AdapterSpKepemilikanBerkas(getApplicationContext(), list_sp_kepemilikan_berkas);
        sp_kepemilikan_berkas.setAdapter(adapter);
        sp_kepemilikan_berkas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                if(position == 0){
                    sipemilik.setText("nama from session");
                    sipemilik.setInputType(InputType.TYPE_NULL);
                }
                else{
                    sipemilik.setText(null);
                    sipemilik.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_kategori_pengurusan = (Spinner) findViewById(R.id.sp_kategori_pengurusan);
        adapter2 = new AdapterSpKategoriPengurusan(getApplicationContext(),kDataset);
        sp_kategori_pengurusan.setAdapter(adapter2);
        sp_kategori_pengurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_kategori_pengurusan = kDataset.get(position).get_id_kategori();
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

                Toast.makeText(getApplicationContext(), id_kategori_pengurusan, Toast.LENGTH_SHORT).show();
            }
        });
        String getId_Jenis_Pengurusan = i.getStringExtra("id_jenis_pengurusan");
        loadDataKatagoriPengurusan(getId_Jenis_Pengurusan);
    }

    public void loadDataKatagoriPengurusan(String id) {
        JsonArrayRequest arrReq = new JsonArrayRequest(URL_BACKEND +"get_kat_pengurusan.php?id="+id,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    DataKategoriPengurusan dk = new DataKategoriPengurusan();
                                    dk.set_id_jenis_pengurusan(obj.getString("id_jenis_pengurusan"));
                                    dk.set_id_kategori(obj.getString("id_kategori"));
                                    dk.set_nama_kategori(obj.getString("nama_kategori"));
                                    kDataset.add(dk);
                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                                adapter2.notifyDataSetChanged();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(arrReq);
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
