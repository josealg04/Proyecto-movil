package com.example.proyectozesari.View;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectozesari.Model.EventDecorator;
import com.example.proyectozesari.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarioFragment extends Fragment {

    public CalendarioFragment() {
        // Required empty public constructor
    }

    MaterialCalendarView mcv;
    TextView fechaEvent;
    TextView municipio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        mcv = view.findViewById(R.id.calendarView);
        fechaEvent = view.findViewById(R.id.titleEvent);
        municipio = view.findViewById(R.id.municipioEvent);
        fechaEvent.setText("Sin eventos");
        municipio.setText("");
        manejoCalendario();
        fechaEventos();
        return view;
    }

    public void fechaEventos(){
        HashSet<CalendarDay> dates = new HashSet<>();
        //Paletilla
        dates.add(CalendarDay.from(2020,1-1,30));
        dates.add(CalendarDay.from(2020,1-1,31));
        dates.add(CalendarDay.from(2020,2-1,1));
        dates.add(CalendarDay.from(2020,2-1,2));

        //San José
        dates.add(CalendarDay.from(2020,3-1,18));

        //Semana Santa
        dates.add(CalendarDay.from(2020,4-1,5));
        dates.add(CalendarDay.from(2020,4-1,6));
        dates.add(CalendarDay.from(2020,4-1,7));
        dates.add(CalendarDay.from(2020,4-1,8));
        dates.add(CalendarDay.from(2020,4-1,9));
        dates.add(CalendarDay.from(2020,4-1,10));
        dates.add(CalendarDay.from(2020,4-1,11));

        //Festival Vallenato
        dates.add(CalendarDay.from(2020,4-1,25));
        dates.add(CalendarDay.from(2020,4-1,26));
        dates.add(CalendarDay.from(2020,4-1,27));
        dates.add(CalendarDay.from(2020,4-1,28));
        dates.add(CalendarDay.from(2020,4-1,29));
        dates.add(CalendarDay.from(2020,4-1,30));
        dates.add(CalendarDay.from(2020,5-1,1));
        dates.add(CalendarDay.from(2020,5-1,2));

        //Virgen del carmen
        dates.add(CalendarDay.from(2020,7-1,16));

        //Feria ganadera
        dates.add(CalendarDay.from(2020,8-1,14));
        dates.add(CalendarDay.from(2020,8-1,15));
        dates.add(CalendarDay.from(2020,8-1,16));
        dates.add(CalendarDay.from(2020,8-1,17));

        //Halloween
        dates.add(CalendarDay.from(2020,10-1,31));

        //Noche buena y navidad
        dates.add(CalendarDay.from(2020,12-1,24));
        dates.add(CalendarDay.from(2020,12-1,25));

        //Año viejo
        dates.add(CalendarDay.from(2020,12-1,31));
        mcv.addDecorators(new EventDecorator(000000,dates));
    }

    private void manejoCalendario() {
        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2020, 1-1, 1))
                .setMaximumDate(CalendarDay.from(2020, 12-1, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        mcv.setSelectedDate(CalendarDay.today());

        mcv.setWeekDayLabels(new String[] {"DOM", "LUN", "MAR", "MIÉ", "JUE", "VIE", "SÁB"});
        mcv.setTitleMonths(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});

        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
                Toast.makeText(getContext(), "" + FORMATTER.format(date.getDate()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext(), "" + date, Toast.LENGTH_SHORT).show();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dateSelected = format.format(date.getDate());
                if(dateSelected.equals("2020-01-30") || dateSelected.equals("2020-01-31") || dateSelected.equals("2020-02-01") || dateSelected.equals("2020-02-02")) {
                    fechaEvent.setText("Festival de la paletilla");
                    municipio.setText("Becerril");
                }else if(dateSelected.equals("2020-03-18")) {
                    fechaEvent.setText("Fiesta Patronal de San José");
                    municipio.setText("La Gloria");
                }else if(dateSelected.equals("2020-04-05") || dateSelected.equals("2020-04-06") || dateSelected.equals("2020-04-07") || dateSelected.equals("2020-04-08") ||dateSelected.equals("2020-04-09") || dateSelected.equals("2020-04-10") || dateSelected.equals("2020-04-11")) {
                    fechaEvent.setText("Semana Santa");
                    municipio.setText("Departamento del Cesar");
                }else if(dateSelected.equals("2020-04-25") || dateSelected.equals("2020-04-26") || dateSelected.equals("2020-04-27") || dateSelected.equals("2020-04-28") ||dateSelected.equals("2020-04-29") || dateSelected.equals("2020-04-30") || dateSelected.equals("2020-05-01") || dateSelected.equals("2020-05-02")) {
                    fechaEvent.setText("Festival de la Leyenda Vallenata");
                    municipio.setText("Valledupar");
                }else if(dateSelected.equals("2020-07-16")) {
                    fechaEvent.setText("Fiesta Patronal de la Virgen del Carmen");
                    municipio.setText("Departamento del Cesar");
                }else if(dateSelected.equals("2020-08-14")) {
                    fechaEvent.setText("Feria Ganadera");
                    municipio.setText("Valledupar");
                }else if(dateSelected.equals("2020-08-15") || dateSelected.equals("2020-08-16") || dateSelected.equals("2020-08-17")) {
                    fechaEvent.setText("Feria Ganadera - Festival de La Quinta");
                    municipio.setText("Valledupar");
                }else if(dateSelected.equals("2020-10-31")) {
                    fechaEvent.setText("Halloween");
                    municipio.setText("Cesar");
                }else if(dateSelected.equals("2020-12-24")) {
                    fechaEvent.setText("Noche buena");
                    municipio.setText("Cesar");
                }else if(dateSelected.equals("2020-12-25")) {
                    fechaEvent.setText("Navidad");
                    municipio.setText("Cesar");
                }else if(dateSelected.equals("2020-12-31")){
                    fechaEvent.setText("Año viejo");
                    municipio.setText("Cesar");
                }else{
                    fechaEvent.setText("Sin eventos");
                    municipio.setText("");
                }
            }
        });
    }
}
