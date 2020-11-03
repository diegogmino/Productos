package com.liceolapaz.dam.dgm;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LoginControl login;
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (LoginControl) findViewById(R.id.CtlLogin);

        login.setOnLoginListener(new OnLoginListener()
        {
            @Override
            public void onLogin(String usuario, String password) {


                //Validamos el usuario y la contraseña
                if (usuario.equals("admin") && password.equals("liceo")) {
                    login.setMensaje("Login correcto");
                    Intent intent = new Intent (MainActivity.this, JugadoresActivity.class);
                    startActivity(intent);

                } else {
                    login.setMensaje("Vuelva a intentarlo.");
                    contador++;
                    if (contador == 3) {
                        finish();
                    }
                }
            }
        });


    }


}