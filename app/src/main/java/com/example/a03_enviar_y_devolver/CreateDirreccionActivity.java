package com.example.a03_enviar_y_devolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a03_enviar_y_devolver.R;
import com.example.a03_enviar_y_devolver.modelos.Direccion;

public class CreateDirreccionActivity extends AppCompatActivity {

    private EditText txtCalle;
    private EditText txtNumero;
    private EditText txtCiudad;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dirreccion);

        inicializarVistas();
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Direccion dir = new Direccion(
                        txtCalle.getText().toString(),
                        Integer.parseInt(txtNumero.getText().toString()),
                        txtCiudad.getText().toString()
                );
                // COSAS...
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DIR", dir) ;
                intent.putExtras(bundle);
                //devolver
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void inicializarVistas() {
        txtCalle = findViewById(R.id.txtCalleCreateDir);
        txtCiudad = findViewById(R.id.txtCiudadCreateDir);
        txtNumero = findViewById(R.id.txtNumeroCreateDir);
        btnCrear = findViewById(R.id.btnCrearCreateDir);
    }
}