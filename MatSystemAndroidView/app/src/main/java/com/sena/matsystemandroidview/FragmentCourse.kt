package com.sena.matsystemandroidview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCourse.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCourse : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param_nombre: String? = null
    private var param_modalidad: String? = null
    private var param_fechaInicio: String? = null
    private var param_fechaFin: String? = null
    private var param_codigo: String? = null
    private var param_requisitos: String? = null
    private var param_descripcion: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param_nombre= it.getString("nombre")
            param_modalidad= it.getString("modalidad")
            param_fechaInicio= it.getString("fechaInicio")
            param_fechaFin= it.getString("fechaFin")
            param_codigo= it.getString("codigo")
            param_requisitos= it.getString("requisitos")
            param_descripcion= it.getString("descripcion")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_course, container, false)

        val nombre:TextView = view.findViewById(R.id.tenombre)
        val modalidad:TextView = view.findViewById(R.id.temodalidad)
        val fechaInicio:TextView = view.findViewById(R.id.tefechaInicio)
        val fechaFin:TextView = view.findViewById(R.id.tefechaFin)
        val codigo:TextView = view.findViewById(R.id.tecodigo)
        val requisitos:TextView = view.findViewById(R.id.terequisitos)
        val descripcion:TextView = view.findViewById(R.id.tedescripcion)

        nombre.text= param_nombre
        modalidad.text= param_modalidad
        fechaInicio.text= param_fechaInicio
        fechaFin.text= param_fechaFin
        codigo.text= param_codigo
        requisitos.text= param_requisitos
        descripcion.text= param_descripcion

        val bl = view.findViewById<Button>(R.id.buttonEnrollLeft)

        bl.setOnClickListener{
           parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentDocumentList())
               .addToBackStack(null)
                .commit()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCourse().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}