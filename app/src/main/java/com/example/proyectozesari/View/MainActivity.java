package com.example.proyectozesari.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyectozesari.Model.Historias;
import com.example.proyectozesari.Model.Sitios;
import com.example.proyectozesari.R;
import com.example.proyectozesari.Presenter.iFragmentsCommunicate;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements iFragmentsCommunicate {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    DetalleSitiosFragment detalleSitiosFragment;
    DetalleHistoriasFragment detalleHistoriasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#283593"));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Fragment fragment=null;
                switch (id){
                    case R.id.home:
                        fragment=new HomeFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.sitios:
                        fragment=new SitiosFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.historias:
                        fragment=new HistoriasFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.calendario:
                        fragment=new CalendarioFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.actividades:
                        //fragment=new HistoriasFragment();
                        //loadFragment(fragment);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }

    @Override
    public void detalleSitio(Sitios sitios) {
        detalleSitiosFragment = new DetalleSitiosFragment();
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("objeto", sitios);
        detalleSitiosFragment.setArguments(bundleEnvio);
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,detalleSitiosFragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }

    @Override
    public void detalleHistoria(Historias historias) {
        detalleHistoriasFragment = new DetalleHistoriasFragment();
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("objeto", historias);
        detalleHistoriasFragment.setArguments(bundleEnvio);
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,detalleHistoriasFragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }
}
