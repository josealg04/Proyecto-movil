package com.example.proyectozesari.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectozesari.Model.Historias;
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
    ArrayList<Historias> listaHistorias;
    com.example.proyectozesari.Presenter.iFragmentsCommunicate iFragmentsCommunicate;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historias, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        listaHistorias = new ArrayList<>();
        uploadList();
        displayInfo();
        return view;
    }

    public void uploadList(){
        listaHistorias.add(new Historias(
                "Leyenda de la Sirena de Hurtado",
                "Valledupar",
                "Cuentan una vez que en Semana Santa una niña muy linda pidió permiso a su mamá para irse a bañar a las profundas y frías aguas del Río Guatapuri, pozo de Hurtado; la madre de la niña, por ser Jueves Santo, le negó el permiso, pero la niña desobediente se marchó a escondidas, llegó a las rocas de la orilla, se quitó sus ropas y se lanzó al agua desde la altura; inmediatamente quedó convertida en Sirena. Su madre la llamó por toda la orilla del rió creyéndola ahogada, pero ella en la mañana, al salir el sol dijo adiós con la cola antes de sonreír por última vez, entonces, todos comprendieron la realidad.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Cuentan los abuelos que antes la sirena salía a las rocas los jueves santo y emitía su hermosos canto que se escuchaba por todo el valle, al tiempo que brindaba a su madre las lagrimas de la desobediencia.",
                R.drawable.sirena));
        listaHistorias.add(new Historias(
                "Leyenda de Francisco El Hombre",
                "Valledupar",
                "Narra la leyenda que una noche después de una parranda de varios días y al ir en marcha hacia su pueblo, para distraerse en la soledad de la noche, abrió el acordeón y, sobre su burro, como era usual en aquella época, empezó a interpretar sus melodías; de pronto al terminar una pieza surgió de inmediato el repertorio de otro acordeonero que desafiante trataba de superarlo; de inmediato Francisco marchó hacia él hasta tenerlo a la vista; su competidor para sorpresa, era Satanás, quien al instante se sentó sobre la raíces de un gran árbol, abrió su acordeón, y con las notas que le brotaban hizo apagar la luna y toda las estrellas.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "El mundo se sumergió en una oscuridad tal, que sólo los ojos de Satanás resplandecían como tizones. Sus notas eran las de un gran maestro; algunos dicen que de ahí nació, de la inspiración del demonio, el canto del amor amor. Francisco, dueño de su virtudes y poseído de gran fe, lejos de acobardarse con la abrazadora oscuridad, abrió su acordeón y extrajo tan hermosa melodía que su magia devolvió la luz a la luna y a las estrellas, infligiendo temor al demonio. Después clamo a Dios y entonó el credo con su voz de cantador taumaturgo, el demonio exaltó un terrible alarido y con su acordeón a rastras irrumpió un gran bullicio hacia las montañas donde se perdió para siempre.",
                R.drawable.franciscoelhombre));
    }

    public void displayInfo(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historiaAdapter = new HistoriaAdapter(getContext(), listaHistorias);
        recyclerView.setAdapter(historiaAdapter);
        historiaAdapter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String name = listaHistorias.get(recyclerView.getChildAdapterPosition(view)).getHistoriaName();
                Toast.makeText(getContext(), "Option: "+name, Toast.LENGTH_SHORT).show();
                iFragmentsCommunicate.detalleHistoria(listaHistorias.get(recyclerView.getChildAdapterPosition(view)));
            }
        });
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
