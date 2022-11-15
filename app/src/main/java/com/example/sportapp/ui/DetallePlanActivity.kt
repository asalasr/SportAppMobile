package com.example.sportapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sportapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetallePlanActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel)
        val titleMenu: String = "AVANCE DEPORTIVO"
        setTitle(titleMenu);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.nivel);
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nivel -> {
                    val obja = Intent(this, NivelActivity::class.java)
                    startActivity(obja)
                    true;
                }
                R.id.perfil -> {
                    val objb = Intent(this, PerfilActivity::class.java)
                    startActivity(objb)
                    true;
                }
                R.id.entrenar -> {
                    val objc = Intent(this, EntrenarActivity::class.java)
                    startActivity(objc)
                    true;
                }

                R.id.calendario -> {
                    val objd = Intent(this, CalendarioActivity::class.java)
                    startActivity(objd)
                    true;
                }
            }
            true
        }
    }
}