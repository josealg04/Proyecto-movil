package com.example.proyectozesari.View;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
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
public class MunicipiosFragment extends Fragment {

    public MunicipiosFragment() {
        // Required empty public constructor
    }

    CardView cardViewAguachica;
    CardView cardViewBosconia;
    CardView cardViewCodazzi;
    CardView cardViewCopey;
    CardView cardViewPaz;
    CardView cardViewManaure;
    CardView cardViewPb;
    CardView cardViewValledupar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_municipios, container, false);

        cardViewAguachica = view.findViewById(R.id.btnaguachica);
        cardViewBosconia = view.findViewById(R.id.btnbosconia);
        cardViewCodazzi = view.findViewById(R.id.btnacodazzi);
        cardViewCopey = view.findViewById(R.id.btncopey);
        cardViewPaz = view.findViewById(R.id.btnpaz);
        cardViewManaure = view.findViewById(R.id.btnmanaure);
        cardViewPb = view.findViewById(R.id.btnpueblobello);
        cardViewValledupar = view.findViewById(R.id.btnvalledupar);

        Bundle datoRecibido = getArguments();
        String dato = datoRecibido.getString("opcion");
        Toast.makeText(getContext(), "Opcion recibida: "+dato, Toast.LENGTH_SHORT).show();

        cardViewAguachica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("municipio", "Aguachica");
                Toast.makeText(getContext(), "Option: Aguachica", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CategoriaSitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewBosconia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("municipio", "Bosconia");
                Toast.makeText(getContext(), "Option: Bosconia", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CategoriaSitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewCodazzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("municipio", "Codazzi");
                Toast.makeText(getContext(), "Option: Codazzi", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CategoriaSitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewCopey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("municipio", "El Copey");
                Toast.makeText(getContext(), "Option: El Copey", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CategoriaSitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewPaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("municipio", "La Paz");
                Toast.makeText(getContext(), "Option: La Paz", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CategoriaSitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewManaure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("municipio", "Manaure");
                Toast.makeText(getContext(), "Option: Manaure", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CategoriaSitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewPb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("municipio", "Pueblo Bello");
                Toast.makeText(getContext(), "Option: Pueblo Bello", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CategoriaSitiosFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewValledupar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("municipio", "Valledupar");
                Toast.makeText(getContext(), "Option: Valledupar", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CategoriaSitiosFragment();
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
