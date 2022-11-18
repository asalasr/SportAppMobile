package com.example.sportapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NivelActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel)

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.nivel);
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendario -> {
                    val obja = Intent(this, CalendarActivity::class.java)
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
                R.id.servicios -> {
                    val objd = Intent(this, ServiciosActivity::class.java)
                    startActivity(objd)
                    true;
                }
            }
            true
        }
    }
}