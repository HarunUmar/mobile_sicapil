package bitcom.sicapil.page;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import bitcom.sicapil.MainActivity;
import bitcom.sicapil.R;

public class PageInfo extends Fragment {

    private Animation animation;
    private TextView page_label;

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

        return view;

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
