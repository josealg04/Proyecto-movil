package com.example.proyectozesari.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectozesari.Model.Actividades;
import com.example.proyectozesari.Model.Sitios;
import com.example.proyectozesari.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleActividadesFragment extends Fragment {

    public DetalleActividadesFragment() {
        // Required empty public constructor
    }

    TextView actividadName;
    TextView actividadDescripcion;
    TextView actividadMunicipio;
    TextView actividadDireccion;
    ImageView actividadImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_actividades, container, false);
        actividadName = view.findViewById(R.id.actividadName);
        actividadImage = view.findViewById(R.id.actividadImage);
        actividadDescripcion = view.findViewById(R.id.actividadDescripcion);
        actividadMunicipio = view.findViewById(R.id.actividadMunicipio);
        actividadDireccion = view.findViewById(R.id.actividadDireccion);
        Bundle actividadObject = getArguments();
        Actividades actividades = null;
        if(actividadObject!=null){
            actividades = (Actividades) actividadObject.getSerializable("objeto");
            actividadName.setText(actividades.getActividadName());
            actividadImage.setImageResource(actividades.getActividadImageId());
            actividadDescripcion.setText(actividades.getActividadDescripcion());
            actividadMunicipio.setText(actividades.getActividadMunicipio());
            actividadDireccion.setText(actividades.getActividadDireccion());
        }
        return view;
    }
}
