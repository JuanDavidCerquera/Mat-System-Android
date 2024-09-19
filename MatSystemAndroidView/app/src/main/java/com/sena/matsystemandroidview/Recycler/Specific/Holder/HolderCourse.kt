package com.sena.matsystemandroidview.Recycler.Specific.Holder

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.sena.matsystemandroidview.FragmentCourse
import com.sena.matsystemandroidview.R
import com.sena.matsystemandroidview.Recycler.Generic.Holder.GenericHolder
import org.json.JSONObject

class HolderCourse(view : View, private val fragmentManager: FragmentManager): GenericHolder(view) {
val card = view.findViewById<CardView>(R.id.cardCourse)
    val etnombre: TextView = view.findViewById(R.id.nombre)
    val etmodalidad: TextView = view.findViewById(R.id.modalidad)
    val etfechaInicio: TextView = view.findViewById(R.id.fechaInicio)
    val etfechaFin: TextView = view.findViewById(R.id.fechaFin)



    override fun render(data: JSONObject) {

        val nombre =data.getString("nombre")
        val modalidad =data.getString("modalidad")
        val fechaInicio =data.getString("fechaInicio")
        val fechaFin =data.getString("fechaFin")
        val codigo =data.getString("codigo")
        val requisitos =data.getString("requisitos")
        val descripcion =data.getString("descripcion")


        etnombre.text=nombre
        etmodalidad.text=modalidad
        etfechaInicio.text=fechaInicio
        etfechaFin.text=fechaFin

        card.setOnClickListener{

            val bundle = Bundle()
            bundle.putString("nombre" ,nombre)
            bundle.putString("modalidad" ,modalidad)
            bundle.putString("fechaInicio" ,fechaInicio)
            bundle.putString("fechaFin" ,fechaFin)
            bundle.putString("codigo" ,codigo)
            bundle.putString("requisitos" ,requisitos)
            bundle.putString("descripcion" ,descripcion)

            val fragment =  FragmentCourse()
            fragment.setArguments(bundle);
            fragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .addToBackStack(null)
                .commit()
        }

    }
}