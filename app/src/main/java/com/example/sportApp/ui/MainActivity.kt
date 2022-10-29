package com.example.sportApp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.sportApp.R
import com.example.sportApp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replaceFragment(EntrenarFragment())
        Log.i("titulo", "ACA LLEGO")
        binding.bottomNavigationView.setOnItemSelectedListener {
            Log.i("titulo", "ACA ENTRAR "+it.itemId)

            when(it.itemId){
                R.id.entrenar -> replaceFragment(EntrenarFragment())
                R.id.perfil -> replaceFragment(PerfilFragment())
                R.id.calendario -> replaceFragment(CalendarioFragment())
                R.id.nivel -> replaceFragment(NivelFragment())
                R.id.servicios -> replaceFragment(ServiciosFragment())
                else ->{
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}