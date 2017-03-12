package com.majada.jhona.myapplication;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Add extends AppCompatActivity {
    private EditText textFecha;
    private EditText textHora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        textHora =(EditText) findViewById(R.id.etHora);
        textFecha = (EditText) findViewById(R.id.etFecha);
        // perform click event on edit text
        textHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment horaFrament = new Hora();
                horaFrament.show(getFragmentManager(), "TimePicker");
            }
        });
        textFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fechaFrament = new Fecha();
                fechaFrament.show(getFragmentManager(), "DatePicker");
            }
        });
    }


}
