package com.example.usuario.favorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.usuario.favorapp.Clases.Transacciones;
import com.example.usuario.favorapp.Fragments.FragmentNotificaciones;
import com.example.usuario.favorapp.Fragments.ListaFavoresFragment;
import com.example.usuario.favorapp.Fragments.PerfilFragment;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private Transacciones tr = new Transacciones();
         public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FrameLayout fl = findViewById(R.id.FrFragment);
        fl.removeAllViews();
        manager.popBackStack();
        getFragmentManager().popBackStack();
        Fragment fragment = new PerfilFragment();
        transaction.replace(R.id.FrFragment, fragment);
        transaction.commit();
        tr.inicializatedFireBase(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FrameLayout fl = findViewById(R.id.FrFragment);
        fl.removeAllViews();
        manager.popBackStack();
        getFragmentManager().popBackStack();
        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_profile:{
                fragment = new PerfilFragment();
                break;
            }
            case R.id.nav_home:{
                fragment = new ListaFavoresFragment();
                break;
            }
           case R.id.nav_notifi:{
               fragment = new FragmentNotificaciones();
                break;
            }
            case R.id.nav_logout:{
                signout();
                break;
            }

        }

        if(fragment != null) {
            transaction.replace(R.id.FrFragment, fragment);
            transaction.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void signout(){
        tr.firebaseAuth.signOut();
        finish();
        startActivity(new Intent(NavigationActivity.this, ActivityLogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}
