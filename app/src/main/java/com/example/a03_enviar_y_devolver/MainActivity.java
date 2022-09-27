package com.example.a03_enviar_y_devolver;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a03_enviar_y_devolver.modelos.Direccion;
import com.example.a03_enviar_y_devolver.modelos.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText txtPassword;
    private EditText txtEmail;
    private Button btndescifrar;
    private Button btncrearDireccion;

    //CONSTANTES DE IDENTIFICACION DE ACTIVIDADES
    private final  int DIRRECCIONES = 3;
    private final  int CAMIONES = 4;

    //ActivityResultLaunchers
    private ActivityResultLauncher<Intent> launcherDirecciones;
    private ActivityResultLauncher<Intent> launcherCamiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializar vistas
        inicializarVistas();
        //inicializar launchers
        inicializarLaunchers();

        //ABRIR VENTANA SIN MAS(startActivity)
        btndescifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = txtPassword.getText().toString();
                String email = txtEmail.getText().toString();
                Intent intent = new Intent(MainActivity.this, DescifrarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("USER", new Usuario(email, password));
                //pasar el bundle
                intent.putExtras(bundle);
                //iniciar actividad
                startActivity(intent);
            }
        });

        //ABRIR VENTANA CREAR DIRRECCION PARA QUE DEVUELVA (startActivityForResult)
        btncrearDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CreateDirreccionActivity.class);
                //startActivityForResult(intent,DIRRECCIONES);
                launcherDirecciones.launch(intent);
            }
        });
    }

    private void inicializarLaunchers() {
        //registerForActivityResult
        //1-Modo en el que se lanza el intent
        //2-Acciones a realizar DESPUES de que se cierre el intent
        launcherDirecciones = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData()!=null){
                                Bundle bundle = result.getData().getExtras();
                                //sacar del bundle la info segun el tag
                                Direccion dir = (Direccion) bundle.getSerializable("DIR");
                                Toast.makeText(MainActivity.this, dir.toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "ACCION CANCELADA", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void inicializarVistas() {

        txtEmail = findViewById(R.id.txtMailMain);
        txtPassword = findViewById(R.id.txtPasswordMain);
        btndescifrar = findViewById(R.id.btnDescifrarMain);
        btncrearDireccion = findViewById(R.id.btnCrearDireccionMain);

    }

    /**
     *              (DEPRECATED)
     * Metodo que se dispara cuando lanzo una actividad con startActivityForResult
     * @param requestCode Identificador de la actividad que esta retornando datos
     * @param resultCode Estado en el que termina la actividad de los datos
     * @param data el intent que en caso de existir contine los datos
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //EL REQUEST CODE ES PARA SABER CUAL ESTA DEVOLVIENDO
        //SIGNIFICA QUE LA VENTANA DE LA QUE VIENE ES DIRRECCCIONES
        if (requestCode == DIRRECCIONES){
            //SIGINIFICA QUE LE HA DADO AL BOTON DETODO CORRECTO
            if (resultCode == RESULT_OK){
                if (data != null) {

                    Bundle bundle = data.getExtras();
                    //sacar del bundle la info segun el tag
                    Direccion dir = (Direccion) bundle.getSerializable("DIR");
                    //LOGICA PARA TRABAJAR CON LA DIRECCION
                    Toast.makeText(this, dir.toString(), Toast.LENGTH_SHORT).show();


                }
            }
            else {
                Toast.makeText(this, "accion cancelada", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == CAMIONES){

        }
    }
}