package com.example.proyectozesari.View;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectozesari.Helpers.DatabaseHelper;
import com.example.proyectozesari.Helpers.SitioBD;
import com.example.proyectozesari.Model.Sitios;
import com.example.proyectozesari.R;
import com.example.proyectozesari.Presenter.iFragmentsCommunicate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SitiosFragment extends Fragment{

    public SitiosFragment() {
        // Required empty public constructor
    }

    SitioAdapter sitioAdapter;
    RecyclerView recyclerView;
    ArrayList<Sitios> listaSitios;
    iFragmentsCommunicate iFragmentsCommunicate;
    Activity activity;
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sitios, container, false);

        /*Bundle datoRecibido = getArguments();
        String datoCategoria = datoRecibido.getString("categoria");
        String datoMunicipio = datoRecibido.getString("municipio");
        Toast.makeText(getContext(), "Municipio recibido: "+datoMunicipio, Toast.LENGTH_SHORT).show();*/

        recyclerView = view.findViewById(R.id.recyclerView);
        listaSitios = new ArrayList<>();
        uploadList();
        displayInfo();
        return view;
    }

    public void uploadList(){
        Bundle datoRecibido = getArguments();
        String datoCategoria = datoRecibido.getString("categoria");
        String datoMunicipio = datoRecibido.getString("municipio");
        Toast.makeText(getContext(), "Municipio recibido: "+datoMunicipio, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "Categoria recibido: "+datoCategoria, Toast.LENGTH_SHORT).show();
        String[] datos = new String[] {datoMunicipio,datoCategoria};
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM sitios WHERE municipio=? AND tipo=? ", datos);
        while(cursor.moveToNext()) {
            Sitios sitio = new Sitios();
            sitio.setSitioId(cursor.getInt(0));
            sitio.setSitioName(cursor.getString(1));
            sitio.setSitioDescripcion(cursor.getString(2));
            sitio.setSitioMunicipio(cursor.getString(3));
            sitio.setSitioDireccion(cursor.getString(4));
            sitio.setSitioTipo(cursor.getString(5));
            sitio.setSitioImageId(cursor.getInt(6));
            listaSitios.add(sitio);
        }
        cursor.close();
    }

    public void displayInfo(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sitioAdapter = new SitioAdapter(getContext(), listaSitios);
        recyclerView.setAdapter(sitioAdapter);
        sitioAdapter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String name = listaSitios.get(recyclerView.getChildAdapterPosition(view)).getSitioName();
                Toast.makeText(getContext(), "Option: "+name, Toast.LENGTH_SHORT).show();
                iFragmentsCommunicate.detalleSitio(listaSitios.get(recyclerView.getChildAdapterPosition(view)));
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity = (Activity) context;
            iFragmentsCommunicate = (iFragmentsCommunicate) this.activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
