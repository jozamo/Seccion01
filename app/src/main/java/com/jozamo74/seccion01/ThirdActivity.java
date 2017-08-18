package com.jozamo74.seccion01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;
import static android.transition.Fade.IN;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonCamera;
    private ImageButton imageButtonContancts;
    private ImageButton imageButtonEmail;


    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //Activar flecha ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Activar icono en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imageButtonPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imageButtonWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imageButtonCamera = (ImageButton) findViewById(R.id.imageButtonCamera);
        imageButtonContancts = (ImageButton) findViewById(R.id.imageButtonContact);
        imageButtonEmail = (ImageButton) findViewById(R.id.imageButtonEmail);


        //Botón para la llamada desde la aplicación llamada de google
        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(intentPhone);
            }
        });

        /*imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                if ( phoneNumber != null && !phoneNumber.isEmpty()) {
                    //comprobar versión android que se esta ejecutando
                    if (Build.VERSION.SDK_INT >= M){
                        //Comprobar si ha aceptado, no ha aceptado o nunca se le ha preguntado
                        if(CheckPermission(Manifest.permission.CALL_PHONE)){
                            //ha aceptado
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                            if(ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                                startActivity(intent);
                        }else{
                            //Ha denegado y es la primera vez que se le pregunta
                            if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                //no se le ha preguntado aún
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            } else{
                                //Ha denegado
                                Toast.makeText(ThirdActivity.this,"Please, enable the requet permission", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(intent);

                            }
                        }

                    }else
                        OlderVersion(phoneNumber);

                }else
                    Toast.makeText(ThirdActivity.this,"Insert a number phone",Toast.LENGTH_SHORT).show();
            }

            private void OlderVersion(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                if ((CheckPermission(Manifest.permission.CALL_PHONE)))
                    startActivity(intentCall);
                else
                    Toast.makeText(ThirdActivity.this, "You decline the access", Toast.LENGTH_SHORT).show();
            }

        });*/

        //Botón para la dirección web
        imageButtonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextWeb.getText().toString();
                if (url != null && !url.isEmpty()){
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url));
                    startActivity(intentWeb);
                    /*
                    --Esto es lo mismo que Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url));
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://"+url));
                     */
                }
            }
        });

        //Botón para ir a la agenda de contactos
        imageButtonContancts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                startActivity(intentContacts);
            }
        });

        //Botón para ir al email
        imageButtonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO = {"jozamo001@gmail.com"};
                String[] CC = {""};
               // Intent intentEmail = new Intent (Intent.ACTION_SEND, Uri.parse("mailto"));
                Intent intentEmail = new Intent(Intent.ACTION_VIEW, Uri.parse("jozamo001@gmail.com"));
                intentEmail.setType("text/plain");
                intentEmail.putExtra(Intent.EXTRA_EMAIL, TO);
                intentEmail.putExtra(Intent.EXTRA_CC, CC);
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
                intentEmail.putExtra(Intent.EXTRA_TEXT, "");

                try{
                    startActivity(intentEmail);
                   // startActivity(Intent.createChooser(intentEmail, "Elige cliente e correo...")); //(fuerza a preguntar )para elegir cliente de correo
                    // intentEmail.setType("message/rfc822"); (hay q cambiar el tipo de email

                    finish();
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(ThirdActivity.this,"No tienes clietnes de correo instalados", Toast.LENGTH_LONG).show();
                }


            }
        });

        //Botón para abrir la camara
        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intentCamera);
            }
        });

    } // end onCreate

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Opción llamada telefono
        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //Comprobar si el permiso ha sido aceptada o denegada
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //permiso concedido
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                    }else {
                        //permiso denegado
                        Toast.makeText(this,"You declined the access", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    //Metodo para comprobar permisos en el manifest (Realmente comprueba que el usuario acepte el permiso)
    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
