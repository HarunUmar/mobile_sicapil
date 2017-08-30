package bitcom.sicapil.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

import bitcom.sicapil.R;

public class Utils {

    public static final String ATTRIBUTE_TTF_KEY = "ttf_name";
    public static final String ATTRIBUTE_SCHEMA = "http://schemas.android.com/apk/lib/com.bitcom.sicapil.util";
    private static Map<String, Typeface> TYPEFACE = new HashMap<String, Typeface>();

    public static Typeface getFonts(Context context, String fontName) {
        Typeface typeface = TYPEFACE.get(fontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "font/"
                    + fontName);
            TYPEFACE.put(fontName, typeface);
        }
        return typeface;
    }

    public static void switchFragment(int id, Fragment fragment,
                                      FragmentActivity activity) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_left,
                R.anim.slide_out_left);
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

}
