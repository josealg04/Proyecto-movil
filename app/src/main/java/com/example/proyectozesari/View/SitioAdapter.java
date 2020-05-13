package com.example.proyectozesari.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectozesari.Model.Sitios;
import com.example.proyectozesari.R;

import java.util.ArrayList;

public class SitioAdapter extends RecyclerView.Adapter<SitioAdapter.ViewHolder> implements View.OnClickListener {

    ArrayList<Sitios> lista;
    LayoutInflater inflater;
    private View.OnClickListener listener;


    public SitioAdapter(Context context, ArrayList<Sitios> lista){
        this.inflater = LayoutInflater.from(context);
        this.lista = lista;
    }

    @NonNull
    @Override
    public SitioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_sitios, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull SitioAdapter.ViewHolder holder, int position) {
        String name = lista.get(position).getSitioName();
        String municipio = lista.get(position).getSitioMunicipio();
        int image = lista.get(position).getSitioImageId();
        holder.sitioName.setText(name);
        holder.sitioMunicipio.setText(municipio);
        holder.sitioImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sitioName, sitioMunicipio;
        ImageView sitioImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sitioName = itemView.findViewById(R.id.sitioName);
            sitioMunicipio = itemView.findViewById(R.id.sitioMunicipio);
            sitioImage = itemView.findViewById(R.id.sitioImage);
        }
    }
}
