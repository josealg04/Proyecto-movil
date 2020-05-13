package com.example.proyectozesari.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectozesari.Model.Historias;
import com.example.proyectozesari.R;

import java.util.ArrayList;

public class HistoriaAdapter extends RecyclerView.Adapter<HistoriaAdapter.ViewHolder> implements View.OnClickListener {

    ArrayList<Historias> lista;
    LayoutInflater inflater;
    private View.OnClickListener listener;

    public HistoriaAdapter(Context context, ArrayList<Historias> lista){
        this.inflater = LayoutInflater.from(context);
        this.lista = lista;
    }

    @NonNull
    @Override
    public HistoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_historias, parent, false);
        view.setOnClickListener(this);
        return new HistoriaAdapter.ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaAdapter.ViewHolder holder, int position) {
        String name = lista.get(position).getHistoriaName();
        String municipio = lista.get(position).getHistoriaMunicipio();
        int image = lista.get(position).getHistoriaImageId();
        holder.historiaName.setText(name);
        holder.historiaMunicipio.setText(municipio);
        holder.historiaImage.setImageResource(image);
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

        TextView historiaName, historiaMunicipio;
        ImageView historiaImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historiaName = itemView.findViewById(R.id.historiaName);
            historiaMunicipio = itemView.findViewById(R.id.historiaMunicipio);
            historiaImage = itemView.findViewById(R.id.historiaImage);
        }
    }

}
