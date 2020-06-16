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
public class CategoriaHistoriasFragment extends Fragment {

    public CategoriaHistoriasFragment() {
        // Required empty public constructor
    }

    CardView cardViewHistorias;
    CardView cardViewJuglares;
    CardView cardViewMusica;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categoria_historias, container, false);

        cardViewHistorias = view.findViewById(R.id.btnhistorias);
        cardViewJuglares = view.findViewById(R.id.btnjuglares);
        cardViewMusica = view.findViewById(R.id.btnmusica);

        Bundle datoRecibido = getArguments();
        final String dato = datoRecibido.getString("municipio");
        Toast.makeText(getContext(), "Municipio recibido: "+dato, Toast.LENGTH_SHORT).show();

        cardViewHistorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Historias");
                bundle.putString("municipio", dato);
                Toast.makeText(getContext(), "Option: Historias", Toast.LENGTH_SHORT).show();
                Fragment fragment = new HistoriasFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewJuglares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Juglares");
                bundle.putString("municipio", dato);
                Toast.makeText(getContext(), "Option: Juglares", Toast.LENGTH_SHORT).show();
                Fragment fragment = new HistoriasFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Musica");
                bundle.putString("municipio", dato);
                Toast.makeText(getContext(), "Option: Musica", Toast.LENGTH_SHORT).show();
                Fragment fragment = new HistoriasFragment();
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
