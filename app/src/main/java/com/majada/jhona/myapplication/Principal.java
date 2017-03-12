package com.majada.jhona.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    private ListView listaTareas;

    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        add = (Button) findViewById(R.id.btnAdd) ;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    public void add(){
        Intent i = new Intent(this, Add.class );
        startActivity(i);
    }
}
