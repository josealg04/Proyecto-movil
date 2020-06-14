package com.example.proyectozesari.View;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
        dates.add(CalendarDay.from(2020,6-1,21));
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

        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
                Toast.makeText(getContext(), "" + FORMATTER.format(date.getDate()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext(), "" + date, Toast.LENGTH_SHORT).show();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dateSelected = format.format(date.getDate());
                if(dateSelected.equals("2020-06-21")){
                    fechaEvent.setText("Día del padre");
                    municipio.setText("Valledupar");
                }else{
                    fechaEvent.setText("Sin eventos");
                    municipio.setText("");
                }
            }
        });
    }
}
