package com.example.proyectozesari.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectozesari.Model.Sitios;
import com.example.proyectozesari.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleSitiosFragment extends Fragment {

    TextView sitioName;
    TextView sitioDescripcion;
    TextView sitioMunicipio;
    TextView sitioDireccion;
    ImageView sitioImage;

    public DetalleSitiosFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_sitios, container, false);
        sitioName = view.findViewById(R.id.sitioName);
        sitioImage = view.findViewById(R.id.sitioImage);
        sitioDescripcion = view.findViewById(R.id.sitioDescripcion);
        sitioMunicipio = view.findViewById(R.id.sitioMunicipio);
        sitioDireccion = view.findViewById(R.id.sitioDireccion);
        Bundle sitioObject = getArguments();
        Sitios sitios = null;
        if(sitioObject!=null){
            sitios = (Sitios) sitioObject.getSerializable("objeto");
            sitioName.setText(sitios.getSitioName());
            sitioImage.setImageResource(sitios.getSitioImageId());
            sitioDescripcion.setText(sitios.getSitioDescripcion());
            sitioMunicipio.setText(sitios.getSitioMunicipio());
            sitioDireccion.setText(sitios.getSitioDireccion());
        }
        return view;
    }
}
