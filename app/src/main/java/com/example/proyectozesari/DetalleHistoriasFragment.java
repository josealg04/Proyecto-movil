package com.example.proyectozesari;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleHistoriasFragment extends Fragment {

    TextView historiaName;
    TextView historiaDescripcion;
    TextView historiaMunicipio;
    ImageView historiaImage;

    public DetalleHistoriasFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detalle_historias, container, false);
        historiaName = view.findViewById(R.id.historiaName);
        historiaImage = view.findViewById(R.id.historiaImage);
        historiaDescripcion = view.findViewById(R.id.historiaDescripcion);
        historiaMunicipio = view.findViewById(R.id.historiaMunicipio);
        Bundle historiaObject = getArguments();
        Historias historias = null;
        if(historiaObject!=null){
            historias = (Historias) historiaObject.getSerializable("objeto");
            historiaName.setText(historias.getHistoriaName());
            historiaImage.setImageResource(historias.getHistoriaImageId());
            historiaDescripcion.setText(historias.getHistoriaDescripcion());
            historiaMunicipio.setText(historias.getHistoriaMunicipio());
        }
        return view;
    }
}
