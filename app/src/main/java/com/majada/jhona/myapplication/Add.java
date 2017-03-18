package com.majada.jhona.myapplication;

import android.app.DialogFragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Add extends AppCompatActivity {
    private EditText textFecha, textHora, textTitulo;
    private RadioGroup estado, prioridad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        textHora =(EditText) findViewById(R.id.etHora);
        textFecha = (EditText) findViewById(R.id.etFecha);

        textTitulo = (EditText) findViewById(R.id.etTarea);
        estado =(RadioGroup) findViewById(R.id.groupEstado);
        prioridad =(RadioGroup) findViewById(R.id.groupPrioridad);
        fechaHoraPorDefecto();
    }

    public void hora(){
        DialogFragment horaFrament = new Hora();
        horaFrament.show(getFragmentManager(), "TimePicker");
    }

    public void fecha(View v){
        DialogFragment fechaFrament = new Fecha();
        fechaFrament.show(getFragmentManager(), "DatePicker");
    }

    public void finalizar(View v){
        //Realizo una llamada a la actividad principal para que se vuelvan a cargar los datos ingresados.
        Intent i = new Intent(Add.this, Principal.class);
        startActivity(i);
        finish();
    }

    public void limpiarCAmpos(View v){
        textHora.setText("");
        textFecha.setText("");
        textTitulo.setText("");
    }

    public void fechaHoraPorDefecto(){
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        textHora.setText(hourFormat.format(date));
    }

    public void agregarDatos(View v){
        AdminSQLite admin = new AdminSQLite(this,
                "ToDo", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("tarea", textTitulo.getText().toString());
        registro.put("fecha", textFecha.getText().toString());
        registro.put("hora", textHora.getText().toString());

        if(estado.getCheckedRadioButtonId()== R.id.radioSinRealizar) {
            registro.put("estado", "Not Done");
        }else{
            registro.put("estado", "Done");
        }

        switch (prioridad.getCheckedRadioButtonId()){
            case R.id.radioBajo:
                registro.put("prioridad", "Low");
                break;
            case R.id.radioMedia:
                registro.put("prioridad", "Medium");
                break;
            case R.id.radioAlta:
                registro.put("prioridad", "Hight");
                break;
            default:
                break;
        }


        bd.insert("tareas", null, registro);
        Toast.makeText(this, "Se guardo la tarea", Toast.LENGTH_SHORT).show();
        bd.close();
        finish();
    }


}
