package bitcom.sicapil.page;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

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
import bitcom.sicapil.adapter.AdapterInfoPengurusan;
import bitcom.sicapil.data.DataInfoPengurusan;
import bitcom.sicapil.util.AppController;

import static bitcom.sicapil.util.EndpointAPI.URL_BACKEND;

public class PageInfo extends Fragment {

    private Animation animation;
    private TextView page_label;

    private RecyclerView iRecyclerView;
    private RecyclerView.Adapter iAdapter;
    private RecyclerView.LayoutManager iLayoutManager;
    private List<DataInfoPengurusan> iDataset = new ArrayList<DataInfoPengurusan>();
    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.page_info, container, false);

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
        iRecyclerView = (RecyclerView) view.findViewById(R.id.info_recycler_view);
        iRecyclerView.setHasFixedSize(true);
        iLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        iRecyclerView.setLayoutManager(iLayoutManager);
        iAdapter = new AdapterInfoPengurusan(getActivity(), iDataset);
        iRecyclerView.setAdapter(iAdapter);

        loadDataInfoPengurusan();
        return view;

    }




    public void loadDataInfoPengurusan() {
        JsonArrayRequest arrReq = new JsonArrayRequest(URL_BACKEND +"info.php",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    DataInfoPengurusan dt = new DataInfoPengurusan();
                                    dt.set_id_pengurusan(obj.getString("id_pengurusan"));
                                    dt.set_jenis_pengurusan(obj.getString("jenis_pengurusan"));
                                    dt.set_tgl_pengurusan(obj.getString("tgl_pengurusan"));
                                    dt.set_status_verifikasi(obj.getString("status_verifikasi"));

                                    iDataset.add(dt);
                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                                iAdapter.notifyDataSetChanged();
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

}
