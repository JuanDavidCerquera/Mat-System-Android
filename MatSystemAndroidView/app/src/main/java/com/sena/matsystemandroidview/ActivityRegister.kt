package com.sena.matsystemandroidview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sena.matsystemandroidview.Componen.DatePicker
import com.sena.matsystemandroidview.Model.MPersona
import com.sena.matsystemandroidview.Services.Login
import com.sena.matsystemandroidview.Services.SMunicipio

class ActivityRegister : AppCompatActivity() {

    private lateinit var eTPrimerNombre: EditText
    private lateinit var eTSegundoNombre: EditText
    private lateinit var eTFechaNacimiento: EditText
    private lateinit var eTPrimerApellido: EditText
    private lateinit var eTSegundoApellido: EditText
    private lateinit var eTCorreo: EditText
    private lateinit var eTSexo: EditText
    private lateinit var eTTelefono: EditText
    private lateinit var eTTipoIdentificacion: EditText
    private lateinit var eTNumeroIdentificacion: EditText
    private lateinit var eTDireccion: EditText
    private lateinit var eTContraseña: EditText
    private lateinit var eTMunicipio: AutoCompleteTextView
    private var MunicipioSelect:Int? =1
    private lateinit var Contraseña:String
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        Login.init(this)
        SMunicipio.init(this)
        // Inicializar vistas después de setContentView()
        eTPrimerNombre = findViewById(R.id.editTextPrimerNombre)
        eTSegundoNombre = findViewById(R.id.editTextSegundoNombre)
        eTFechaNacimiento = findViewById(R.id.editTextFechaNacimiento)
        eTPrimerApellido = findViewById(R.id.editTextPrimerApellido)
        eTSegundoApellido = findViewById(R.id.editTextSegundoApellido)
        eTCorreo = findViewById(R.id.editTextCorreo)
        eTSexo = findViewById(R.id.editTextSexo)
        eTTelefono = findViewById(R.id.editTextTelefono)
        eTTipoIdentificacion = findViewById(R.id.editTextTipoIdentificacion)
        eTNumeroIdentificacion = findViewById(R.id.editTextNumeroIdentificacion)
        eTDireccion = findViewById(R.id.editTextDireccion)
        eTContraseña = findViewById(R.id.editTextContraseña)
        eTMunicipio = findViewById(R.id.editTextMunicipio)
        button = findViewById(R.id.button)

        SMunicipio.CargarSelect{ select, idMAP ->
            val adapter: ArrayAdapter<String> = ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                select)
            eTMunicipio.setAdapter(adapter)
            eTMunicipio.setText(idMAP.filterValues { it == MunicipioSelect }.keys.firstOrNull())

            eTMunicipio.setOnItemClickListener { parent, view, position, id ->
                val selectedName = parent.getItemAtPosition(position) as String//toma el nombre de la posicion seleccionada
                val selectedId = idMAP[selectedName]  // Con el nombre tomado obtiene el id con el map
                MunicipioSelect = selectedId
            }
        }
        eTFechaNacimiento.setOnClickListener{ showDatePickerDialog(eTFechaNacimiento) }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        button.setOnClickListener {

            Login.GuardarPersona(ModerPerson(),Contraseña)

            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)


        }
    }
    private fun showDatePickerDialog(editText:EditText) {
        val datePicker = DatePicker {day,month,year -> onDateSelected(day, month,year,editText)}
        datePicker.show(supportFragmentManager,"datapicker")
    }
    private fun onDateSelected(day:Int,month:Int,year:Int,editText:EditText){
        val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, day)
        editText.setText(formattedDate)
    }

    private fun ModerPerson(): MPersona {
        Contraseña = eTContraseña.text.toString()
        return  MPersona(
            primerNombre = eTPrimerNombre.text.toString(),
            segundoNombre = eTSegundoNombre.text.toString(),
            fechaNacimiento =eTFechaNacimiento.text.toString(),
            primerApellido = eTPrimerApellido.text.toString(),
            segundoApellido = eTSegundoApellido.text.toString(),
            correoElectronico = eTCorreo.text.toString(),
            telefono = eTTelefono.text.toString().toInt(),
            tipoDocumento = eTTipoIdentificacion.text.toString(),
            numeroDocumento = eTNumeroIdentificacion.text.toString().toInt(),
            genero = eTSexo.text.toString(),
            direccion = eTDireccion.text.toString(),
            municipioId = MunicipioSelect!!
        )


    }
}