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
public class CategoriaActividadesFragment extends Fragment {

    public CategoriaActividadesFragment() {
        // Required empty public constructor
    }

    CardView cardViewAves;
    CardView cardViewRio;
    CardView cardViewCaminata;
    CardView cardViewCanotaje;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categoria_actividades, container, false);

        cardViewAves = view.findViewById(R.id.btnaves);
        cardViewRio = view.findViewById(R.id.btnrio);
        cardViewCaminata = view.findViewById(R.id.btncaminata);
        cardViewCanotaje = view.findViewById(R.id.btncanotaje);

        cardViewAves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Avistamiento de aves");
                Toast.makeText(getContext(), "Option: Avistamiento de aves", Toast.LENGTH_SHORT).show();
                Fragment fragment = new ActividadesFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Bañarse en el río");
                Toast.makeText(getContext(), "Option: Bañarse en el río", Toast.LENGTH_SHORT).show();
                Fragment fragment = new ActividadesFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewCaminata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Caminatas");
                Toast.makeText(getContext(), "Option: Caminatas", Toast.LENGTH_SHORT).show();
                Fragment fragment = new ActividadesFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        cardViewCanotaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("categoria", "Canotaje");
                Toast.makeText(getContext(), "Option: Canotaje", Toast.LENGTH_SHORT).show();
                Fragment fragment = new ActividadesFragment();
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
