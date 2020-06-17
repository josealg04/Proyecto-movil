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
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectozesari.Helpers.DatabaseHelper;
import com.example.proyectozesari.Model.Actividades;
import com.example.proyectozesari.Model.Sitios;
import com.example.proyectozesari.Presenter.iFragmentsCommunicate;
import com.example.proyectozesari.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActividadesFragment extends Fragment {

    public ActividadesFragment() {
        // Required empty public constructor
    }

    ActividadAdapter actividadAdapter;
    RecyclerView recyclerView;
    TextView textView;
    ArrayList<Actividades> listaActividades;
    com.example.proyectozesari.Presenter.iFragmentsCommunicate iFragmentsCommunicate;
    Activity activity;
    SQLiteDatabase db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actividades, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        textView = view.findViewById(R.id.emptyrecycler);
        listaActividades = new ArrayList<>();
        uploadList();
        displayInfo();
        return view;
    }

    public void uploadList(){
        Bundle datoRecibido = getArguments();
        String datoCategoria = datoRecibido.getString("categoria");
        Toast.makeText(getContext(), "Categoria recibido: "+datoCategoria, Toast.LENGTH_SHORT).show();
        String[] datos = new String[] {datoCategoria};
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM actividades WHERE tipo=? ", datos);
        while(cursor.moveToNext()) {
            Actividades actividad = new Actividades();
            actividad.setActividadId(cursor.getInt(0));
            actividad.setActividadName(cursor.getString(1));
            actividad.setActividadDescripcion(cursor.getString(2));
            actividad.setActividadMunicipio(cursor.getString(3));
            actividad.setActividadDireccion(cursor.getString(4));
            actividad.setActividadTipo(cursor.getString(5));
            actividad.setActividadImageId(cursor.getInt(6));
            listaActividades.add(actividad);
        }
        cursor.close();
    }

    public void displayInfo(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        actividadAdapter = new ActividadAdapter(getContext(), listaActividades);
        if(!listaActividades.isEmpty()) {
            recyclerView.setAdapter(actividadAdapter);
            actividadAdapter.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    String name = listaActividades.get(recyclerView.getChildAdapterPosition(view)).getActividadName();
                    Toast.makeText(getContext(), "Option: "+name, Toast.LENGTH_SHORT).show();
                    iFragmentsCommunicate.detalleActividad(listaActividades.get(recyclerView.getChildAdapterPosition(view)));
                }
            });
        }else{
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
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
