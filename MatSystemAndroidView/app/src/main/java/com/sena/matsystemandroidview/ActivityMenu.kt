package com.sena.matsystemandroidview

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button1 = findViewById<Button>(R.id.button4)
        val button2 = findViewById<Button>(R.id.button5)
        val button3 = findViewById<Button>(R.id.button6)
        val button4 = findViewById<Button>(R.id.button7)
        button1.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentUser())
                .commit()
        }
        button2.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentCourseList())
                .commit()
        }
        button3.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentInfo())
                .commit()
        }
        button4.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentDocumentUsuarioList())
                .commit()
        }

    }
}