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
import java.util.Calendar;
import java.util.Date;


public class Add extends AppCompatActivity {
    Date mDate;
    private static final int SEVEN_DAYS = 604800000;
    private static String dateString, timeString;
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

    public void hora(View v){
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

    //Restaurar campos por defecto.
    public void limpiarCAmpos(View v){
        estado.check(R.id.radioSinRealizar);
        prioridad.check(R.id.radioMedia);
        textTitulo.setText("");
    }
//Metodo que configura la hora por defecto en los campos editext.
    public void fechaHoraPorDefecto(){
        mDate = new Date();
        mDate = new Date(mDate.getTime() + SEVEN_DAYS);
        Calendar c = Calendar.getInstance();
        c.setTime(mDate);
        setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        textFecha.setText(dateString);
        setTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                c.get(Calendar.MILLISECOND));
        textHora.setText(timeString);

    }
    private static void setTimeString(int hourOfDay, int minute, int mili) {
        String hour = "" + hourOfDay;
        String min = "" + minute;
        if (hourOfDay < 10)
            hour = "0" + hourOfDay;
        if (minute < 10)
            min = "0" + minute;
        timeString = hour + ":" + min + ":00";
    }

    private static void setDateString(int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        String mon = "" + monthOfYear;
        String day = "" + dayOfMonth;
        if (monthOfYear < 10)
            mon = "0" + monthOfYear;
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;
        dateString = year + "/" + mon + "/" + day;
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
