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
import com.example.proyectozesari.Model.Historias;
import com.example.proyectozesari.Model.Sitios;
import com.example.proyectozesari.R;
import com.example.proyectozesari.Presenter.iFragmentsCommunicate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoriasFragment extends Fragment {

    public HistoriasFragment() {
        // Required empty public constructor
    }

    HistoriaAdapter historiaAdapter;
    RecyclerView recyclerView;
    TextView textView;
    ArrayList<Historias> listaHistorias;
    iFragmentsCommunicate iFragmentsCommunicate;
    Activity activity;
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historias, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        textView = view.findViewById(R.id.emptyrecycler);
        listaHistorias = new ArrayList<>();
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
        Cursor cursor = db.rawQuery(" SELECT * FROM historias WHERE municipio=? AND tipo=? ", datos);
        while(cursor.moveToNext()) {
            Historias historia = new Historias();
            historia.setHistoriaId(cursor.getInt(0));
            historia.setHistoriaName(cursor.getString(1));
            historia.setHistoriaDescripcion(cursor.getString(2));
            historia.setHistoriaMunicipio(cursor.getString(3));
            historia.setHistoriaTipo(cursor.getString(4));
            historia.setHistoriaImageId(cursor.getInt(5));
            listaHistorias.add(historia);
        }
        cursor.close();
    }

    public void displayInfo(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historiaAdapter = new HistoriaAdapter(getContext(), listaHistorias);
        if(!listaHistorias.isEmpty()) {
            recyclerView.setAdapter(historiaAdapter);
            historiaAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = listaHistorias.get(recyclerView.getChildAdapterPosition(view)).getHistoriaName();
                    Toast.makeText(getContext(), "Option: " + name, Toast.LENGTH_SHORT).show();
                    iFragmentsCommunicate.detalleHistoria(listaHistorias.get(recyclerView.getChildAdapterPosition(view)));
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
