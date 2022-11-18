package com.example.sportapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sportapp.R
import com.example.sportapp.viewmodels.EventosViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class EventosActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView;

    private lateinit var viewModel: EventosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)
        val titleMenu: String = "EVENTOS"
        setTitle(titleMenu);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.servicios);
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
                    val objd = Intent(this, CalendarActivity::class.java)
                    startActivity(objd)
                    true;
                }
            }
            true
        }
    }
}