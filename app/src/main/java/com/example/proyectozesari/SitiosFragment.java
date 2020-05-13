package com.example.proyectozesari;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SitiosFragment extends Fragment {

    public SitiosFragment() {
        // Required empty public constructor
    }

    SitioAdapter sitioAdapter;
    RecyclerView recyclerView;
    ArrayList<Sitios> listaSitios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sitios, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        listaSitios = new ArrayList<>();
        uploadList();
        displayInfo();
        return view;
    }

    public void uploadList(){
        listaSitios.add(new Sitios(
                "Parque de las Monedas",
                "Patillal",
                "Parque ubicado en Patillal, corregimiento a media hora al norte de Valledupar, donde se rinde homenaje a destacados compositores vallenatos nacidos en este pueblo, entre los que se encuentra el maestro Rafael Escalona.",
                "Patillal, Valledupar, Cesar",
                R.drawable.parque_monedas));
        listaSitios.add(new Sitios(
                "Balneario La Vega",
                "Patillal",
                "A 20 minutos de Valledupar en dirección a Patillal, tierra de compositores como el maestro Rafael Escalona. Es uno de los cauces por donde pasa el famosos río Badillo, del cual se han escrito canciones vallenatas.",
                "Patillal, Valledupar, Cesar",
                R.drawable.balneario_vega));
        listaSitios.add(new Sitios(
                "Finca Ecoturística la Danta",
                "Manaure",
                "La Danta, una especie de eco-parque multiservicios enclavado en las estribaciones de la vertiente occidental de la Serranía del Perijá, más específicamente en el municipio de Manaure",
                "Río, Manaure, Cesar",
                R.drawable.finca_danta));
        listaSitios.add(new Sitios(
                "Mi Pedazo de Acordeón",
                "Valledupar",
                "El Monumento Mi Pedazo de Acordeón rinde homenaje a Alejo Durán, primer Rey Vallenato y al principal instrumento e la música vallenata.",
                "Cra. 9 #3-2, Valledupar, Cesar",
                R.drawable.pedazo_acordeon));
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
            }
        });
    }
}
