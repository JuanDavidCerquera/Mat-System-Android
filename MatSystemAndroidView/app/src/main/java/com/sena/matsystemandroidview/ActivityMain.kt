package com.sena.matsystemandroidview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ActivityMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sharedPreferences = getSharedPreferences("SesionUsuario", MODE_PRIVATE)
        val sesionIniciada = sharedPreferences.getBoolean("sesionIniciada", false)

        if(sesionIniciada == true){
            val intent = Intent(this, ActivityMenu::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            finish()
        }



    }
}