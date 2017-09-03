package bitcom.sicapil.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import bitcom.sicapil.LoginActivity;
import bitcom.sicapil.MainActivity;
public class Session {

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    Activity activity;
    public static final String PREF_NAME = "sicapil";

    public Session(Context context, Activity activity){
        this.activity = activity;
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void BuatSession(String nama, String email, String id){
        editor.putBoolean("sicapil", true);
        editor.putString("nama", nama);
        editor.putString("email", email);
        editor.putString("id_user", id);
        editor.commit();
        Intent i = new Intent(_context,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
        activity.finish();

    }
    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            activity.finish();
        }
        else {
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            activity.finish();
        }
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("nama", pref.getString("nama", null));
        user.put("email", pref.getString("email", null));
        user.put("id_user", pref.getString("id_user", null));
        return user;
    }
    public void Keluar(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
        activity.finish();
    }
    public boolean isLoggedIn(){
        return pref.getBoolean("sicapil", false);
    }
}