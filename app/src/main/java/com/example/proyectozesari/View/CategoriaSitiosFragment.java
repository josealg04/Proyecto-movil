package com.example.proyectozesari.View;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectozesari.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriaSitiosFragment extends Fragment {

    public CategoriaSitiosFragment() {
        // Required empty public constructor
    }

    CardView cardViewRestaurantes;
    CardView cardViewHoteles;
    CardView cardViewBares;
    CardView cardViewMonumentos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_categoria_sitios, container, false);

        cardViewRestaurantes = view.findViewById(R.id.btnrestaurantes);
        cardViewHoteles = view.findViewById(R.id.btnhoteles);
        cardViewBares = view.findViewById(R.id.btnbares);
        cardViewMonumentos = view.findViewById(R.id.btnmonumentos);

        Bundle datoRecibido = getArguments();
        final String dato = datoRecibido.getString("municipio");
        Toast.makeText(getContext(), "Municipio recibido: "+dato, Toast.LENGTH_SHORT).show();

        cardViewRestaurantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Restaurantes");
                bundle.putString("municipio", dato);
                Toast.makeText(getContext(), "Option: Restaurantes", Toast.LENGTH_SHORT).show();
                Fragment fragment = new SitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewHoteles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Hoteles");
                bundle.putString("municipio", dato);
                Toast.makeText(getContext(), "Option: Hoteles", Toast.LENGTH_SHORT).show();
                Fragment fragment = new SitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewBares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Bares");
                bundle.putString("municipio", dato);
                Toast.makeText(getContext(), "Option: Bares", Toast.LENGTH_SHORT).show();
                Fragment fragment = new SitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewMonumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Monumentos");
                bundle.putString("municipio", dato);
                Toast.makeText(getContext(), "Option: Monumentos", Toast.LENGTH_SHORT).show();
                Fragment fragment = new SitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        return view;
    }
}
