package bitcom.sicapil.page;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
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

import bitcom.sicapil.MainActivity;
import bitcom.sicapil.R;

public class PageSetting extends Fragment {

    private Animation animation;
    private TextView page_label;

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
