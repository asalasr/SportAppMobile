package com.example.sportapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.sportapp.R
import com.example.sportapp.viewmodels.EntrenamientoViewModel
import com.example.sportapp.viewmodels.EventoViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_evento_listar.*

class ServiciosActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)
        val titleMenu: String = "SERVICIOS"
        setTitle(titleMenu);

        val boton1=findViewById<Button>(R.id.btnEventos)

        boton1.setOnClickListener {
            val intento1 = Intent(this, EventosActivity::class.java)
            startActivity(intento1)

        }

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.servicios);
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.calendario -> {
                    val obja= Intent(this, CalendarioActivity::class.java)
                    startActivity(obja)
                    true;
                }
                R.id.nivel -> {
                    val objb= Intent(this, NivelActivity::class.java)
                    startActivity(objb)
                    true;
                }
                R.id.perfil -> {
                    val objc= Intent(this, PerfilActivity::class.java)
                    startActivity(objc)
                    true;
                }
                R.id.entrenar -> {
                    val objd = Intent(this, EntrenarActivity::class.java)
                    startActivity(objd)
                    true;
                }
            }
            true
        }
    }

}