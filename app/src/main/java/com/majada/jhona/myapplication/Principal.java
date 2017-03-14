package com.majada.jhona.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class Principal extends AppCompatActivity {
    private ListView listaTareas;
    ArrayList<Tarea> registros;

    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
       listaTareas =(ListView) findViewById(R.id.lvTareas);
       AdminSQLite admin = new AdminSQLite(this,"ToDo", null, 1);
       SQLiteDatabase bd = admin.getWritableDatabase();
       Cursor fila = bd.rawQuery("select tarea, estado ,prioridad, fecha, hora from tareas ", null);
        registros = new ArrayList<Tarea>();
        if (fila.moveToFirst()) {
            do {
                 registros.add( new Tarea( fila.getString(0), fila.getString(1),fila.getString(2), fila.getString(3),fila.getString(4)));

            } while (fila.moveToNext());
        }
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        listaTareas.addFooterView(v);
        AdaptadorTareas adaptador = new AdaptadorTareas(this);
        ListView lv1 = (ListView)findViewById(R.id.lvTareas);
        lv1.setAdapter(adaptador);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registros);


        //setListAdapter(new Tarea(this, (Vector) fila));
        //listaTareas.setAdapter(adapter);
        bd.close();
    }
    /*registros.add( new Tarea(  )fila.getString(0)) + " prioridad: " +fila.getString(1) + " estado : "+
                            fila.getString(2));*/
    //Abrir activity de añadir al realizar click en el footer
    public void add(View v){
        Intent i = new Intent(this, Add.class );
        startActivity(i);
    }

    class AdaptadorTareas  extends ArrayAdapter<Tarea>{
        AppCompatActivity appCompatActivity;

        AdaptadorTareas(AppCompatActivity context) {
            super(context, R.layout.tarea, registros);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.tarea, null);
            TextView tituloTarea = (TextView)item.findViewById(R.id.tareaList);
            tituloTarea.setText(registros.get(position).getTitulo());
            TextView fechaTarea = (TextView)item.findViewById(R.id.fechaTarea);
            fechaTarea.setText("Fecha: "+ registros.get(position).getFecha() +" "+registros.get(position).getHora());
            //Marco la priodidad de la tarea.
            TextView prioridadTarea = (TextView)item.findViewById(R.id.prioridadTarea);
            prioridadTarea.setText("Prioridad: "+ registros.get(position).getPrioridad());
            //Marco si está realizada o no dependiendo de la consulta.
            CheckBox checkTarea = (CheckBox) item.findViewById(R.id.checkTarea);
             if(registros.get(position).getEstado().equals("Done")){
                 checkTarea.setChecked(true);
             }else{
                checkTarea.setChecked(false);
             }
             //Muestro la fecha
            fechaTarea.setText("Fecha: "+ registros.get(position).getFecha() +" "+registros.get(position).getHora());
            return(item);
        }

    }
}
