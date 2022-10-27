package com.example.SportApp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.SportApp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class PerfilActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.perfil);
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendario -> {
                    val obja = Intent(this, CalendarioActivity::class.java)
                    startActivity(obja)
                    true;
                }
                R.id.nivel -> {
                    val objb = Intent(this, NivelActivity::class.java)
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