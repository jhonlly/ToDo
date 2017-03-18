package com.majada.jhona.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class Principal extends AppCompatActivity {
    private ListView listaTareas;
    ArrayList<Tarea> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
       listaTareas =(ListView) findViewById(R.id.lvTareas);
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        listaTareas.addFooterView(v);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdminSQLite admin = new AdminSQLite(this,"ToDo", null, 1);
        //Acciones sobre los elementos.
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select tarea, estado ,prioridad, fecha, hora, id from tareas ", null);
        registros = new ArrayList<Tarea>();
        if (fila.moveToFirst()) {
            do {
                registros.add( new Tarea( fila.getString(0), fila.getString(1),fila.getString(2), fila.getString(3),fila.getString(4), fila.getInt(5)));

            } while (fila.moveToNext());
        }
        //View v = getLayoutInflater().inflate(R.layout.footer, null);
        //listaTareas.addFooterView(v);
        AdaptadorTareas adaptador = new AdaptadorTareas(this);
        ListView lv1 = (ListView)findViewById(R.id.lvTareas);
        lv1.setAdapter(adaptador);
        //Eliminar elementos.
        listaTareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               //Confirmación a la hora de eliminar las tareas.
                AlertDialog.Builder notificacionEliminar = new AlertDialog.Builder(Principal.this);
                notificacionEliminar.setTitle("Importante");
                notificacionEliminar.setMessage("¿ Elimina esta tarea?");
                notificacionEliminar.setCancelable(false);
                //Al precionar el botón confirmas para eliminar el elemento
                notificacionEliminar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        AdminSQLite admin = new AdminSQLite(Principal.this,"ToDo", null, 1);
                        //Acciones sobre los elementos.
                        final SQLiteDatabase bd = admin.getWritableDatabase();
                        bd.delete("tareas", "id=" + registros.get(position).getId(), null);
                        Intent i = new Intent(Principal.this, Principal.class);
                        startActivity(i);
                        bd.close();
                        finish();
                    }
                });
                //Negativo
                notificacionEliminar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                notificacionEliminar.show();

                return false;
            }
        });
        //Llamada a la vista de modificar.
        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 // registros.get(position).getId();
                Intent in = new Intent(getApplicationContext(), Modificar.class);
               //Paso los los valores a la actividad de modificar.
                in.putExtra("id",registros.get(position).getId() );
                in.putExtra("tarea", registros.get(position).getTitulo() );
                in.putExtra("fecha", registros.get(position).getFecha() );
                in.putExtra("hora", registros.get(position).getHora());
                in.putExtra("estado", registros.get(position).getEstado());
                in.putExtra("prioridad", registros.get(position).getPrioridad());
                startActivity(in);
            }
        });

        bd.close();
    }

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
            fechaTarea.setText("Date: "+ registros.get(position).getFecha() +" "+registros.get(position).getHora());
            //Marco la priodidad de la tarea.
            TextView prioridadTarea = (TextView)item.findViewById(R.id.prioridadTarea);
            prioridadTarea.setText("Priority: "+ registros.get(position).getPrioridad());
            //Marco si está realizada o no dependiendo de la consulta.
            CheckBox checkTarea = (CheckBox) item.findViewById(R.id.checkTarea);
             if(registros.get(position).getEstado().equals("Done")){
                 checkTarea.setChecked(true);
             }else{
                checkTarea.setChecked(false);
             }
            return(item);
        }

    }
}
