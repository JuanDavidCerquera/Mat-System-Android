package com.sena.matsystemandroidview

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ActivityUpdatePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Se han enviado  las instrucciones para recuperar su contraseña al correo: example@gmail.com")
                .setCancelable(false)
                .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
                    // Acción cuando se pulsa "Aceptar"
                    val intent: Intent = Intent(this, ActivityUpdatePasswordCode::class.java)
                    startActivity(intent)
                })
                .setIcon(android.R.drawable.ic_dialog_info) // Opcional: Agregar un ícono al diálogo
                .show()

        }
    }
}