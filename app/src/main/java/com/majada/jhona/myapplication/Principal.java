package com.majada.jhona.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Vector;

public class Principal extends ListActivity {
    private ListView listaTareas;

    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
       listaTareas =(ListView) findViewById(R.id.list);
       AdminSQLite admin = new AdminSQLite(this,"ToDo", null, 1);
       SQLiteDatabase bd = admin.getWritableDatabase();
       Cursor fila = bd.rawQuery("select tarea, estado ,prioridad, fecha, hora from tareas ", null);
       ArrayList registros = new ArrayList();
        if (fila.moveToFirst()) {
            do {
                registros.add("tarea: "+ fila.getString(0) + " prioridad: " +fila.getString(1) + " estado : "+
                        fila.getString(2));
            } while (fila.moveToNext());
        }
        setContentView(R.layout.activity_principal);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registros);
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        listaTareas.addFooterView(v);
        setListAdapter(new Tarea(this, (Vector) fila));
        listaTareas.setAdapter(adapter);
        bd.close();
    }

    //Abrir activity de añadir al realizar click en el footer
    public void add(View v){
        Intent i = new Intent(this, Add.class );
        startActivity(i);
    }
}
