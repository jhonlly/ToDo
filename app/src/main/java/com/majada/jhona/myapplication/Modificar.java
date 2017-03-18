package com.majada.jhona.myapplication;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


public class Modificar extends AppCompatActivity {
    private EditText textFecha, textHora, textTitulo;
    private RadioGroup estado, prioridad;
    private int id;
    private Button actualizar, finalizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        finalizar = (Button)findViewById(R.id.btnCancelarM);
        textHora =(EditText) findViewById(R.id.etHoraM);
        textFecha = (EditText) findViewById(R.id.etFechaM);
        actualizar = (Button) findViewById(R.id.btnActualizarM);
        textTitulo = (EditText) findViewById(R.id.etTareaM);
        estado =(RadioGroup) findViewById(R.id.groupEstadoM);
        prioridad =(RadioGroup) findViewById(R.id.groupPrioridadM);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bun = getIntent().getExtras();
        id = bun.getInt("id");
        String estadoRecibido = bun.getString("estado");
        String prioridadRecibida = bun.getString("prioridad");
        textTitulo.setText(bun.getString("tarea"));
        //Marco el estado de la tarea dependiendo de la
        if(estadoRecibido.equals("Done")) {
            estado.check(R.id.radioDoneM);
        }else{
            estado.check(R.id.radioSinRealizarM);
        }
        switch (prioridadRecibida){
            case "Low":
                prioridad.check(R.id.radioBajoM);
                break;
            case "Medium":
                prioridad.check(R.id.radioMediaM);
                break;
            case "High":
                prioridad.check(R.id.radioAltaM);
                break;
        }
        String hora = bun.getString("hora");
        textHora.setText(hora);
                //setText(bun.getString("hora"));
        textFecha.setText(bun.getString("fecha"));
        //Al pulsar en el boton actualizar se iniciara la actulizacion en la base de datos.
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLite admin = new AdminSQLite(Modificar.this,"ToDo", null, 1);
                //Acciones sobre los elementos.
                final SQLiteDatabase bd = admin.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("tarea", textTitulo.getText().toString());
                if(estado.getCheckedRadioButtonId()== R.id.radioSinRealizarM) {
                    registro.put("estado", "Not Done");
                }else{
                    registro.put("estado", "Done");
                }

                switch (prioridad.getCheckedRadioButtonId()){
                    case R.id.radioBajoM:
                        registro.put("prioridad", "Low");
                        break;
                    case R.id.radioMediaM:
                        registro.put("prioridad", "Medium");
                        break;
                    case R.id.radioAltaM:
                        registro.put("prioridad", "High");
                        break;
                    default:
                        break;
                }

                registro.put("fecha", textFecha.getText().toString());
                registro.put("hora", textHora.getText().toString());
                bd.update("tareas", registro, "id=" + id, null);
                bd.close();
                finish();
            }

        });



    }
    public void hora(){
        DialogFragment horaFrament = new Hora();
        horaFrament.show(getFragmentManager(), "TimePicker");
    }

    public void fecha(View v){
        DialogFragment fechaFrament = new Fecha();
        fechaFrament.show(getFragmentManager(), "DatePicker");
    }
    public void finalizarM(View v){
        //Realizo una llamada a la actividad principal para que se vuelvan a cargar los datos ingresados.
        Intent i = new Intent(Modificar.this, Principal.class);
        startActivity(i);
        finish();
    }
}
