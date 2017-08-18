package com.jozamo74.seccion01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button boton;
    private EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Forzar y cargar icono en el Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        boton = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editTextName);


        boton.setOnClickListener(this);

        /*boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Hola desde codigo",Toast.LENGTH_SHORT).show();
            }
        });*/


    }

   /* public void pulsaBoton(View view){
        Toast.makeText(this,"Hola",Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onClick(View v) {


        String nombre = name.getText().toString();
        //Acceso al segundo activity y mandar string
        //intent explicito (se les especifica desde donde y hacia donde)
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("NAME",nombre);
        startActivity(intent);

    }
}
