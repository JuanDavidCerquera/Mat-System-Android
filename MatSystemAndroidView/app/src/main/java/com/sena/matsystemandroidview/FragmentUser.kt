package com.sena.matsystemandroidview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sena.matsystemandroidview.Componen.DatePicker
import com.sena.matsystemandroidview.Model.MPersona
import com.sena.matsystemandroidview.Services.Login
import com.sena.matsystemandroidview.Services.SMunicipio
import kotlin.text.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentUser : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    // Propiedades para los EditText
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
    private lateinit var eTMunicipio: AutoCompleteTextView
    private var isButtonPressed = false
    private var MunicipioSelect:Int? =1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        SMunicipio.init(requireContext())
        Login.init(requireContext())

         eTPrimerNombre = view.findViewById(R.id.editTextPrimerNombre)
         eTSegundoNombre = view.findViewById(R.id.editTextSegundoNombre)
         eTFechaNacimiento = view.findViewById(R.id.editTextFechaNacimiento)
         eTPrimerApellido = view.findViewById(R.id.editTextPrimerApellido)
         eTSegundoApellido = view.findViewById(R.id.editTextSegundoApellido)
         eTCorreo = view.findViewById(R.id.editTextCorreo)
         eTSexo = view.findViewById(R.id.editTextSexo)
         eTTelefono = view.findViewById(R.id.editTextTelefono)
         eTTipoIdentificacion = view.findViewById(R.id.editTextTipoIdentificacion)
         eTNumeroIdentificacion = view.findViewById(R.id.editTextNumeroIdentificacion)
        eTDireccion = view.findViewById(R.id.editTextDireccion)
        eTMunicipio = view.findViewById(R.id.editTextMunicipio)

        val btnEditar: Button = view.findViewById(R.id.buttonEditar)
        val btnCerrarsession :Button = view.findViewById(R.id.buttonCerrarSesion)

        val UserSession = requireContext().getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE)
        val editor = UserSession.edit()
// Recuperar la información
        val personaId = UserSession.getInt("usuarioId", 0)
        val primerNombre = UserSession.getString("personaprimerNombre", "")
        val segundoNombre = UserSession.getString("personasegundoNombre", "")
        val fechaNacimiento = UserSession.getString("personafechaNacimiento", "")
        val primerApellido = UserSession.getString("personaprimerApellido", "")
        val segundoApellido = UserSession.getString("personasegundoApellido", "")
        val correoElectronico = UserSession.getString("personacorreoElectronico", "")
        val telefono = UserSession.getInt("personatelefono", 0)
        val tipoDocumento = UserSession.getString("personatipoDocumento", "")
        val numeroDocumento = UserSession.getInt("personanumeroDocumento", 0)
        val genero = UserSession.getString("personagenero", "")
        val direccion = UserSession.getString("personadireccion", "")
        MunicipioSelect = UserSession.getInt("municipioId", 1)

// Asignar valores a los EditText
        eTPrimerNombre.setText(primerNombre)
        eTSegundoNombre.setText(segundoNombre)
        eTFechaNacimiento.setText(fechaNacimiento)
        eTPrimerApellido.setText(primerApellido)
        eTSegundoApellido.setText(segundoApellido)
        eTCorreo.setText(correoElectronico)
        eTSexo.setText(genero)
        eTTelefono.setText(telefono.toString())
        eTTipoIdentificacion.setText(tipoDocumento)
        eTNumeroIdentificacion.setText(numeroDocumento.toString())
        eTDireccion.setText(direccion)

        SMunicipio.CargarSelect{select, idMAP ->
            val adapter: ArrayAdapter<String> = ArrayAdapter(
                requireContext(),
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

        btnEditar.setOnClickListener {
            if (!isButtonPressed) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Desea modificar su información?")
                    .setMessage("Se habilitaran los campos para su edicion, vuelva a presionar el boton para confirmar")
                    .setCancelable(true)
                    .setNegativeButton("Cancelar") { dialog, which ->
                        enableEditTexts(false)
                    }
                    .setPositiveButton("Aceptar") { dialog, which ->
                        enableEditTexts(true)
                        btnEditar.text = "Confirmar"
                        isButtonPressed = !isButtonPressed
                    }
                    .setIcon(android.R.drawable.ic_dialog_info) // Opcional: Agregar un ícono al diálogo
                    .show()
            }else{
                //guardar datos
                try {
                    Login.ActualizarPersona(ModerPerson(), personaId)
                    AlertDialog.Builder(requireContext())
                        .setTitle("Actualizacion exitosa")
                        .setMessage("Se ha actualizado sus datos correctamente")
                        .setCancelable(true)
                        .show()
                    btnEditar.text = "Editar"
                    isButtonPressed = !isButtonPressed
                    enableEditTexts(false)
                }catch (e:Exception){
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage("Se ha presentado un error al actualizar sus datos")
                        .setCancelable(true)
                        .show()
                }
            }
        }
        btnCerrarsession.setOnClickListener{
            // Eliminar todos los datos de "SesionUsuario"
            editor.clear()
            editor.apply()

            // Redirigir a la actividad de login
            val intent = Intent(requireContext(), ActivityLogin::class.java)
            startActivity(intent)

            // Finalizar la actividad actual para que el usuario no pueda volver a ella con el botón de "Atrás"
            activity?.finish()
        }


        return view

    }
    private fun ModerPerson(): MPersona {
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


    private fun showDatePickerDialog(editText:EditText) {
        val datePicker = DatePicker {day,month,year -> onDateSelected(day, month,year,editText)}
        datePicker.show(parentFragmentManager,"datapicker")
    }
    fun onDateSelected(day:Int,month:Int,year:Int,editText:EditText){
        val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, day)
        editText.setText(formattedDate)
    }

    fun enableEditTexts(enable: Boolean) {
        // Habilitar o deshabilitar los EditText según el parámetro
        eTPrimerNombre.isEnabled = enable
        eTSegundoNombre.isEnabled = enable
        eTFechaNacimiento.isEnabled = enable
        eTPrimerApellido.isEnabled = enable
        eTSegundoApellido.isEnabled = enable
        eTCorreo.isEnabled = enable
        eTSexo.isEnabled = enable
        eTTelefono.isEnabled = enable
        eTTipoIdentificacion.isEnabled = enable
        eTNumeroIdentificacion.isEnabled = enable
        eTDireccion.isEnabled = enable
        eTMunicipio.isEnabled = enable
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentUser().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}