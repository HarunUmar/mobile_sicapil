package bitcom.sicapil;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import bitcom.sicapil.page.PageHome;
import bitcom.sicapil.page.PageInfo;
import bitcom.sicapil.page.PageSetting;
import bitcom.sicapil.page.PageTentang;
import bitcom.sicapil.util.Session;
import bitcom.sicapil.util.Utils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Animation animation;
    private RelativeLayout loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView user_nama = (TextView) header.findViewById(R.id.user_nama);
        TextView user_email = (TextView) header.findViewById(R.id.user_email);
        Session sesi = new Session(getApplicationContext(),MainActivity.this);
        HashMap<String,String> user = sesi.getUserDetails();
        user_nama.setText(user.get("nama"));
        user_email.setText(user.get("email"));

        loader = (RelativeLayout) findViewById(R.id.loader);
        goPageHome();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Utils.switchFragment(R.id.frag_container, new PageTentang(), this);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Utils.switchFragment(R.id.frag_container, new PageHome(), this);
        } else if (id == R.id.nav_info) {
            Utils.switchFragment(R.id.frag_container, new PageInfo(), this);
        } else if (id == R.id.nav_setting) {
            Utils.switchFragment(R.id.frag_container, new PageSetting(), this);
        } else if (id == R.id.nav_tentang) {
            Utils.switchFragment(R.id.frag_container, new PageTentang(), this);
        } else if (id == R.id.nav_keluar) {
            Session session = new Session(getApplicationContext(),MainActivity.this);
            session.Keluar();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void goPageHome() {
        Utils.switchFragment(R.id.frag_container, new PageHome(), this);
    }

    public void showLoader() {
        loader.setClickable(true);
        loader.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(this,
                R.anim.fade_in);
        loader.startAnimation(animation);
    }

    public void hideLoader() {
        loader.setClickable(false);
        animation = AnimationUtils.loadAnimation(this,
                R.anim.fade_out);
        loader.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                loader.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
