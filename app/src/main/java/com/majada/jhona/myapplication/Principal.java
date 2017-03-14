package com.majada.jhona.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class Principal extends AppCompatActivity {
    private ListView listaTareas;
    ArrayList<Tarea> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
       listaTareas =(ListView) findViewById(R.id.lvTareas);
       AdminSQLite admin = new AdminSQLite(this,"ToDo", null, 1);
        //Acciones sobre los elementos.
        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Principal.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este teléfono ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        TextView date =(TextView) findViewById(R.id.fechaTarea);

                        date.setText("lalalalal");
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                ///return false;
            }
        });
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
        bd.close();
    }
    //Abrir activity de añadir al realizar click en el footer
    public void add(View v){
        Intent i = new Intent(this, Add.class );
        startActivity(i);
    }


    public void notificacion(View v){
        TextView fecha  = (TextView) findViewById(R.id.fechaTarea);
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!"+ fecha.getText().toString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
