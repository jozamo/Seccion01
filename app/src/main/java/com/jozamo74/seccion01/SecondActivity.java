package com.jozamo74.seccion01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button buttonNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Activar flecha ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Forzar y cargar el logo del action
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        textView = (TextView)findViewById(R.id.textViewMain);
        buttonNext = (Button)findViewById(R.id.buttonGoSharing);

        //Tomar datos del intent
        Bundle bundle = getIntent().getExtras();

        if(bundle != null && ! bundle.getString("NAME").isEmpty()){
            String name = bundle.getString("NAME");
            //Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
            textView.setText("Hello "+ name);
        }else
            Toast.makeText(this,"Name field is empty!",Toast.LENGTH_SHORT).show();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }


}
