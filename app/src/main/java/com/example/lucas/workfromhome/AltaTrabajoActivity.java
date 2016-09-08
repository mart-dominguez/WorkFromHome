package com.example.lucas.workfromhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AltaTrabajoActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText nombreOferta;
    private Trabajo ofertaLaboral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_trabajo);
        btnAdd = (Button) findViewById(R.id.btnAddOferta);
        nombreOferta = (EditText) findViewById(R.id.nombreOferta);
        View.OnClickListener listenerBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnAddOferta:
                        ofertaLaboral = new Trabajo(99,nombreOferta.getText().toString());
                        Intent i = getIntent();
                        // Le metemos el resultado que queremos mandar a la
                        // actividad principal.
                        i.putExtra("trabajoNew",ofertaLaboral);
                        // Establecemos el resultado, y volvemos a la actividad
                        // principal. La variable que introducimos en primer lugar
                        // "RESULT_OK" es de la propia actividad, no tenemos que
                        // declararla nosotros.
                        setResult(RESULT_OK, i);
                        // Finalizamos la Activity para volver a la anterior
                        finish();
                }
            }
        };
        btnAdd.setOnClickListener(listenerBtn);

    }
}
