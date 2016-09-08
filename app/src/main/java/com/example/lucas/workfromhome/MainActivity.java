package com.example.lucas.workfromhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private ListView lista;
    private String tituloOferta;
    private Trabajo ofertaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.listaOfertas);
        OfertaAdapter ofertaAdapter = new OfertaAdapter(this, Arrays.asList(Trabajo.TRABAJOS_MOCK));
        lista.setAdapter(ofertaAdapter);

        lista.setOnItemLongClickListener(this);

        registerForContextMenu(lista);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        Log.d("MainActivity999","PASO 1: onCreateContextMenu");
        menu.setHeaderTitle("Acciones");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.listaOfertas) {
            ((ListView)v).getItemAtPosition(0);
            //usar getItemAtPosition() para obtener el elemento
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.postularse:
                Toast.makeText(this, "Te has postulado a esta oferta: << " +ofertaSeleccionada.getDescripcion()+">>.  Nos comunicaremos a la brevedad...",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.compartir:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Ey! No te pierdas esta oferta! "+ofertaSeleccionada.getDescripcion());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.createMyJob) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("MainActivity999","PASO 2: onItemLongClick");
        ofertaSeleccionada = (Trabajo) adapterView.getItemAtPosition(i);

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //http://www.proyectosimio.com/es/programacion-android-startactivityforresult-lanzar-una-actividad-para-recibir-un-resultado/
        //http://www.limecreativelabs.com/startactivityforresult-actividades-que-devuelven-resultados/
        ofertaSeleccionada = data.getParcelableExtra("trabajoNew");
    }
}
