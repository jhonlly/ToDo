package com.majada.jhona.myapplication;

import android.app.DialogFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
       // textFecha.setText( );
    }

    public void agreegarDatos(){
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("Tarea: ", textTitulo.getText().toString());
        if(estado.getCheckedRadioButtonId()== R.id.radioSinRealizar) {
            editor.putBoolean("Estado", true);
        }else{
            editor.putBoolean("Estado", false);
        }
    }

    public void botonChequeado(View v){
        if(estado.getCheckedRadioButtonId()== R.id.radioSinRealizar){

        }
    }
}
