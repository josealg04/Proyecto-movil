package com.example.proyectozesari.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.example.proyectozesari.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(FragmentActivity context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.image_home,
            R.drawable.image_sitios,
            R.drawable.image_historias
    };

    public String[] slide_headings = {
            "BIENVENIDOS",
            "SITIOS",
            "HISTORIAS"
    };

    public String[] slide_descs = {
            "Zezari Tour, la app que necesitas para conocer el Cesar. Encontrarás la sección de sitios para conocer los lugares más recomendados de cada municipio del Cesar, además encontrarás las historias, leyendas y música de cada municipio.",
            "En esta sección encontrarás los lugares más conocidos y representativos de cada municipio del departamento con su respectiva infomación.",
            "En esta sección encontrarás podrás seleccionar cualquier municipio del departamento para ver las historias, leyendas, juglares y música de cada uno de ellos."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
