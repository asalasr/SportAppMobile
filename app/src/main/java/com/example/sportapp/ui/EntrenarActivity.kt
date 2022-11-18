package com.example.sportapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sportapp.R
import com.example.sportapp.viewmodels.EntrenamientoViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class EntrenarActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView;

    private lateinit var viewModel: EntrenamientoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenar)
        val titleMenu: String = "ENTRENAMIENTO"
        setTitle(titleMenu);
        Log.i("EntrenarActivity","entra a onCreate")

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.entrenar);
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendario -> {
                    val obja= Intent(this, CalendarActivity::class.java)
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