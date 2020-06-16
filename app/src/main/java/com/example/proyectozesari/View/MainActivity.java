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

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyectozesari.Helpers.DatabaseHelper;
import com.example.proyectozesari.Helpers.MunicipioBD;
import com.example.proyectozesari.Helpers.SitioBD;
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
    DatabaseHelper database;

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

        database = new DatabaseHelper(this);
        guardarSitioRestaurantes();
        guardarMunicipios();
        guardarSitioHoteles();

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
                Bundle bundle = new Bundle();
                switch (id){
                    case R.id.home:
                        fragment=new HomeFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.sitios:
                        fragment=new MunicipiosFragment();
                        bundle.putString("opcion", "Sitios");
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                        break;
                    case R.id.historias:
                        fragment=new MunicipiosFragment();
                        bundle.putString("opcion", "Historias");
                        fragment.setArguments(bundle);
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

    public void guardarSitioRestaurantes(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idSitio;
        ContentValues values =  new ContentValues();
        values.put(SitioBD.SITIO_ID,0);
        values.put(SitioBD.SITIO_NAME,"Restaurante la Brasa");
        values.put(SitioBD.SITIO_DESCRIPCION,"Ofrece comida para llevar, reservas, acceso para silla de ruedas, servicio de mesa, asientos. Buenos platos como desayunos, brunches, almuerzos, cenas, platos únicos en su sector y a buen precio. Buena Infraestructura y ambiente agradable, ubicación y parqueadero de fácil acceso.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Aguachica");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 40 Vía al Mar Entre Calle 3 y 4");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.parque_monedas);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,1);
        values.put(SitioBD.SITIO_NAME,"Restaurante Montacarga del Norte");
        values.put(SitioBD.SITIO_DESCRIPCION,"Entrega a domicilio, comida para llevar, asientos al aire libre, acceso para silla de ruedas, servicio de mesa. Sus almuerzos generosos en cuanto a calidad con porciones muy grandes y precios muy adecuados. Posee platos típicos particulares (Asados variados, ricos jugos, entre otros). Considerado uno de los mejores de Valledupar, desde el servicio hasta el pago.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 19 # 5 - 30");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.balneario_vega);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);
    }

    public void guardarSitioHoteles(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idSitio;
        ContentValues values =  new ContentValues();
        values.put(SitioBD.SITIO_ID,2);
        values.put(SitioBD.SITIO_NAME,"Hotel Tativán");
        values.put(SitioBD.SITIO_DESCRIPCION,"Este hotel moderno y tranquilo se encuentra a 7 minutos a pie de la Plaza Alfonso López y a 9 minutos a pie de los objetos históricos del Museo Arqueológico del César. Las habitaciones luminosas se encuentran repartidas en 2 torres y cuentan con Wi-Fi gratis, TV por cable y minibar. Las habitaciones mejoradas cuentan con área de visitas y las suites incluyen sala de estar. Se ofrece servicio a la habitación. El hotel cuenta con un restaurante luminoso con vistas panorámicas de la ciudad, además de un bar de cocteles con jardín en la terraza. Hay una piscina al aire libre con una fuente y un solárium. Otros servicios incluyen un sauna seco y un sauna húmedo, además de un centro de negocios. Se ofrece servicio de lavandería por una tarifa adicional. Alojamientos de aproximadamente $ 146. 205.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 16a #9-50");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.parque_monedas);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,3);
        values.put(SitioBD.SITIO_NAME,"Hotel Monterrey Plus-Aguachica");
        values.put(SitioBD.SITIO_DESCRIPCION,"El hotel Monterrey es una alternativa muy llamativa por la relación calidad precio, por $35.000, ofrece una habitación cómoda, limpia, con aire acondicionado, televisión y baño privado, muy confortable, servicio excelente de parqueadero. Brinda un ambiente hogareño con excelente comodidad. Sin servicio de restaurante.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Aguachica");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 3 #39-91");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.balneario_vega);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);
    }

    public void guardarMunicipios(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idMunicipio;
        ContentValues values =  new ContentValues();
        values.put(MunicipioBD.MUNICIPIOS_ID,0);
        values.put(MunicipioBD.MUNICIPIOS_NAME,"Aguachica");
        idMunicipio = db.insert(MunicipioBD.TABLE_MUNICIPIOS, null, values);

        values.put(MunicipioBD.MUNICIPIOS_ID,1);
        values.put(MunicipioBD.MUNICIPIOS_NAME,"Bosconia");
        idMunicipio = db.insert(MunicipioBD.TABLE_MUNICIPIOS, null, values);

        values.put(MunicipioBD.MUNICIPIOS_ID,2);
        values.put(MunicipioBD.MUNICIPIOS_NAME,"Codazzi");
        idMunicipio = db.insert(MunicipioBD.TABLE_MUNICIPIOS, null, values);

        values.put(MunicipioBD.MUNICIPIOS_ID,3);
        values.put(MunicipioBD.MUNICIPIOS_NAME,"El Copey");
        idMunicipio = db.insert(MunicipioBD.TABLE_MUNICIPIOS, null, values);

        values.put(MunicipioBD.MUNICIPIOS_ID,4);
        values.put(MunicipioBD.MUNICIPIOS_NAME,"La Paz");
        idMunicipio = db.insert(MunicipioBD.TABLE_MUNICIPIOS, null, values);

        values.put(MunicipioBD.MUNICIPIOS_ID,5);
        values.put(MunicipioBD.MUNICIPIOS_NAME,"Manaure");
        idMunicipio = db.insert(MunicipioBD.TABLE_MUNICIPIOS, null, values);

        values.put(MunicipioBD.MUNICIPIOS_ID,6);
        values.put(MunicipioBD.MUNICIPIOS_NAME,"Pueblo Bello");
        idMunicipio = db.insert(MunicipioBD.TABLE_MUNICIPIOS, null, values);

        values.put(MunicipioBD.MUNICIPIOS_ID,7);
        values.put(MunicipioBD.MUNICIPIOS_NAME,"Valledupar");
        idMunicipio = db.insert(MunicipioBD.TABLE_MUNICIPIOS, null, values);
    }
}
