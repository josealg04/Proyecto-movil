package com.example.proyectozesari.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectozesari.Model.Actividades;
import com.example.proyectozesari.Model.Sitios;
import com.example.proyectozesari.R;

import java.util.ArrayList;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ViewHolder> implements View.OnClickListener  {

    ArrayList<Actividades> lista;
    LayoutInflater inflater;
    private View.OnClickListener listener;

    public ActividadAdapter(Context context, ArrayList<Actividades> lista){
        this.inflater = LayoutInflater.from(context);
        this.lista = lista;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActividadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_actividades, parent, false);
        view.setOnClickListener(this);
        return new ActividadAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadAdapter.ViewHolder holder, int position) {
        String name = lista.get(position).getActividadName();
        String contacto = lista.get(position).getActividadContacto();
        int image = lista.get(position).getActividadImageId();
        holder.actividadName.setText(name);
        holder.actividadContacto.setText(contacto);
        holder.actividadImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView actividadName, actividadContacto;
        ImageView actividadImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            actividadName = itemView.findViewById(R.id.actividadName);
            actividadContacto = itemView.findViewById(R.id.actividadContacto);
            actividadImage = itemView.findViewById(R.id.actividadImage);
        }
    }
}
