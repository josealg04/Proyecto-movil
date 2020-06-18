package com.example.proyectozesari.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyectozesari.Helpers.ActividadBD;
import com.example.proyectozesari.Helpers.DatabaseHelper;
import com.example.proyectozesari.Helpers.HistoriaBD;
import com.example.proyectozesari.Helpers.MunicipioBD;
import com.example.proyectozesari.Helpers.SitioBD;
import com.example.proyectozesari.Model.Actividades;
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
    DetalleActividadesFragment detalleActividadesFragment;
    DatabaseHelper database;
    final private int REQUEST_CODE_ASK_PERMISSION=111;

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
        guardarSitioBares();
        guardarMonumentos();
        guardarLeyendas();
        guardarHistorias();
        guardarCulturaJuglares();
        guardarCulturaMusica();
        guardarActividades();
        solicitarpermisos();

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
                        fragment=new CategoriaActividadesFragment();
                        loadFragment(fragment);
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

    @Override
    public void detalleActividad(Actividades actividades) {
        detalleActividadesFragment = new DetalleActividadesFragment();
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("objeto", actividades);
        detalleActividadesFragment.setArguments(bundleEnvio);
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,detalleActividadesFragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }

    public void guardarSitioRestaurantes(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idSitio;
        ContentValues values =  new ContentValues();
        values.put(SitioBD.SITIO_ID,0);
        values.put(SitioBD.SITIO_NAME,"Restaurante La Brasa");
        values.put(SitioBD.SITIO_DESCRIPCION,"Ofrece comida para llevar, reservas, acceso para silla de ruedas, servicio de mesa, asientos. Buenos platos como desayunos, brunches, almuerzos, cenas, platos únicos en su sector y a buen precio. Buena Infraestructura y ambiente agradable, ubicación y parqueadero de fácil acceso.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Aguachica");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 40 Vía al Mar Entre Calle 3 y 4");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Restaurante+La+Brasa-Aguachica,+Cesar/@8.3117531,-73.597162,15z/data=!4m2!3m1!1s0x0:0x8ec3af8125d44c2d?sa=X&ved=2ahUKEwjh8ICQvorqAhXRmeAKHVt-A2AQ_BIwCnoECBUQCA");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.restaurantelabrasa);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,1);
        values.put(SitioBD.SITIO_NAME,"Restaurante La Fogata");
        values.put(SitioBD.SITIO_DESCRIPCION,"Es el restaurante más famoso del municipio. Es un restaurante de paso, ubicado en un buen lugar para comer, ofrece un delicioso chicharrón de cerdo (comida típica del municipio), refrescantes bebidas a cualquier hora del día. Se hacen jugos naturales y también se venden todo tipo de gaseosas. Sus salones tienen aire acondicionado y podrás parquear tu vehículo al frente.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Bosconia");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 13 #2");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Restaurante+la+Fogata/@9.9737073,-73.8857913,15z/data=!4m2!3m1!1s0x0:0x5f923f7bafb6515c?sa=X&ved=2ahUKEwjqkcTfvorqAhUph-AKHSlCCbAQ_BIwCnoECBQQCA");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.lafogata);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,2);
        values.put(SitioBD.SITIO_NAME,"Restaurante Bufet el Rodeo");
        values.put(SitioBD.SITIO_DESCRIPCION,"Posee excelente prestación de atención al cliente, calidad y servicio, comidas exquisitas a buen precio. Ambiente agradable y buena ubicación.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Codazzi");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 12 A #15-90");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Restaurante+BUFET+EL+RODEO/@10.0332353,-73.2375579,17z/data=!3m1!4b1!4m5!3m4!1s0x8e601b0eaa93a237:0x5c3a22bc4b76499d!8m2!3d10.03323!4d-73.2353692");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.elrodeo);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,3);
        values.put(SitioBD.SITIO_NAME,"Restaurante El Merendero");
        values.put(SitioBD.SITIO_DESCRIPCION,"Es un restaurante al que vale la pena visitar, es uno de los mejores restaurantes calidad-precio del municipio. Buena atención y excelente servicio, lo que lo hace un excelente lugar para compartir en familia con comidas de calidad. Ambiente agradable y amplio espacio de estacionamiento.");
        values.put(SitioBD.SITIO_MUNICIPIO,"El Copey");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 6 Este #4B-54");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Restaurante+El+Merendero/@10.1500776,-73.9552688,17z/data=!3m1!4b1!4m5!3m4!1s0x8e5f8969fb7e7cf9:0xcc1fb5c3bfe3906c!8m2!3d10.1500723!4d-73.9530801");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.elmerendero);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,4);
        values.put(SitioBD.SITIO_NAME,"Restaurante Majuvi Gourmet");
        values.put(SitioBD.SITIO_DESCRIPCION,"Es uno de los restaurantes más conocido y mejor valorado de la paz. Es un lugar acogedor, cómodo, y bien amoblado con servicio de TV. Ofrece gran variedad de comidas gourmet, bebidas y comidas rápidas. Tiene un gran atención al cliente.");
        values.put(SitioBD.SITIO_MUNICIPIO,"La Paz");
        values.put(SitioBD.SITIO_DIRECCION,"Transversal 4 #2-85, Robles");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Restaurante+Majuvi/@10.3866881,-73.1790414,17z/data=!3m1!4b1!4m5!3m4!1s0x8e8abdbea89ea1bf:0x61122bd738a6f6a3!8m2!3d10.3866828!4d-73.1768527");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.majuvi);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,5);
        values.put(SitioBD.SITIO_NAME,"Restaurante Asadero Brasa Manaurera");
        values.put(SitioBD.SITIO_DESCRIPCION,"Posee excelente prestación de atención al cliente, calidad y servicio, comidas exquisitas y asados a buen precio. Ambiente agradable al aire libre y buena ubicación.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Manaure");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 2 #6-15");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Brasa+Manaurera+Restaurante/@10.3902744,-73.0263546,15z/data=!4m5!3m4!1s0x0:0x91ff2bb9f32cd90d!8m2!3d10.3902744!4d-73.0263546");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.brasamanaurera);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,6);
        values.put(SitioBD.SITIO_NAME,"Restaurante Mi Pueblo Bello");
        values.put(SitioBD.SITIO_DESCRIPCION,"Ofrece excelentes comidas, asados y picadas deliciosas, con buen sazon y sabor típico de las comidas de la costa y sobretodo a buen precio. Atención excelente que lleva consigo una gran amabilidad y gentileza por parte del personal.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Pueblo Bello");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 9 #5-17");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Restaurante+Mi+Pueblo+Bello/@10.4126036,-73.5933415,17z/data=!3m1!4b1!4m5!3m4!1s0x8ef54f83589c7c73:0x6e96fa1c248e4e6d!8m2!3d10.4125983!4d-73.5911528");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.mipueblobello);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,7);
        values.put(SitioBD.SITIO_NAME,"Restaurante Montacarga del Norte");
        values.put(SitioBD.SITIO_DESCRIPCION,"Entrega a domicilio, comida para llevar, asientos al aire libre, acceso para silla de ruedas, servicio de mesa. Sus almuerzos generosos en cuanto a calidad con porciones muy grandes y precios muy adecuados. Posee platos típicos particulares (Asados variados, ricos jugos, entre otros). Considerado uno de los mejores de Valledupar, desde el servicio hasta el pago.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 19 #5-30");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Restaurante+Montacarga+del+Norte/@10.4869444,-73.2662606,17z/data=!3m1!4b1!4m5!3m4!1s0x8e8ab8320e3f8ee1:0x70ae2b1c680f1662!8m2!3d10.4869391!4d-73.2640719");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.montacarga);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,8);
        values.put(SitioBD.SITIO_NAME,"Restaurante Varadero");
        values.put(SitioBD.SITIO_DESCRIPCION,"Una historia llena de sabor originada en el año 1999, hoy sigue viva para ser disfrutada por todos nuestros comensales locales, y con el tiempo, se ha vuelto un auténtico destino obligatorio para turistas. En este mágico lugar siempre se están cocinando fascinantes platos que cautivarán tus sentidos, cada ingrediente es una nota de sabor que te invita a vibrar al son de una experiencia gastronómica única, donde el Chef toma exclusivamente lo mejor del mar para hacer arte, conjugar colores, texturas, sabores y brindar un regalo a tu vista y a tu paladar.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 12 #6-56");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Varadero+Valledupar/@10.4819454,-73.2487814,15z/data=!4m5!3m4!1s0x0:0xd24c6882762d2c77!8m2!3d10.4819454!4d-73.2487814");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.varadero);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,9);
        values.put(SitioBD.SITIO_NAME,"Restaurante Rider's Bike Bistro");
        values.put(SitioBD.SITIO_DESCRIPCION,"Restaurante ubicado en una zona preferencial de Valledupar, donde podrá disfrutar de exquisitos platos, con un excelente servicio. Posee un encantador ambiente y sofisticada decoración, cuenta con salón vip, terraza exterior, todo muy bien organizado. La satisfacción del cliente es uno de los principales objetivos de este restaurante, y su servicio simplifica mucho esta tarea, pues se centra en prestar una excelente atención, contando con meseros atentos a las peticiones los clientes.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 13 No. 6-46 Parque Novalito");
        values.put(SitioBD.SITIO_TIPO,"Restaurantes");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Rider's+restaurante+bistro/@10.4811781,-73.2503249,17z/data=!3m1!4b1!4m5!3m4!1s0x8e8ab9b4ba1ebe93:0x9e89d52ef34d1629!8m2!3d10.4811728!4d-73.2481362");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.bistro);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);
    }

    public void guardarSitioHoteles(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idSitio;
        ContentValues values =  new ContentValues();
        values.put(SitioBD.SITIO_ID,10);
        values.put(SitioBD.SITIO_NAME,"Hotel Monterrey Plus-Aguachica");
        values.put(SitioBD.SITIO_DESCRIPCION,"El hotel Monterrey es una alternativa muy llamativa por la relación calidad precio, por $35.000, ofrece una habitación cómoda, limpia, con aire acondicionado, televisión y baño privado, muy confortable, servicio excelente de parqueadero. Brinda un ambiente hogareño con excelente comodidad. Sin servicio de restaurante.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Aguachica");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 3 #39-91");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Hotel+Monterrey+Plus-Aguachica/@8.3123085,-73.5999998,17z/data=!3m1!4b1!4m8!3m7!1s0x8e5d85877ff69a29:0x6184d696d28bbc80!5m2!4m1!1i2!8m2!3d8.3123032!4d-73.5978111");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.monterrey);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,11);
        values.put(SitioBD.SITIO_NAME,"Hotel Jorlín");
        values.put(SitioBD.SITIO_DESCRIPCION,"Ubicado en un lugar central del municipio, considerado como uno de los mejores, ofrece desayunos gratis tipo bufet. Ofrece servicios como parqueadero con muy buena seguridad, un buen aseo general del lugar, silencioso que lo hace muy tranquilo. Habitaciones cómodas y muy bien presentadas. Posee una piscina al aire libre para una buena recreación.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Bosconia");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 23 #19-56");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Hotel+Jorlin/@9.9720732,-73.8905112,17z/data=!3m1!4b1!4m8!3m7!1s0x8e5f93755ddc96d7:0x185f89a50e5134eb!5m2!4m1!1i2!8m2!3d9.9720679!4d-73.8883225");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.jorlin);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,12);
        values.put(SitioBD.SITIO_NAME,"Casa Grande Hotel");
        values.put(SitioBD.SITIO_DESCRIPCION,"Las habitaciones poseen aire a condicionado, camas muy cómodas, espacio agradable, los servicios que ofrece como limpieza, wifi, etc., son excelentes, la atención que presta al cliente es bastante satisfactoria. El sitio se encuentra bien ubicado.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Codazzi");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 16 #11-03 Centro");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Casa+Grande+Hotel/@10.0308523,-73.2382754,17z/data=!3m1!4b1!4m5!3m4!1s0x8e601af3ed40e199:0x5a99c53d759e900c!8m2!3d10.030847!4d-73.2360867");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.casagrande);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,13);
        values.put(SitioBD.SITIO_NAME,"Hotel El Cachaco");
        values.put(SitioBD.SITIO_DESCRIPCION,"Hotel ubicado en un excelente lugar central del municipio, modesto, económico, seguro y atención al cliente excelente, ambiente familiar, con habitaciones muy limpias.");
        values.put(SitioBD.SITIO_MUNICIPIO,"El Copey");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 3 #8-38 Carretera Troncal");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Hotel+El+Cachaco/@10.1491029,-73.9550537,17z/data=!3m1!4b1!4m5!3m4!1s0x8e5f894207cd3be9:0xa3c641f16edfa663!8m2!3d10.1490976!4d-73.952865");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.elcachaco);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,14);
        values.put(SitioBD.SITIO_NAME,"Hotel Issa Real");
        values.put(SitioBD.SITIO_DESCRIPCION,"Uno de los mejores hoteles que puede haber en el municipio. Con una excelente fachada externa que lo hace llamativo para los pasantes. Las habitaciones son muy cómodas, y muy bien decoradas, cuentan con buen espacio, aire acondicionado y ventilador, cama de madera tallada, baño, closet, tv satelital. Los precios que oferta son cómodos con respecto a los servicios que ofrece. Brinda una buena atención a los clientes.");
        values.put(SitioBD.SITIO_MUNICIPIO,"La Paz");
        values.put(SitioBD.SITIO_DIRECCION,"Tranversal 4 #1-2A");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/HOTEL+PAZ+REAL/@10.3861977,-73.181573,17z/data=!4m5!3m4!1s0x8e8abc8202f2dcb3:0x6c29de2fd59256fa!8m2!3d10.3860752!4d-73.1815716");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.issa);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,15);
        values.put(SitioBD.SITIO_NAME,"El Tucán Casa Campo");
        values.put(SitioBD.SITIO_DESCRIPCION,"El Tucán Casa Campo se encuentra en Manaure Balcón del Cesar, en la región de Cesar, y ofrece jardín. El establecimiento se encuentra a 38 km de Valledupar y proporciona aparcamiento privado gratuito. Esta casa rural cuenta con balcón, vistas al jardín, 3 dormitorios, sala de estar, TV de pantalla plana vía satélite, cocina equipada y 2 baños con bañera de hidromasaje y bañera o ducha. La casa rural cuenta con bañera de hidromasaje. El Tucán Casa Campo dispone de terraza.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Manaure");
        values.put(SitioBD.SITIO_DIRECCION,"Km 2 Manaure A Sabana Rubia");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/El+Tuc%C3%A1n+Casa+Campo/@10.3890115,-73.0097858,17z/data=!3m1!4b1!4m8!3m7!1s0x8e8a9718f8e364c9:0xee48754b679d8f9b!5m2!4m1!1i2!8m2!3d10.3890062!4d-73.0075971");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.eltucan);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,16);
        values.put(SitioBD.SITIO_NAME,"Hotel Campestre Uraku");
        values.put(SitioBD.SITIO_DESCRIPCION,"Es un hotel acogedor, cómodo, con una gran naturaleza a su alrededor. Ofrece habitaciones bien amobladas, restaurante, piscina exterior, sillas asoleadoras, sombrillas, balcón/terraza, baño con dicha, y servicio Wi-Fi.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Pueblo Bello");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 22 #4-14");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Hotel+Campestre+Uraku/@10.4375944,-73.5789612,17z/data=!3m1!4b1!4m8!3m7!1s0x8ef54f51bb295db3:0x42aae164f3ee6209!5m2!4m1!1i2!8m2!3d10.4375891!4d-73.5767725");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.uraku);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,17);
        values.put(SitioBD.SITIO_NAME,"Hotel Tativán");
        values.put(SitioBD.SITIO_DESCRIPCION,"Este hotel moderno y tranquilo se encuentra a 7 minutos a pie de la Plaza Alfonso López y a 9 minutos a pie de los objetos históricos del Museo Arqueológico del César. Las habitaciones luminosas se encuentran repartidas en 2 torres y cuentan con Wi-Fi gratis, TV por cable y minibar. Las habitaciones mejoradas cuentan con área de visitas y las suites incluyen sala de estar. Se ofrece servicio a la habitación. El hotel cuenta con un restaurante luminoso con vistas panorámicas de la ciudad, además de un bar de cocteles con jardín en la terraza. Hay una piscina al aire libre con una fuente y un solárium. Otros servicios incluyen un sauna seco y un sauna húmedo, además de un centro de negocios. Se ofrece servicio de lavandería por una tarifa adicional. Alojamientos de aproximadamente $ 146.205.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 16a #9-50");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Hotel+Tativan/@10.4743148,-73.2494066,17z/data=!3m1!4b1!4m8!3m7!1s0x8e8ab9b6d400bdff:0x8e8845dec66aa1bd!5m2!4m1!1i2!8m2!3d10.4743095!4d-73.2472179");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.tativan);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,18);
        values.put(SitioBD.SITIO_NAME,"Hotel Hampton By Hilton");
        values.put(SitioBD.SITIO_DESCRIPCION,"El hotel Hampton by Hilton Valledupar está conectado a la plaza comercial Mayales, y tiene una ubicación céntrica en uno de los vecindarios más exclusivos de la ciudad, a solo cinco minutos en automóvil del Aeropuerto Alfonso López Pumarejo. Este hotel en Valledupar ofrece acceso fácil a varios de los principales parques empresariales, tiendas y restaurantes. Disfruta de la animada vida nocturna y de la música tradicional colombiana, el vallenato, por la cual es famosa Valledupar.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 30 #6A-133");
        values.put(SitioBD.SITIO_TIPO,"Hoteles");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Hampton+by+Hilton+Valledupar,+Colombia/@10.4541398,-73.2464941,16z/data=!4m15!1m6!3m5!1s0x8e8ab99773996773:0x450a10765cb7b1f2!2sHotel+Hampton+by+Hilton!8m2!3d10.446185!4d-73.237074!3m7!1s0x8e8ab82e2fe53121:0x7ec7731db7655e17!5m2!4m1!1i2!8m2!3d10.4564305!4d-73.2425451");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.hampton);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);
    }

    public void guardarSitioBares(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idSitio;
        ContentValues values =  new ContentValues();
        values.put(SitioBD.SITIO_ID,19);
        values.put(SitioBD.SITIO_NAME,"Juglares Bar");
        values.put(SitioBD.SITIO_DESCRIPCION,"Un lugar agradable, buena música, ambiente en su mayoría de vallenato puro, buenos licores fríos. El sitio es hermoso y con un excelente ambiente impecable. Un lugar donde puedes hablar, tomar y hacer negocios.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Aguachica");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 5 #29-52");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Juglares+bar/@8.3095624,-73.6084618,17z/data=!4m12!1m6!3m5!1s0x8e5d85e4eeafae25:0x50bdbcca2db81377!2sJuglares+bar!8m2!3d8.3095571!4d-73.6062731!3m4!1s0x8e5d85e4eeafae25:0x50bdbcca2db81377!8m2!3d8.3095571!4d-73.6062731");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.juglaresbar);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,20);
        values.put(SitioBD.SITIO_NAME,"Bar Deja Vú");
        values.put(SitioBD.SITIO_DESCRIPCION,"Un excelente lugar para pasar en compañía disfrutando de las variadas bebidas que ofrecen en el lugar, prestando una excelente atención a los clientes y llevando con claridad las cuentas, ubicado en un excelente sitio con un ambiente agradable ideal para la definición de una noche de rumba, música de todo tipo, todas estas características lo hacen un lugar espectacular.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Bosconia");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 17A #11-1");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Discoteca+Deja+V%C3%BA/@9.9730057,-73.8873539,17z/data=!3m1!4b1!4m5!3m4!1s0x8e5f9374cd3741a1:0xada30cc894f13c6e!8m2!3d9.9730004!4d-73.8851652");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.dejavu);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,21);
        values.put(SitioBD.SITIO_NAME,"Sanjuanera Bar");
        values.put(SitioBD.SITIO_DESCRIPCION,"Posee un ambiente muy agradable y acogedor para pasar un buen rato. Licores de calidad y a precios asequibles. La atención que brinda puede llegar a ser en ocasiones un poco regular, por lo que algunos clientes tienden a quejarse.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Codazzi");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 16 #10A-18");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Sanjuanera+Bar/@10.0298876,-73.2384383,17z/data=!3m1!4b1!4m5!3m4!1s0x8e601b1328bab6ef:0x69b6f051bb32b51e!8m2!3d10.0298823!4d-73.2362496");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.sanjuanera);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,22);
        values.put(SitioBD.SITIO_NAME,"La Plaza Licores");
        values.put(SitioBD.SITIO_DESCRIPCION,"Un sitio agradable para un buena recreación y entretenimiento. Ambiente social con buena música variada, que lo hacen en un buen lugar. Ideal para pasar momentos agradables con bebidas y música para todos los gustos, con una gran variedad de cervezas y a buen precio.");
        values.put(SitioBD.SITIO_MUNICIPIO,"El Copey");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 10 Kra 16-56, Centro");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/La+plaza+licores/@10.1492119,-73.9642914,17z/data=!3m1!4b1!4m5!3m4!1s0x8e5f893fcc230d63:0x3715ff8a9cac4588!8m2!3d10.1492066!4d-73.9621027");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.plazalicores);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,23);
        values.put(SitioBD.SITIO_NAME,"Estadero La Machaca");
        values.put(SitioBD.SITIO_DESCRIPCION,"Uno de los pocos bares del municipio. Está ubicado en un sitio excelente y tranquilo. Ofrece gran variedad de licores a precios asequibles y música de todo tipo, un lugar impecable y con un excelente servicio de atención al cliente.");
        values.put(SitioBD.SITIO_MUNICIPIO,"La Paz");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 4 Norte #6-2, Robles");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Estadero+La+Machaca/@10.3882829,-73.1753411,17z/data=!3m1!4b1!4m5!3m4!1s0x8e8abc8655040137:0x2e1e2da484691444!8m2!3d10.3882776!4d-73.1731524");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.lamachaca);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,24);
        values.put(SitioBD.SITIO_NAME,"Estanco El Viejo Sola");
        values.put(SitioBD.SITIO_DESCRIPCION,"Un lugar excelente, en donde se puede disfrutar de una gran variedad de licores a precios asequibles y música variada para todos los gustos. El lugar posee una terraza en donde se puede disfrutar tranquilamente del sitio, dentro de este, la sala tiene aire acondicionado, todas estas comodidades y mas hacen de este un lugar muy atractivo.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Manaure");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 8 #2-30");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Estanco+y+comida+El+viejo+sola/@10.3891125,-73.0293189,15z/data=!4m5!3m4!1s0x0:0x87aa005c4f8cdee6!8m2!3d10.3891125!4d-73.0293189");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.viejosola);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,25);
        values.put(SitioBD.SITIO_NAME,"Disco-Bar La Oficina");
        values.put(SitioBD.SITIO_DESCRIPCION,"Uno de los pocos bares del municipio. Está ubicado en un sitio excelente y tranquilo. Ofrece gran variedad de licores a precios asequibles y música de todo tipo, un lugar impecable, amplio y con un excelente servicio.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Pueblo Bello");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 4 #8-29");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/LA+OFICINA+ESTACO+HELADERIA/@10.4125229,-73.5931914,15z/data=!4m5!3m4!1s0x0:0x6290ffb62d3c0a55!8m2!3d10.4125229!4d-73.5931914");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.oficina);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,26);
        values.put(SitioBD.SITIO_NAME,"Bar La Bodeguita");
        values.put(SitioBD.SITIO_DESCRIPCION,"El mejor Bar de la ciudad, y uno de los pocos con música Rock en todo el municipio. En este sitio se puede vivir la experiencia de degustar cervezas de diferentes rincones del mundo. Su dueño, Jorge, es una persona muy humilde y Super comprometida a que sus clientes vivan una experiencia única en cada trago. La atención es muy buena, incluso las comidas que ofrece, que son muy diferentes opciones de comida tales como hamburguesas, perros calientes, papas fritas con topping de queso fundido y tocineta, entre otros. Los platos son de muy buena calidad y económicos. Las hamburguesas cuestan alrededor de 15.000 pesos y los perros alrededor de 10.000 pesos. Venden cervezas nacionales desde 2.500 pesos e internacionales que pueden superar los 70.000. También Venden licor por shot, vaso o botella. En este bar existe la particularidad que los clientes pueden elegir o seleccionar la música que sonará y Cabe destacar que este sitio tiene una norma o regla y es que se prohíbe el colocar música de vallenato.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 19 #10-29");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/La+Bodeguita/@10.476552,-73.2616998,17z/data=!3m1!4b1!4m5!3m4!1s0x8e8ab9c46b607a79:0xc2beaad61cd4a6ea!8m2!3d10.4765467!4d-73.2595111");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.labodeguita);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,27);
        values.put(SitioBD.SITIO_NAME,"Bar La Premiere");
        values.put(SitioBD.SITIO_DESCRIPCION,"Posee un ambiente de terraza en donde te puedes sentar charlar un rato tomar la bebida que quieras y disfrutar de la buena música. Dentro del lugar se observa un mejor ambiente, mesas, buena música a mayor volumen, y el sitio es oscuro donde se puedes bailar en la tarima que tiene dentro o en cualquier otra parte. Es tan popular que en ocasiones especiales se llena mucho lo que dificulta el movimiento.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 9 # 10-88");
        values.put(SitioBD.SITIO_TIPO,"Bares");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/La+Premiere/@10.4809428,-73.2538753,17z/data=!3m1!4b1!4m5!3m4!1s0x8e8ab9b540fc079f:0x56c7d21ba31a557e!8m2!3d10.4809375!4d-73.2516866");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.lapremiere);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);
    }

    public void guardarMonumentos(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idSitio;
        ContentValues values =  new ContentValues();
        values.put(SitioBD.SITIO_ID,28);
        values.put(SitioBD.SITIO_NAME,"Monumento Morrocoy");
        values.put(SitioBD.SITIO_DESCRIPCION,"Se encuentra ubicado en el parque Morrocoy del municipio de aguachica, es un monumento que hace homenaje a la tortuga, coloquialmente conocida como morrocoyo.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Aguachica");
        values.put(SitioBD.SITIO_DIRECCION,"Calle 5 #171");
        values.put(SitioBD.SITIO_TIPO,"Monumentos");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Parque+Morrocoy/@8.3099184,-73.6188728,17z/data=!3m1!4b1!4m5!3m4!1s0x8e5d857ce9a4a421:0x6d1ba00b4cd42fd5!8m2!3d8.3099131!4d-73.6166841");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.morrocoy);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,29);
        values.put(SitioBD.SITIO_NAME,"Estatua Enrique Aarón Hayen");
        values.put(SitioBD.SITIO_DESCRIPCION,"El nombre de bosconia se debe a San Juan Bosco, fundador de la Comunidad Salesiana, a quien admiraba el fundador de Bosconia: Enrique Aarón Hayen. Precisamente la imagen de quien fundó el pueblo en 1.958, adorna el parque central de Bosconia.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Bosconia");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 6 #20-79");
        values.put(SitioBD.SITIO_TIPO,"Monumentos");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Parque+Enrique+Aaron/@9.9765877,-73.892443,15z/data=!4m5!3m4!1s0x0:0x1963541414a561cf!8m2!3d9.9765877!4d-73.892443");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.fundadorbosconia);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,30);
        values.put(SitioBD.SITIO_NAME,"Monumento A Las Guitarras");
        values.put(SitioBD.SITIO_DESCRIPCION,"Ubicado en el parque de la guitarra se puede observar el monumento de 3 compositores con guitarras en sus brazos, muy visible y decorativo.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Codazzi");
        values.put(SitioBD.SITIO_DIRECCION,"Carrera 16 Entre Calle 10-11");
        values.put(SitioBD.SITIO_TIPO,"Monumentos");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Parque+De+La+Guitarra/@10.0298959,-73.2392393,17z/data=!3m1!4b1!4m5!3m4!1s0x8e601af3a3f2a1bf:0xcdd83744f9e8d38!8m2!3d10.0298906!4d-73.2370506");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.guitarras);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,31);
        values.put(SitioBD.SITIO_NAME,"Estatua Almojabanera");
        values.put(SitioBD.SITIO_DESCRIPCION,"Estatua en homenaje a los comerciantes de almojabanas, el manjar exclusivo de la tierra pacífica.");
        values.put(SitioBD.SITIO_MUNICIPIO,"La Paz");
        values.put(SitioBD.SITIO_DIRECCION,"Transversal 4 Calle 6");
        values.put(SitioBD.SITIO_TIPO,"Monumentos");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Robles,+La+Paz,+Cesar/@10.3867984,-73.1709312,3a,75y,127.11h,90t/data=!3m6!1e1!3m4!1s8RZp9ivqRh1mmyHSiJnLiw!2e0!7i13312!8i6656!4m5!3m4!1s0x8e8abc8637abe8c9:0x8a85ae2f95f39825!8m2!3d10.3868315!4d-73.1706425?hl=es");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.almojabanera);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,33);
        values.put(SitioBD.SITIO_NAME,"Monumento La Pilonera Mayor");
        values.put(SitioBD.SITIO_DESCRIPCION,"La pilonera mayor es una gran escultura homenaje a consuelo Araujo Noguera, la más grande de las piloneras quien fue una mujer destacada por sus cualidades y virtudes de defender el folclor vallenato. Es un punto de interés para todas las personas que visitan Valledupar, excelente para una fotografía.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Glorieta vía al Río Hurtado");
        values.put(SitioBD.SITIO_TIPO,"Monumentos");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Glorieta+La+Pilonera+Mayor/@10.4968096,-73.2711714,17z/data=!3m1!4b1!4m5!3m4!1s0x8e8ab8244a5398b7:0x956a2d574804cf64!8m2!3d10.4968043!4d-73.2689827");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.lapilonera);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);

        values.put(SitioBD.SITIO_ID,35);
        values.put(SitioBD.SITIO_NAME,"Monumento Martin Elías");
        values.put(SitioBD.SITIO_DESCRIPCION,"El monumento es majestuoso, un lugar para recordar a los grandes del vallenato, donde se puede observar esculturas de algunos de los grandes artistas del vallenato como Diomedes Díaz y su hijo Martín Elías. Un lugar agradable que permite tomar excelentes fotografías.");
        values.put(SitioBD.SITIO_MUNICIPIO,"Valledupar");
        values.put(SitioBD.SITIO_DIRECCION,"Glorieta al lado del Parque La Provincia");
        values.put(SitioBD.SITIO_TIPO,"Monumentos");
        values.put(SitioBD.SITIO_MAPS,"https://www.google.com/maps/place/Monumento+Martin+Elias/@10.5004748,-73.2700511,17z/data=!3m1!4b1!4m5!3m4!1s0x8e8ab914c8081509:0x11167a8b98bd36e5!8m2!3d10.5004695!4d-73.2678624");
        values.put(SitioBD.SITIO_IMAGE,R.drawable.martinelias);
        idSitio = db.insert(SitioBD.TABLE_SITIOS, null, values);
    }

    public void guardarLeyendas(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idHistoria;
        ContentValues values =  new ContentValues();
        values.put(HistoriaBD.HISTORIA_ID,0);
        values.put(HistoriaBD.HISTORIA_NAME,"Leyenda de la Sirena de Hurtado");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Cuentan una vez que en Semana Santa una niña muy linda pidió permiso a su mamá para irse a bañar a las profundas y frías aguas del Río Guatapuri, pozo de Hurtado; la madre de la niña, por ser Jueves Santo, le negó el permiso, pero la niña desobediente se marchó a escondidas, llegó a las rocas de la orilla, se quitó sus ropas y se lanzó al agua desde la altura; inmediatamente quedó convertida en Sirena. Su madre la llamó por toda la orilla del rió creyéndola ahogada, pero ella en la mañana, al salir el sol dijo adiós con la cola antes de sonreír por última vez, entonces, todos comprendieron la realidad.\n" +
                "\n" +
                "Cuentan los abuelos que antes la sirena salía a las rocas los jueves santo y emitía su hermosos canto que se escuchaba por todo el valle, al tiempo que brindaba a su madre las lagrimas de la desobediencia.");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Valledupar");
        values.put(HistoriaBD.HISTORIA_TIPO,"Leyendas");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.sirena);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,1);
        values.put(HistoriaBD.HISTORIA_NAME,"Leyenda de Francisco El Hombre");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Narra la leyenda que una noche después de una parranda de varios días y al ir en marcha hacia su pueblo, para distraerse en la soledad de la noche, abrió el acordeón y, sobre su burro, como era usual en aquella época, empezó a interpretar sus melodías; de pronto al terminar una pieza surgió de inmediato el repertorio de otro acordeonero que desafiante trataba de superarlo; de inmediato Francisco marchó hacia él hasta tenerlo a la vista; su competidor para sorpresa, era Satanás, quien al instante se sentó sobre la raíces de un gran árbol, abrió su acordeón, y con las notas que le brotaban hizo apagar la luna y toda las estrellas.\n" +
                "\n" +
                "El mundo se sumergió en una oscuridad tal, que sólo los ojos de Satanás resplandecían como tizones. Sus notas eran las de un gran maestro; algunos dicen que de ahí nació, de la inspiración del demonio, el canto del amor amor. Francisco, dueño de su virtudes y poseído de gran fe, lejos de acobardarse con la abrazadora oscuridad, abrió su acordeón y extrajo tan hermosa melodía que su magia devolvió la luz a la luna y a las estrellas, infligiendo temor al demonio. Después clamo a Dios y entonó el credo con su voz de cantador taumaturgo, el demonio exaltó un terrible alarido y con su acordeón a rastras irrumpió un gran bullicio hacia las montañas donde se perdió para siempre.");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Valledupar");
        values.put(HistoriaBD.HISTORIA_TIPO,"Leyendas");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.franciscoelhombre);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,2);
        values.put(HistoriaBD.HISTORIA_NAME,"Don Antón, El Caballero de Aguachica");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"En esta ciudad, los siglos han pasado, pero la leyenda se mantiene viva: en las noches más oscuras deambula el fantasma de don Antón García de Bonilla, con su traje de otros tiempos.\n" +
                "El fantasma de don Antón García de Bonilla recorre todavía las calles y caminos de San Roque de Aguachica.\n" +
                "\"Aguachica, municipio privilegiado. Dios ha puesto la mano aquí\". Charles Gail, funcionario de la Texas Oil Company\n" +
                "\"Pernoctamos en la miserable aldea de Aguachica\". Diario de O'Leary, el edecán de Bolívar.\n" +
                "Fueron tres personajes del mismo nombre: abuelo, padre e hijo. Antón García de Bonilla, el abuelo, llegó a estas tierras con sus reses, sus esclavos y sus bestias de carga.\n" +
                "Al adquirir la encomienda que incluía los terrenos de lo que hoy es Aguachica, en 1616, marcó el destino ganadero de la región, así como su importancia para el comercio entre el río Magdalena y Ocaña, ciudad que para entonces llevaba 40 años de fundada.\n" +
                "Pero el Antón García de Bonilla que se hizo famoso fue el nieto, heredero de las tierras del abuelo, y propietario de la vieja casona que hoy se conserva a la entrada del centro histórico de Ocaña. Fue éste el que protagonizó una romántica historia de amor en la segunda mitad del siglo XVII, cuando se enamoró de María, hija ni más ni menos que del alférez real don Luis Téllez Blanco. Y de Ocaña se la llevó con gran pompa a vivir con él en su hacienda de San Roque de Aguachica.\n" +
                "Dice la historia que una de las entretenciones de la joven pareja era pasear por los recovecos de la vecina ciénaga de Patiño mientras que una pequeña orquesta, desde otra barcaza, los deleitaba con su música. No puede uno menos que evocar los orígenes de La música del agua, ese famoso tema que unos años más tarde estaría componiendo Georg Friedrich Handel para complacer los caprichos del rey Jorge I. El Rey desde una barcaza escuchaba a la orquesta, mientras ésta navegaba en otra, a lo largo del Támesis. Sea como fuere, en muchos mapas figura hoy no la ciénaga de Patiño, sino la de Doña María.\n" +
                "El nombre Aguachica, que tuvo la hacienda y ahora el municipio, probablemente se deriva de la presencia de varios nacederos en la zona, algunos de ellos incorporados hoy al gran parque urbano conocido como el Agüil, y no como asegura la tradición del pueblo de un día en el que don Antón llegó sediento a su casa después de una larga cabalgata y se dirigió a una criada diciendo: \"tráeme agua, chica\". Los frecuentes racionamientos del servicio de acueducto de los tiempos recientes pueden haber colaborado para mantener viva esta versión apócrifa.\n" +
                "En los siglos XVI y XVII, la hacienda de San Roque de Aguachica fue el punto de partida para el poblamiento de toda la región. Pero hay que reconocer que la historia del poblado mismo se inicia antes de la llegada del primer García.\n" +
                "Algunos estudios arqueológicos en los límites del área urbana de la actual población encontraron restos humanos de hace 3.200 años. Se hallaron también semillas de algodón, alfarería, así como caparazones de tortugas morrocoy, animales que aún hoy constituyen un plato típico de la región (de hecho, un apelativo algo despectivo para los habitantes de Aguachica es \"morrocoyeros\").\n" +
                "Pero 1748 fue la fecha más aceptada para la designación de Aguachica como parroquia. De esa época sobrevivió hasta el siglo XIX una cruz de plata, hoy desaparecida, que decía: \"Se acabó esta cruz de Aguachica hoy 14 de junio de 1753\". Se menciona también un documento fechado en 1766 que habla de la visita de don Francisco de Caviedes y Godoy, juez de tierras de Ocaña y 'capitán de asaltos y emboscadas' en donde dice que se hizo la mesura de la población \"con la venia del licenciado presbítero Martín Dionisio de la Peña y los alcaldes de la santa hermandad\".\n" +
                "Quizás fue una epidemia, o más probablemente la búsqueda de una mayor cercanía al camino real, lo que llevó luego al traslado de la ciudad al sitio actual, en fecha que debaten los historiadores, pero que pudo ser hacia el año 1800. La hipótesis de la epidemia, sin mucho sustento histórico, es atractiva dado que San Roque -el patrono de Aguachica es justamente el santo de las pestes. Incluyendo claro está la de los pollos.\n" +
                "Para finales del siglo XIX no había sido mucho el crecimiento de Aguachica: su población era apenas de 701 personas. Las enfermedades del trópico y las difíciles vías de comunicación, unidas a las inundaciones del río Magdalena entorpecían su desarrollo. Ya a finales de ese siglo la región se benefició de la bonanza del tabaco, producto que, según Agustín Codazzi, no tenía nada que envidiarle al de Ambalema. Pero el momento culminante de la historia de Aguachica llegaría en la primera mitad del siglo XX.\n" +
                "Entre 1925 y 1929 se construyó el cable aéreo para el transporte de carga y pasajeros que iba desde Gamarra, en el Magdalena, hasta Ocaña, con una estación en Aguachica. Se trataba de una ambiciosa obra que en su plan original se proponía llegar hasta el lago de Maracaibo. En 1944, con la construcción de la carretera, las instalaciones de este cable se vendieron como chatarra, marcándole así un destino similar al de aquél que iba de Manizales a Mariquita. Solo quedaron de recuerdo los pesados cimientos de concreto de sus torres.\n" +
                "Para Aguachica, los siglos han pasado, pero una leyenda se mantiene viva: en las noches más oscuras deambula en la penumbra el fantasma de don Antón García de Bonilla, con su traje de otros tiempos, condenado a salir en su caballo negro dice la tradición por incumplir una promesa a Santa Rita, por avaro, por mujeriego, o quizás por maltratar a sus esclavos. Cierro este escrito con dos fragmentos poéticos, el primero del presbítero Alfredo Sánchez Fajardo y el segundo del poeta ocañero Marco Carvajalino, que aluden a este hecho:\n" +
                " \n" +
                "La remota tradición de Aparecidos\n" +
                "de aquel noble don Antón\n" +
                "que aún arrastra sus espuelas bulliciosas\n" +
                "por las calles silenciosas.\n" +
                "Refinado y fastuoso caballero,\n" +
                "tuvo tierras y esclavos a porfía,\n" +
                "como en el corazón rico venero\n" +
                "de largueza sin par y de hidalguía;\n" +
                "y aunque amante nocturno y callejero,\n" +
                "sólo tuvo un amor: ¡doña María!\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Aguachica");
        values.put(HistoriaBD.HISTORIA_TIPO,"Leyendas");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.anton);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);
    }

    public void guardarHistorias(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idHistoria;
        ContentValues values =  new ContentValues();
        values.put(HistoriaBD.HISTORIA_ID,3);
        values.put(HistoriaBD.HISTORIA_NAME,"Historia de Aguachica");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"El actual territorio de Aguachica estaba habitado por las naciones amerindias de los Chimilas, antes de la llegada de los primeros españoles, que la fundaron el 16 de agosto de 1748.\n" +
                "El actual territorio de Aguachica se empezó a consolidar en los primeros veinte años del siglo XVIII a partir de la hacienda de San Roque de propiedad de Don Antón García de Bonilla, localizada al oriente de la actual vía cuarenta, hacia la planta del acueducto municipal. Por razones asociadas a una \"peste\" los primeros pobladores de este asentamiento debieron trasladarse más abajo, alrededor del actual Parque San Roque. El primer núcleo poblacional era un incipiente núcleo de habitación en propiedades que hacia 1722 pertenecía a Don Casimiro Ramos de barahoja, articulado al flujo de mercancías y población de Gamarra a Ocaña. Unas décadas después, el 16 de agosto de 1748, mediante concesión Realenga de los terrenos de Aguachica viejo y San Francisco hecha a favor de Don José Lázaro de Rivera se realizó un acto de fundación o reconocimiento de la parroquia.\n" +
                "Sus fundaciones o refundaciones fueron reconfirmadas por la administración del Virrey José Alfonso Pizarro, entre 1749 y 1753. Tanto en la fundación de Aguachica como la de San Francisco, hacia 1753 se inició la construcción de algunas casas. Se acepta como fecha de fundación, el 16 de agosto de 1748, habiendo sido elevado a la categoría municipal en virtud de la ordenanza número 40 de 1914. El primer núcleo poblacional era un incipiente núcleo de habitación en propiedades, que en 1722 pertenecía a don Casimiro Ramos de Barahoja, articulado al flujo de mercancías y población de Gamarra a Ocaña. Unas décadas después, el 16 de agosto de 1748, mediante concesión Realenga de los terrenos de Aguachica viejo y San Francisco, hecha a favor de don José Lázaro de Rivera, se realizó un acto de fundación o reconocimiento de la parroquia.\n" +
                "Luego sus fundaciones o refundaciones fueron reconfirmadas por la administración del Virrey José Alfonso Pizarro, entre 1749 y 1753. Tanto en la fundación de Aguachica como la de San Francisco, hacia 1753 se inició la construcción de algunas casas. En la historia se acepta como fecha de fundación, el 16 de agosto de 1748, habiendo sido elevado a la categoría municipal en virtud de la ordenanza número 40 de 1914.\n" +
                "A pesar de considerar el 16 de agosto de 1784 como año de fundación de Aguachica, la realidad muestra una serie de documentos históricos, en los cuales queda demostrada su existencia, incluso como parroquia, con bastante anterioridad a esa fecha.\n" +
                "Un hecho cierto, es que los orígenes de Aguachica están indefectiblemente unidos y se diluyen en la historia de Ocaña, ciudad a la cual estuvo circunscrita en diferentes épocas.\n" +
                "El nombre de Aguachica, según la tradición regional, le fue asignado por Don Antón García de Bonilla y Rodríguez. Se dice que por la cercanía de sus haciendas al Río Magdalena le llamó al poblado Villa de ‘San Roque’ y para complementarla y ubicarla se le agregó “de Aguachica”.\n" +
                "Dicho puerto, al producirse el hecho de la independencia pasó a llamarse Puerto Nacional, y alcanzó gran esplendor comercial durante el siglo XIX, el cual perdió con el cambio de cauce que tuvo el río a comienzos del siglo XX, que lo llevó a ceder su playa; por lo que el punto de embarque y desembarque utilizado, quedó inadecuado; y se sintió la necesidad de establecer un puerto alterno, que dejó a éste olvidado y desde entonces pasó a llamarse Puerto Viejo, perteneciente hoy a la jurisdicción de Gamarra.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Aguachica");
        values.put(HistoriaBD.HISTORIA_TIPO,"Historias");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.baguachica);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,4);
        values.put(HistoriaBD.HISTORIA_NAME,"Historia de Bosconia");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Bosconia es un punto de referencia comercial en la Región Caribe Colombiana, centro agrícola y ganadero, se encuentra ubicada al Noroccidente del Departamento del Cesar, con una altura de 110 metros sobre el nivel de mar, y una temperatura promedio de 38 grados centígrados y llegando a alcanzar 42 grados centígrados. Fue fundada el 20 de agosto de 1958 por el Ingeniero Electromecánico Enrique Aarón Hayen, quien se hizo acompañar del Gobernador del Departamento del Magdalena, para la época, Capitán de Fragata Luis Fosión Millán Vargas, del presbítero José Agustín Mackensie, párroco del corregimiento de Caracolicito quien era conocido en la región como Guare-Ku, que en la lengua indígena significa “Amigo fiel”, de Francisco Riascos que ejercía como secretario de gobernación y el acompañamiento de otras personalidades de la región, bautizaron al naciente pueblo en honor de san Juan Bosco, creador de la Comunidad Salesiana, de quien era devoto el fundador.\n" +
                "La población se conformó inicialmente con los trabajadores que construyeron la línea del ferrocarril, en su mayoría provenientes de los departamentos de Bolívar, Atlántico y Magdalena. El auge del tren ocasionó la llegada de negociantes que hacían tránsito para las ciudades del interior del país con mercancías de contrabando que compraban en Maicao (La Guajira), y fueron los primeros que se establecieron en el sector. De los primeros negocios en la Estación, recordamos al Sr. Rúa propietario del primer teatro-Local de la época, Cleto Domínguez, propietario del Granero 16 de Julio, Marcos Tulio Gómez y Fernanda Charrys propietarios de Almacén El Triunfo, Maritza Valera y José Guerra Vence, los Hermanos Pulgarín Mejía, Los Hermanos Molina Tejera, Rafael Maestre, Nelson Guerrero, Néstor Pérez “Chichilo”, Oscar Barros y otros que por el momento se me escapan del pensamiento, pero que cada uno aporto un grano de arena para construir la Bosconia que se muestra imponente ante el contexto del Caribe Colombiano.\n" +
                "Para la década del 60 al 70, se inicia la construcción de la carretera que une al Departamento del Cesar con el Departamento del Magdalena, (San Roque - Bosconia - Fundación) una vía que solo era utilizada en época de verano por los transportadores provenientes de los Santanderes que acortaba el trayecto. En esa época el recorrido era largo y se transitaba por un carreteable destapado entre Fundación, Magdalena, Alto de las Minas. Valledupar, San Roque, este flujo vehicular marco el desarrollo que se vislumbraba en la región y fue cuando se inició el asentamiento en el sector de “El Cruce”, donde se ubicaron la familia Marenco Pérez y el Cachaco Carmelo Alsina Serrano quienes fueron los primeros pobladores, después llegaron Daniel Trillos y Doña Carmen Duarte, los Ricos, Luís Beleño y familia, Niévalo Ochoa, Godofredo Galvis, Higinio Sinuco, Luís Marín, Marcos Mena, Miguel Gullo Fragoso, Rudy Santiago, Luís Cantillo y otros.\n" +
                "Bosconia inicialmente fue inspección, y subsiguientemente corregimiento de Valledupar, posteriormente hizo parte del Municipio de El Copey de donde se segrego y se conformó en Municipio el 2 de diciembre de 1979, siendo su primer alcalde José Del Carmen Molina Tejera.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Bosconia");
        values.put(HistoriaBD.HISTORIA_TIPO,"Historias");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.bbosconia);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,5);
        values.put(HistoriaBD.HISTORIA_NAME,"Historia de Codazzi");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Esta localidad en sus comienzos (1702) se llamaba \"Espíritu Santo\" y fue el capitán Salvador Félix Arias, Gobernador de la Provincia de Santa Marta, quien la fundó en terrenos cedidos para tal fin (existen versiones de que fue el Capitán Félix de Torres quien la funda en el año 1784.\n" +
                "Estos terrenos eran reclamados por los indígenas que venían de la Serranía del Perijá, obligando al capitán Salvador Arias a huir.\n" +
                "La creación de este municipio se dio por la división del municipio de Robles, fue el cura Leandro María de Algezáres, quien da el nombre de Agustín Codazzi en honor al militar y geógrafo italiano (año 1958).\n" +
                "La localidad de Agustín Codazzi antes de llegar hasta la categoría de municipio, fue conocida e hizo parte del recordado Robles, época en la cual el Padre Leandro María de Algezáres inició la gesta para la desagregación municipal, conocida en nuestros días, nacional e internacionalmente, como Agustín Codazzi o sencillamente Codazzi.\n" +
                "La creación del municipio de Agustín Codazzi fue propuesta mediante Decreto Nº179 del 25 de febrero de 1958 de la Gobernación del Departamento del Magdalena, seguidamente aprobada mediante resolución Nº 0265 del 9 de abril de 1958 del Ministerio de Gobierno y confirmada a través de la Ordenanza Nº 122 del 12 de noviembre de 1958, expedida por la Honorable Asamblea Departamental de Magdalena.\n" +
                "Agustíno Codazzi fue hijo de Domingo Codazzi, comerciante de telas y de Constanza Bartolotti. Nació el 12 de julio de 1793, en la ciudad de Lugo (Italia).\n" +
                "Durante el período 1850-1859 Codazzi recorrió todo lo que hoy es Colombia en la llamada Comisión Corográfica, que fue un conjunto de geógrafos y naturalista encargados de cartografiar el país. Sin embargo, cuando se disponía a recorrer lo que es la costa atlántica colombiana lo sorprendió un ataque de malaria que lo llevó a su muerte en la población del Espíritu Santo el 7 de febrero de 1859, hoy denominada Codazzi en su honor.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Codazzi");
        values.put(HistoriaBD.HISTORIA_TIPO,"Historias");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.bcodazzi);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,6);
        values.put(HistoriaBD.HISTORIA_NAME,"Historia de El Copey");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Fundado a orillas de la quebrada de su mismo nombre, en el sector de piedras azules por el señor Loño quien tenía unos corrales en lo que hoy es el banco agrario y donde los vaqueros que llevaban ganado hacia la guajira se quedaban a descansar. el Copey debe su nombre a un árbol que para la época era muy abundante, el cual se le llamaba el árbol de cope, se cree que el nombre completo se formó al señalarse un punto de encuentro entre los viajeros uno de estos árboles de Cope y al que se le agregó la palabra hey, la cual era muy común para referirse a usted o para llamar la atención de alguien, quedando con el tiempo como El Copey.\n" +
                "El municipio de El Copey nace siendo la única Villa del Cesar debido a las cantidades de fundadores procedentes de diferentes lugares de Colombia, como Cartagena, Valledupar, Bogotá y Bucaramanga, entre otros. La riqueza hídrica que supone el río hizo que los primeros pobladores se ubicaron a unos 10 metros de él.\n" +
                "Todo parece indicar la historia reciente de El Copey, pero remontándose en sus albores, el Copey surgió gracias a los viajeros Bolivarenses o lo que llamaban el Magdalena Grande y que en principio se radicaron en el que ahora es su mayor corregimiento Caracolicito, ahí llegaron los primeros pobladores de esta zona y luego por disputas de terrenos algunos se desplazaron unos kilómetros más adelante fundando el Municipio actual. Su población originaria es de El Guamo (Bolívar), unas minorías de la Guajira y Vallenatos; otros grupos procedentes de Plato, El Difícil y Fundación.\n" +
                "En la actualidad y debido al desplazamiento goza de una población plural de cachacos, costeños, paisas, santandereanos y otras minorías que han incrementado el comercio en la localidad.\n" +
                "El himno del Copey fue creado por Orlando Vides Duran, nació en el año de 1952 y falleció en el año de 2007, ahora su familia vive en El Copey-Cesar.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"El Copey");
        values.put(HistoriaBD.HISTORIA_TIPO,"Historias");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.bcopey);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,7);
        values.put(HistoriaBD.HISTORIA_NAME,"Historia de La Paz");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"El Municipio de La Paz comenzó a poblarse en enero de 1775 cuando varias familias ganaderas de Valledupar decidieron establecer sus hatos de ganado en el sitio que ellos conocían como Cerros de La Paz, dentro de los grupos de fundadores se encontraban: Simón de Torres, Leonardo del Castillo, Arcisclo Arzuaga y Juan Oñate.\n" +
                "Rápidamente La Paz se convirtió en una alternativa residencial y laboral de personas de diversos puntos de la geografía. Por medio de la Ordenanza N° 4 de 1888, la Asamblea Departamental del Magdalena creó el Municipio del Espíritu Santo (hoy Codazzi), conformado por el territorio de La paz, San Diego y Becerril; en 1936 mediante la Ordenanza N° 8, la Asamblea del Departamento del Magdalena en honor a Luis Rafael Robles, cambió el nombre de Espíritu Santo por el de Robles con cabecera de La Paz.\n" +
                "Cuando fue creado el Departamento del Cesar segregado del Magdalena mediante la Ley 25 del 21 de diciembre de 1967, La Paz quedo siendo la cabecera del Municipio del mismo nombre.\n" +
                "Quedando La Paz como Municipio, se veía en la necesidad de crear una entidad que la dirija como Municipio, se instauró la Alcaldía Municipal de La Paz en representación del poder ejecutivo y así mismo del Estado, siendo su primer alcalde José María Arzuaga.\n" +
                "Los alcaldes eran nombrados por el Gobernador de turno y sin periodo fijo de mandato y la Secretaria de Gobierno se encargaba de realizar las posesiones y despido de empleados de libre nombramiento y remoción.\n" +
                "A partir de 1988 se inicia la elección del alcalde a través del Voto Popular y es en el mandato del segundo alcalde el Señor: Hernán Morón Araujo, en la cual se creó varias jefaturas y eran nombrados por el propio alcalde; entre estas, la Jefatura de Personal, la cual tenía que ver con todos los menesteres como son: tomar posesión, contabilizar incapacidades, expedir certificaciones laborales, tiempo de servicio.\n" +
                "En los últimos años los alcaldes que pasaron por la entidad fueron Hernán Morón, Orlando Cruz Vega, Francisco Mejía, Aris Murgas, Gonzalo Araujo, Juan Bautista Calderón, Antonio María Araujo, Primo León Montaño, Gerardo Alfonso Gutiérrez Arzuaga y Wilson Rincón Álvarez. \n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"La Paz");
        values.put(HistoriaBD.HISTORIA_TIPO,"Historias");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.bpaz);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,8);
        values.put(HistoriaBD.HISTORIA_NAME,"Historia de Manaure");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Fue fundado por el reconocido ganadero del Municipio de la Paz Buenaventura Maya. Manaure Balcón del Cesar ha sido por excelencia el Balcón Turístico del Cesar, se caracteriza por la gran hermosura de su vegetación, la frescura de su medio ambiente la belleza de su río y sobre todo por la amabilidad de sus gentes. Manaure ha sido Musa de inspiración de viejos juglares del vallenato como son. el viejo Emiliano Zuleta, Poncho Cotes, Escalona, Andrés Becerra, Leandro Díaz, Juan Manuel Muegues y muchos otros, que al llegar a esta tierra se prendaron del encanto mágico de su entorno dedicándole sus mejores canciones.\n" +
                "\n" +
                "La historia cuenta que Manaure era una hermosa sabana llena de pastizales y arroyos, ideal para pastorear el ganado de todos los alrededores, que una vez un ganadero del Municipio de La Paz se le extravió una vaca y se adentró hasta encontrarla en esta sabana, que su ubre estaba hinchada y de ella manaba leche, él hizo un gesto de admiración exclamando en estos pastizales a las vacas les mana la ubre, de esta manera nació el nombre de nuestro municipio. Manaure, que inicialmente fue bautizado como sabana donde mana la ubre, y luego por convergencia de lenguaje se unificó el término a Manaure, existe otra historia acerca del nombre de nuestro municipio según cuentan nuestros antepasados que estas tierras eran habitadas por los indios bobures o boredes descendientes de los caribes los cuales eran oriundos de la República de Venezuela, quienes emigraron a esta región a través de la Serranía del Perijá, los cuales eran de costumbres nómadas vivían en rocas y se dedicaban a la caza, la pesca, la incipiente agricultura y la ganadería, al llegar a estas tierras se establecieron en las colinas que están alrededor de estas sabanas, el cacique de esta tribu se llamaba Cacique Manaure, cuando continuaron su vida nómada a otros lugares, esta sabana adopta el nombre de dicho cacique. \n" +
                "\n" +
                "Fue la hermosura de estos pastizales un atractivo para las gentes del municipio de la Paz ubicados a unos 10 km de esta sabana y enamorados de estas tierras empezaron a frecuentarlas hasta que decidieron hacer asentamientos en ella, de esta manera se fue poblando poco a poco hasta llegar a elevarla a corregimiento en 1.913, fueron sus primeros pobladores gentes emprendedoras de la Paz y Villanueva, quienes además de practicar la ganadería empezaron a fomentar la agricultura y organizar algunas fincas, posteriormente para los años 58, 59 y 60 producto de la violencia que azotó el país, en ese entonces con la muerte de Jorge Eliécer Gaitán hubo una gran migración de personas de interior del país sobre todo Nortesantandereanos, gente trabajadora que al vincularse como trabajadores a las fincas cafeteras existentes en la región le dieron desarrollo y progreso al municipio y fue así como empezó el emporio de una tierra agrícola que llego a convertirse en la despensa agrícola del Cesar, estos santandereanos se hicieron propietarios de muchas fincas de la región convirtiéndolas en fuentes de gran empleo y progreso, este avance generó que los dirigentes comunitarios del corregimiento gestionaran ante la esfera departamental y nacional la segregación de La Paz y la Municipalidad, entre ellos podemos mencionar a destacadas personalidades como Guillermo Araque, Arturo Navarro, Arturo Acosta, Julio Maestre, José Ángel Pacheco, quienes se encargaron de recoger las 600 firmas de las 200 exigidas, estampadas en 10 hojas de papel sellado y sin mayores exigencias de ley pues se acogían a los beneficio que otorgaba la ley de la frontera en la pretensión de convertirnos en municipio, este proceso contó con la ayuda y el aval del entonces Gobernador del Cesar Guillermo Pepe Castro quien con gran entusiasmo inició los trámites de rigor, mientras tanto los habitantes del corregimiento tenían su atención fijada en los trámites que el proyecto tenía en el departamento, la asamblea y la nación, quienes eran los entes decisorios, finalmente mediante la ordenanza 28 del 28 de noviembre de 1.980 la asamblea departamental del cesar eleva a municipio el corregimiento de Manaure, posteriormente el presidente Julio Cesar Turbay Ayala en un gesto de buena voluntad y sin mayor demora firmó la aprobación del proyecto de ley el 21 de diciembre de 1.980, su primer alcalde fue Alfonso Murgas, nombrado por decreto departamental, continúan posteriormente Guillermo Araque García, Enrique Campo Mieles, Claudio Morón, Orlando Velásquez, Jesús Palmera, Jaider López, Claudio Morón.\n" +
                "\n" +
                "El primer alcalde electo por el pueblo fue Enrique Campo Mieles, con quien se empieza a vislumbrar el desarrollo y l JUNA a autonomía Municipal, lo sucede José Maria Castro como alcalde encargado durante tres meses, sucede Virgilio Ardila elegido como el mejor alcalde del país, continúan en su orden José Perpiñan, repite Virgilio Ardila, Gonzalo Aguirre, Hilis Plata, Henry Oñate Fragozo, Noheli Rincón y actualmente dignamente administrada por el Doctor JUAN CARLOS ARAUJO OROZCO con su plan de desarrollo GANA MANAURE GANAMOS TODOS CON PROSPERIDAD A SALVO.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Manaure");
        values.put(HistoriaBD.HISTORIA_TIPO,"Historias");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.bmanaure);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,9);
        values.put(HistoriaBD.HISTORIA_NAME,"Historia de Pueblo Bello");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"A raíz de las persecuciones en Europa ocasionadas entre las dos guerras mundiales llegaron a este lugar inmigrantes europeos especialmente desde Alemania quienes se quedan en estos parajes por la tranquilidad de sus montañas y lo suave de su clima. El territorio geográfico donde se inicia la construcción del caserío está situado dentro de la Línea Negra (territorio sagrado) del Resguardo Indígena Arhuaco.\n" +
                "Finalizando la década de los años 20 Lucila Mestre, hija de Binerva Triana de Mestre lleva al Concejo de Valledupar el proyecto de cambio de nombre y en memoria de su querida madre quien continuamente manifestaba que un paisaje tan hermoso con pintorescas casas y laboriosos y buenos moradores debería llamarse Pueblo Bello, el que fue aprobado por Acuerdo Municipal, dándose definitivamente el nombre de Pueblo Bello. A este territorio donde las personas acomodadas de Valledupar y Barranquilla construyeron una pista de aterrizaje para en épocas de vacaciones llegaran en sus avionetas y disfrutaran de la suavidad del clima y la tranquilidad que daba la población.\n" +
                "La población de Pueblo Bello es de 17,228 habitantes según el censo de DANE en el año 2005, de los cuales más del 70% son indígenas Arhuacos.\n" +
                "De 1940 en adelante y con la violencia existente en el interior del país, oleadas de campesinos provenientes de la zona andina llegan a estas tierras para ir descombrando las montañas, reemplazando la vegetación natural por el cultivo del café convirtiéndose este en la base de la economía aumentándose el número de mulares para el acarreo del grano.\n" +
                "Al abrir la carretera Valledupar - Pueblo Bello, aumentan los inmigrantes surgiendo comercio que abastece a toda esta vasta región.\n" +
                "A partir de 1960 la Federación Nacional de Cafeteros abre una agencia de compra de café facilitándole a estos campesinos la venta de sus cosechas al irse mezclando la población surge un grupo humano heterogéneo por lo que no ha sido posible mantener una unidad cultural y cada familia se esmera por su bienestar económico olvidándose del trabajo comunitario para que este caserío progrese.\n" +
                "En 1997 en medio del conflicto armado, el Gobernador Departamental del Cesar, Mauricio Pimiento, y el alcalde municipal de Valledupar, Rodolfo Campo Soto (sin consulta previa alguna), crearon el municipio de Pueblo Bello bajo la ordenanza 037 del 10 de diciembre de 1997. Con ésta medida se abrió el espacio para que colonos y comerciantes, y miembros del control político y económico del territorio, impusieran en el consejo municipal, un plan de ordenamiento territorial (de nuevo sin consulta previa),3 que introdujo programas, proyectos y estrategias, que contribuyeron a la contaminación y a la profanación permanente de los espacios indígenas sagrados y de interés, protección y manejo cultural, establecido por la constitución del año 1991 y los acuerdos internacionales de la OIT y la UNESCO, todo lo cual choca contra los principios de la cultura aborigen, invisibiliza las dinámicas propias, obstaculiza el ejercicio de gobierno propio, de las prácticas tradicionales y la vigencia de la Ley de origen; vulnera los derechos a la autonomía y libre determinación y acentúa el deterioro cultural Arhuaco.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Pueblo Bello");
        values.put(HistoriaBD.HISTORIA_TIPO,"Historias");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.bpueblo);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,10);
        values.put(HistoriaBD.HISTORIA_NAME,"Historia de Valledupar");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Antes de la llegada de los españoles que ocurrió durante la primera mitad del siglo XVI, la zona correspondiente a la llamada Provincia de Padilla estuvo poblada por el grupo etnolingüístico Chimila, que conformó una división territorial dividida en dos zonas: al norte la región del Cacique Upar y al Sur las bajas planicies de Chiriguaná. Los Chimilas pertenecían a la gran familia chibcha.\n" +
                "Valledupar fue fundada el 6 de enero de 1550 por los conquistadores españoles capitán Hernando de Santana y Juan de Castellanos. Para el asentamiento de la fundación el capitán español escogió la parte septentrional de Valledupar, bañado por el río Guatapurí, que en lengua Chimila significa \"agua fría\". Valledupar fue erigida en parroquia en 1560.\n" +
                "Valledupar fue erigida capital de la Provincia de Valledupar de conformidad con la ley 15 de 1850, y fue elevada a capital del departamento del Valle de Upar del Estado Soberano del Magdalena de acuerdo con la ley 29 de diciembre de 1864. Al reestructurarse la división político-administrativa del Estado Unitario colombiano, fue erigida como municipio del departamento del Magdalena según la ordenanza número 57 de 1915, la cual estableció su extensión y límites.\n" +
                "Al crearse el departamento del Cesar mediante la ley 25 de 1967, Valledupar fue escogida como su capital.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Valledupar");
        values.put(HistoriaBD.HISTORIA_TIPO,"Historias");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.bvalledupar);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);
    }

    public void guardarCulturaJuglares(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idHistoria;
        ContentValues values =  new ContentValues();
        values.put(HistoriaBD.HISTORIA_ID,11);
        values.put(HistoriaBD.HISTORIA_NAME,"Gustavo Gutiérrez");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Gustavo Gutiérrez Cabello (Valledupar, Cesar, 12 de septiembre de 1940) es un músico colombiano, cantante, compositor, guitarrista y acordeonista de música vallenata. \n" +
                "Su estilo de composición vallenata es catalogado como \"romántico, filosófico y poético\" y es considerado uno de los creadores del \"vallenato lírico\" junto a Santander Durán Escalona, Octavio Daza, Freddy Molina, Rita Fernández Padilla, Sergio Moya Molina, Hernando Marín, Mateo Torres, Rosendo Romero y Fernando Meneses Romero.\n" +
                "Sus canciones han sido grabadas por Los Hermanos Zuleta, Jorge Oñate, Diomedes Díaz, el Binomio de Oro, Los Betos, Iván Villazón, Silvio Brito, Alfredo Gutiérrez, la Billo's Caracas Boys, Los Melódicos, La Tremenda de Venezuela, Pacho Galán, entre otros.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Valledupar");
        values.put(HistoriaBD.HISTORIA_TIPO,"Juglares");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.gustavoguti);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,12);
        values.put(HistoriaBD.HISTORIA_NAME,"Rafael Escalona");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Rafael Calixto Escalona Martínez (Valledupar, 26 de mayo de 1927- Bogotá, 13 de mayo de 2009) conocido como \"El maestro Escalona\", fue un compositor colombiano, considerado uno de los más grandes compositores de la música vallenata. \n" +
                "Escalona fue cofundador del Festival de la Leyenda Vallenata junto a la gestora cultural Consuelo Araújo Noguera y el político liberal Alfonso López Michelsen. Por su amistad con López, Escalona fue nombrado Cónsul de Colombia en Colón, Panamá una vez López llegó a la presidencia de Colombia. \n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Valledupar");
        values.put(HistoriaBD.HISTORIA_TIPO,"Juglares");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.escalona);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,13);
        values.put(HistoriaBD.HISTORIA_NAME,"Leandro Díaz");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Leandro José Díaz Duarte (Hatonuevo, La Guajira, 20 de febrero de 1928 - Valledupar, Cesar, 22 de junio de 2013) fue un compositor colombiano y uno de los símbolos de la música vallenata.3 Fue conocido por su composición descriptiva y narrativa.\n" +
                "En la 38.ª versión del Festival de la Leyenda Vallenata, proclamaron a Leandro como \"Rey a Vida del Festival de la Leyenda Vallenata\" acompañado de Rafael Escalona, Emiliano Zuleta Baquero, Calixto Ochoa, Adolfo Pacheco y Tobías Enrique Pumarejo.");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Valledupar");
        values.put(HistoriaBD.HISTORIA_TIPO,"Juglares");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.leandrodiaz);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,14);
        values.put(HistoriaBD.HISTORIA_NAME,"Jorge Oñate");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Jorge Antonio González Oñate (La Paz, 31 de marzo de 1950), conocido como Jorge Oñate y apodado \"El Jilguero de América\" y \"El Ruiseñor del Cesar\", es un músico colombiano, cantante y compositor de música vallenata. Es considerado uno de los cantantes más importantes de la música vallenata junto a Diomedes Díaz, Rafael Orozco y Poncho Zuleta, por su gran aporte a la música vallenata ha sido grandemente homenajeado y denominado \"la leyenda\". Desde el comienzo de su carrera, en 1968 a 2012, había logrado 25 discos de oro, 7 discos de platino y 6 de doble platino. También incursionó en política como concejal de su pueblo natal, subdirector del departamento del Cesar y segundo en la lista del congresista Alfredo Cuello Dávila, a quien reemplazó en varias ocasiones.");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"La Paz");
        values.put(HistoriaBD.HISTORIA_TIPO,"Juglares");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.jorgeonate);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,15);
        values.put(HistoriaBD.HISTORIA_NAME,"Colacho Mendoza");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Nicolás Elías Mendoza Daza (San Juan del Cesar, 15 de abril de 1936 - Valledupar, 27 de septiembre de 2003), conocido como \"Colacho\" Mendoza\", fue un acordeonero colombiano de vallenato. Es considerado uno de los mejores acordeoneros de todos los tiempos en la música vallenata por su extensa trayectoria como Rey Vallenata, Rey de Reyes del Festival y haber grabado con los más reconocidos intérpretes del género. \n" +
                "Fue \"Rey Vallenato\", categoría \"Acordeón Profesional\" del Festival de la Leyenda Vallenata en 1969 y primer \"Rey de Reyes\" en 1987, duelo que le ganó a otro gran acordeonero, Alejandro Durán. \n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Valledupar");
        values.put(HistoriaBD.HISTORIA_TIPO,"Juglares");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.colacho);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);
    }

    public void guardarCulturaMusica(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idHistoria;
        ContentValues values =  new ContentValues();
        values.put(HistoriaBD.HISTORIA_ID,16);
        values.put(HistoriaBD.HISTORIA_NAME,"Vallenato");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"Este género musical nace en Valledupar, donde yacían tribus indígenas, incluidas las Chimilas y Tupes, gobernadas por un poderoso jefe conocido como el Cacique Upar. Ahí es donde la ciudad recibe su nombre (Valle de Upar) y vallenato, a su vez, significa “nacido en el valle”.\n" +
                "Se dice que los agricultores de la región heredaron las tradiciones de juglares españoles y africanos, cantando y tocando sus instrumentos mientras viajaban de ciudad en ciudad con sus vacas, compartiendo noticias y mensajes. Finalmente, los instrumentos africanos e indígenas, como las flautas de gaita, la guacharaca y los tambores, se unieron al acordeón europeo y así es como nace el vallenato.\n" +
                "El Vallenato se toca al día de hoy con estos tres instrumentos musicales principales:\n" +
                "-\tUn pequeño tambor (caja vallenata) sostenido entre las rodillas y tocado con las manos desnudas, una tradición traída a Colombia por esclavos africanos.\n" +
                "-\tLa guacharaca, un instrumento indígena diseñado para imitar la llamada del ave guacharaca y utilizado inicialmente para rituales de caza y citas. Es acanalado y de madera, raspado con un tenedor de tres puntas.\n" +
                "-\tEl acordeón, originario de Alemania.\n" +
                "Por esta razón, el vallenato es tan especial, representa la unión de tres culturas (indígenas, europeas y africanas) en un género único que ha roto fronteras.\n");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Valledupar");
        values.put(HistoriaBD.HISTORIA_TIPO,"Musica");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.vallenato);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);

        values.put(HistoriaBD.HISTORIA_ID,17);
        values.put(HistoriaBD.HISTORIA_NAME,"Porro");
        values.put(HistoriaBD.HISTORIA_DESCRIPCION,"El porro es un ritmo musical de la Región Caribe colombiana, tradicional de los departamentos de Córdoba, Bolívar y Sucre. Posee un ritmo cadencioso, alegre y fiestero, propicio para el baile en parejas. Se ejecuta en compás de 2/2 o, como se le dice popularmente en América, compás partido. Es una música fiestera popular que generalmente es interpretada por bandas conocidas en Colombia como \"Pelayeras\", también conocidas como \"Bandas de Músicos\". Actualmente en Aguachica se celebra el Festival del Porro.");
        values.put(HistoriaBD.HISTORIA_MUNICIPIO,"Aguachica");
        values.put(HistoriaBD.HISTORIA_TIPO,"Musica");
        values.put(HistoriaBD.HISTORIA_IMAGE,R.drawable.porro);
        idHistoria = db.insert(HistoriaBD.TABLE_HISTORIAS, null, values);
    }

    public void guardarActividades(){
        SQLiteDatabase db = database.getWritableDatabase();
        long idActividad;
        ContentValues values =  new ContentValues();
        values.put(ActividadBD.ACTIVIDAD_ID,0);
        values.put(ActividadBD.ACTIVIDAD_NAME,"Avistamiento de aves");
        values.put(ActividadBD.ACTIVIDAD_DESCRIPCION,"En el Cesar puede encontrar alrededor de 500 especies de aves, con 4 focos de especial interés:\n" +
                "  1.\tSerranía del Perijá.\n" +
                "  2.\tSierra Nevada.\n" +
                "  3.\tCiénaga de Zapatosa.\n" +
                "  4.\tBosque seco del Valle del Cesar\n" +
                "Lo puede hacer en el municipio de Manaure Balcón Turístico del Cesar, en el Eco Parque Los Besotes y en la Ciénaga de Zapatosa en Chimichagua.\n" +
                "Lugares destacados para observación de aves en Manaure:\n" +
                "  1.\tReserva Natural Los Tananeos.\n" +
                "  2.\tCentro Turístico Villa Adelaida.\n" +
                "  3.\tFinca Ecoturística La Danta.\n" +
                "  4.\tFinca Cafetera Las Nieves (vereda San Antonio).\n" +
                "  5.\tReserva Natural ProAves (vereda El Cinco).\n" +
                "Disponibilidad permanente. Los horarios, días e itinerarios se establecen con base en la preferencia del cliente.");
        values.put(ActividadBD.ACTIVIDAD_CONTACTO,"Guía José Luis Ropero - 317 626 8212");
        values.put(ActividadBD.ACTIVIDAD_TIPO,"Avistamiento de aves");
        values.put(ActividadBD.ACTIVIDAD_IMAGE,R.drawable.fotoaves);
        idActividad = db.insert(ActividadBD.TABLE_ACTIVIDADES, null, values);

        values.put(ActividadBD.ACTIVIDAD_ID,1);
        values.put(ActividadBD.ACTIVIDAD_NAME,"Caminatas");
        values.put(ActividadBD.ACTIVIDAD_DESCRIPCION,"Este plan es perfecto para todo aquel que disfrute ejercitar su cuerpo, disfrutando de la naturaleza y el clima fresco de la mañana. \n" +
                "  - Mirador del Santo Ecce Homo: Si visitas Valledupar puedes realizar caminatas por el parque lineal del rio Guatapurí y conectar con los senderos del cerro del mirador del Santo Ecce Homo. \n" +
                "  - Manaure: En este municipio encontraras el Cerro de la Cruz y los caminos hasta Sabana Rubia.  \n" +
                "  - Recomedaciones: Ropa y calzado adecuados. Si realizas estas actividades en Valledupar ten en cuenta que el clima es cálido y seco, la hidratación es fundamental. En Manaure ten en cuenta que la mayoría de los senderos son en altura; en el caso de Sabana Rubia llegarás hasta los 3.000 m.s.n.m por lo que debes llevar ropa para clima frio.");
        values.put(ActividadBD.ACTIVIDAD_CONTACTO,"No disponible");
        values.put(ActividadBD.ACTIVIDAD_TIPO,"Caminatas");
        values.put(ActividadBD.ACTIVIDAD_IMAGE,R.drawable.fotocaminatas);
        idActividad = db.insert(ActividadBD.TABLE_ACTIVIDADES, null, values);

        values.put(ActividadBD.ACTIVIDAD_ID,2);
        values.put(ActividadBD.ACTIVIDAD_NAME,"Bañarse en el río");
        values.put(ActividadBD.ACTIVIDAD_DESCRIPCION,"Colombia es uno de los países con mayor número de recursos hídricos en el mundo. En él se pueden encontrar seis tipos de aguas, incluyendo aguas lluvias, aguas superficiales, aguas subterráneas, aguas termo minerales, aguas marinas y oceánicas y aguas de alimentación glacial. El Cesar cuenta con una riqueza hídrica que puede aprovechar para darse un refrescante chapuzón. Disfrute de este plan junto a su familia y seguramente querrá volver.\n" +
                "Puede disfrutar de este plan en el Río Guatapurí en Valledupar, en el Balneario del Río Badillo en Patillal y en el sector de La Mina.");
        values.put(ActividadBD.ACTIVIDAD_CONTACTO,"No disponible");
        values.put(ActividadBD.ACTIVIDAD_TIPO,"Bañarse en el río");
        values.put(ActividadBD.ACTIVIDAD_IMAGE,R.drawable.fotorios);
        idActividad = db.insert(ActividadBD.TABLE_ACTIVIDADES, null, values);

        values.put(ActividadBD.ACTIVIDAD_ID,3);
        values.put(ActividadBD.ACTIVIDAD_NAME,"Canotaje");
        values.put(ActividadBD.ACTIVIDAD_DESCRIPCION,"El ecoturismo y turismo de aventura, tienen su espacio en un solo Departamento, el Cesar ofrece distintas oportunidades a los visitantes, debido a los diferentes climas, fuentes hídricas y riqueza cultural que caracteriza a esta región. El departamento del Cesar cuenta con una gran cantidad de ríos con las características requeridas para hacer canotaje. La Ciénaga de Zapatosa en Chimichagua, es el mejor sitio para disfrutar esta actividad. Además, en las riberas del río Magdalena y los ríos Guatapurí y Cesar.");
        values.put(ActividadBD.ACTIVIDAD_CONTACTO,"No disponible");
        values.put(ActividadBD.ACTIVIDAD_TIPO,"Canotaje");
        values.put(ActividadBD.ACTIVIDAD_IMAGE,R.drawable.fotocanotaje);
        idActividad = db.insert(ActividadBD.TABLE_ACTIVIDADES, null, values);
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

    private void solicitarpermisos(){
        int permisoFineGPS = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permisoCoarseGPS = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permisoFineGPS!=PackageManager.PERMISSION_GRANTED || permisoCoarseGPS!=PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ASK_PERMISSION);
            }
        }
    }
}
