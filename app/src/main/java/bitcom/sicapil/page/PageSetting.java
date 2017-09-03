package bitcom.sicapil.page;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import bitcom.sicapil.MainActivity;
import bitcom.sicapil.R;
import bitcom.sicapil.util.AppController;
import bitcom.sicapil.util.Session;

import static bitcom.sicapil.util.EndpointAPI.URL_BACKEND;

public class PageSetting extends Fragment {

    private Animation animation;
    private TextView page_label;
    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.page_setting, container, false);
        LinearLayout body = (LinearLayout) view.findViewById(R.id.body);
        setupUI(body);

        showLoader();

        page_label = (TextView) view.findViewById(R.id.page_label);

        if (savedInstanceState == null) {
            //animPageLabel();
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                hideLoader();
            }
        }, 1000);

        EditText txt_nama = (EditText) view.findViewById(R.id.txt_nama);
        EditText txt_email = (EditText) view.findViewById(R.id.txt_email);
        EditText txt_nomorhp = (EditText) view.findViewById(R.id.txt_nomorhp);
        Session sesi = new Session(getActivity().getApplicationContext(),getActivity());
        HashMap<String, String> user = sesi.getUserDetails();
        String id_user = user.get("id_user");
        loadDataUser(id_user.toString(),txt_nama,txt_email,txt_nomorhp);
        return view;
    }

    public void loadDataUser(final String id_user,final EditText nama, final EditText email, final EditText nomorhp) {
        JsonArrayRequest arrReq = new JsonArrayRequest(URL_BACKEND +"get_user.php?id="+id_user,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    nama.setText(obj.getString("nama"));
                                    email.setText(obj.getString("email"));
                                    nomorhp.setText(obj.getString("no_hp"));
                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                            }
                            hideLoader();
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

    private void animPageLabel() {
        animation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_left);
        page_label.startAnimation(animation);
    }

    public void showLoader() {
        ((MainActivity) getContext()).showLoader();
    }

    public void hideLoader() {
        ((MainActivity) getContext()).hideLoader();
    }

    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
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
