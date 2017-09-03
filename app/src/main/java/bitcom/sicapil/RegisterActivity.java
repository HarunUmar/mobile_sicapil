package bitcom.sicapil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bitcom.sicapil.util.AppController;

import static bitcom.sicapil.util.EndpointAPI.URL_BACKEND;

public class RegisterActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private  String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        LinearLayout body = (LinearLayout) findViewById(R.id.body);
        setupUI(body);

       final EditText txt_username = (EditText) findViewById(R.id.txt_nama);
       final EditText txt_email = (EditText) findViewById(R.id.txt_email);
       final EditText txt_nohp = (EditText) findViewById(R.id.txt_nomorhp);
       final EditText txt_password = (EditText) findViewById(R.id.txt_password);
       final EditText txt_confirm_password = (EditText) findViewById(R.id.txt_password_konfirmasi);

        Button btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama  = txt_username.getText().toString();
                String email = txt_email.getText().toString();
                String hp    = txt_nohp.getText().toString();
                String pass  = txt_password.getText().toString();
                String konfirmasi = txt_confirm_password.getText().toString();
                Daftar(nama,email,hp,pass,konfirmasi);
            }
        });
        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void Daftar(final String username, final String email, final String no_hp,final String password, final String confirm_password) {
        final ProgressDialog Ddialog = new ProgressDialog(this);
        Ddialog.setCancelable(false);
        Ddialog.setMessage("Proses Pendaftaran ...");
        Ddialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, URL_BACKEND+"register.php",new Response.Listener<String>(){
            @Override
            public void onResponse(String response) { Log.e(TAG, "Register Response: " + response.toString());
                Ddialog.dismiss();
                try {
                    JSONObject jObj = new JSONObject(response);
                    int success = jObj.getInt("success");
                    if (success == 1) {
                        Toast.makeText(getApplicationContext(), jObj.getString("message"), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else { Toast.makeText(getApplicationContext(), jObj.getString("message"), Toast.LENGTH_LONG).show();}
                } catch (JSONException e) { e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
               Ddialog.dismiss(); }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("pilih","reg");
                params.put("nama", username);
                params.put("email",email);
                params.put("password", password);
                params.put("confirm_password", confirm_password);
                params.put("no_hp",no_hp);
                return params; }};
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(RegisterActivity.this);
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
