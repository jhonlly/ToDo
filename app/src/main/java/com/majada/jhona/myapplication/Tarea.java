package com.majada.jhona.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;


public class Tarea extends BaseAdapter{
    private final Activity actividad;
    private final Vector lista;


    public Tarea(Activity actividad, Vector lista) {
        super();
        this.actividad = actividad;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.elementAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.tarea, null, true);
        TextView tituloTarea =(TextView)view.findViewById(R.id.tareaList);
        //TextView fechaTarea =(TextView)view.findViewById(R.id.fe);

        tituloTarea.setText((Integer) lista.elementAt(position));
        //fechaTarea.setText((Integer) lista.elementAt(position));

        return view;
    }
}
