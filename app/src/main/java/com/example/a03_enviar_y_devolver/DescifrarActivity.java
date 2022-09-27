package com.example.a03_enviar_y_devolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.a03_enviar_y_devolver.modelos.Usuario;

public class DescifrarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descifrar);

        //RECUOERAR EL INTENT
        Intent intent = getIntent();
        //RECUPERAR BUNDLE
        Bundle bundle = intent.getExtras();

        //SI NO ES NULL HAY UN STRING
        if (bundle != null){
            /*String password = bundle.getString("PASS");
            String email = bundle.getString("EMAIL");
            Usuario user = new Usuario(email, password);*/

            Usuario user = (Usuario) bundle.getSerializable("USER");

            Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show();
        }
    }
}