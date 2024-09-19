package com.sena.matsystemandroidview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sena.matsystemandroidview.Services.Login

class ActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Login.init(this)

        val username:EditText = findViewById(R.id.username)
        val password:EditText = findViewById(R.id.password)
        val button:Button = findViewById(R.id.button)
        val button2:Button = findViewById(R.id.button2)
        val button3:Button = findViewById(R.id.button3)




        button.setOnClickListener() {
        //     Login.Cargar(this)

//            val intent = Intent(this, ActivityMenu::class.java)
//            startActivity(intent)


            Login.Login(username.text.toString(), password.text.toString()) { access ->
                if (access) {
                    val intent = Intent(this, ActivityMenu::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }
        button2.setOnClickListener {
            val intent: Intent = Intent(this, ActivityUpdatePassword::class.java)
            startActivity(intent)

        }
        button3.setOnClickListener {
            val intent: Intent = Intent(this, ActivityRegister::class.java)
            startActivity(intent)
        }
    }
}